package Lesson_6_7_8.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {
    private JTextField msgInputField;
    private JTextArea chatArea;

    private final static String IP_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8181;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;



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




    private void connection () throws IOException {
        socket = new Socket(IP_ADDRESS, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());


        new Thread(() -> {
            while (true){
                try {
                    String serverMessage = dis.readUTF();
                    if (serverMessage.equalsIgnoreCase("/q")){
                        break;
                    }

                    chatArea.append(serverMessage + "\n");
                } catch (IOException ignored) {
                }
            }
            closeConnetection();
        }).start();
    }


    private void sendMessageToServer() {
        if (!msgInputField.getText().trim().isEmpty()){
            try {
                String messageToServer = msgInputField.getText();
                dos.writeUTF(messageToServer);
                chatArea.append("Проверка инпута с чата: " + msgInputField.getText() + "\n");
                msgInputField.setText("");

            } catch (IOException ignored) {
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




