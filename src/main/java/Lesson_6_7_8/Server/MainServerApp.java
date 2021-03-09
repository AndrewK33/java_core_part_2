package Lesson_6_7_8.Server;

import Lesson_6_7_8.Client.EchoClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServerApp {
    public static void main(String[] args) {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8181)){
            System.out.println("Server start");
            socket = serverSocket.accept();
            System.out.println("Client ready");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                String clienMessage = dis.readUTF();
                System.out.println(clienMessage);

                if (clienMessage.equalsIgnoreCase("/q")) {
                    dos.writeUTF(clienMessage);
                    closeConnetection(socket, dis, dos);
                    break;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                String name = reader.readLine();
                                dos.writeUTF("Проверка инпута со стороны сервера: " + name);
                                if (name.equalsIgnoreCase("/end")) {
                                    break;
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();



            }

        } catch (IOException ignored) {
        }

    }

    private static void closeConnetection (Socket s, DataInputStream dis, DataOutputStream dos) {
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
            s.close();
        } catch (IOException ignored) {
        }


    }

}
