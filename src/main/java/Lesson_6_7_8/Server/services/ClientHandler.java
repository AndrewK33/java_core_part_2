package Lesson_6_7_8.Server.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class ClientHandler {


    private MyServer myServer;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;




    private String name;


    public ClientHandler(MyServer myServer, Socket socket) {

        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";


            new Thread(() -> {
               try {

                   authentication();
                   readMessage();
               } catch (IOException e) {
                   System.out.println(e.getMessage());
               } finally {
                   closeConnection();
               }
            }).start();


        } catch (IOException e) {
            System.out.println("ERROR: SERVER ERROR");
        }

    }




    private void authentication() throws IOException {
        while (true){
            String authStr = dis.readUTF();
            if (authStr.startsWith("/auth")) {

                String [] arr = authStr.split("\\s");
                String nick = myServer
                        .getAuthenticationService()
                        .getNickByLoginAndPass(arr[1], arr[2]);
                if (!nick.isEmpty()) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMessage("/authisok " + nick);
                        name = nick;
                        myServer.sendMessageToClients("SERVER NOTIFICATION: " + nick + " is joined to chat" + "\n");
                        myServer.subscribe(this);
                        return;

                    } else {
                        sendMessage(" This " + nick + " is busy");
                    }
                } else {
                    sendMessage(" Wrong login or pass");
                }
            }
        }
    }

    public void sendMessage(String message) {
        try {
            dos.writeUTF(message);
        } catch (IOException ignored) {
        }
    }

    private void readMessage() throws IOException {
        while (true){
            String messageFromClient = dis.readUTF();
            if (messageFromClient.startsWith("/")) {



                if (messageFromClient.startsWith("/w")) {
                    String [] arr = messageFromClient.trim().split(" ", 3);
                    myServer.sendMessageToClient(this, arr[1], arr[2]);
                    continue;
                }

                if (messageFromClient.startsWith("/online")) {
                    myServer.sendOnlineClientLists(this);
                    continue;
                }

                if (messageFromClient.equals("/q")) {
                    sendMessage(messageFromClient);
                    return;
                }
            }
            myServer.sendMessageToClients(name + ": " + messageFromClient);

        }
    }



    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }

    private void closeConnection() {
        myServer.unSubscribe(this);
        myServer.sendMessageToClients("SERVER NOTIFICATION: " + name + " leave chat" + "\n");
    }




}
