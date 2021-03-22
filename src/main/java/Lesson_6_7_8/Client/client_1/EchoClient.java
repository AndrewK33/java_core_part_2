package Lesson_6_7_8.Client.client_1;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {
    private JTextField msgInputField;
    private JTextArea chatArea;

    private final static String IP_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8081;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private boolean isAuthorized;



    private EchoClient () {
        try {
            connection();
        } catch (IOException ignored) {
        }
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EchoClient();
        });
    }


    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }




    private void connection () throws IOException {
        socket = new Socket(IP_ADDRESS, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        setAuthorized(false);


        Thread thread = new Thread(() -> {
            try {
                while (true){
                    String serverMessage = dis.readUTF();
                    if (serverMessage.startsWith("/authisok")) {
                        setAuthorized(true);
                        chatArea.append("SERVER NOTIFICATION: " + serverMessage + "\n");
                        break;
                    }
                    chatArea.append("SERVER NOTIFICATION: " + serverMessage + "\n");
                }
                while (true){

                    String serverMessage = dis.readUTF();
                    if (serverMessage.equals("/q")){
                        break;
                    }
                    chatArea.append(serverMessage + "\n");
                }
            } catch (IOException ignored) {
            }
            closeConnetection();
        });
        /*thread.setDaemon(true);*/
        thread.start();
    }


    private void sendMessageToServer() {
        if (!msgInputField.getText().trim().isEmpty()){
            try {
                String messageToServer = msgInputField.getText();
                dos.writeUTF(messageToServer);
                /*if (!messageToServer.equals("/q") || !messageToServer.startsWith("/auth")) {
                    chatArea.append(messageToServer + "\n");
                }*/
                msgInputField.setText("");
            }
            catch (IOException ignored) {
            }
        }
    }




    private void prepareGUI() {

        setBounds(600, 300, 500, 500);
        setTitle("Мессенджер");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Сенд");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);

        btnSendMsg.addActionListener(e -> {
            sendMessageToServer();
        });

        msgInputField.addActionListener(e -> {
            sendMessageToServer();
        });

        /*addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    dos.writeUTF("/q");
                } catch (IOException ignored) {
                }

            }
        });*/

        setVisible(true);
    }

    private void closeConnetection() {
        try {
            dos.flush();
        } catch (IOException ignored) {
        }

        try {
            dis.close();
        } catch (IOException ignored) {
        }

        try {
            dos.close();
        } catch (IOException ignored) {
        }

        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }




}




