package Lesson_6_7_8.Server.services;

import Lesson_6_7_8.Server.interfaces.AuthenticationService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final int PORT = 8081;

    private List<ClientHandler> clientsList;

    private AuthenticationService authService;




    public MyServer () {
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            this.authService =  new AuthenticationServiceImpl();
            authService.start();

            clientsList = new ArrayList<>();

            while (true) {
                Socket socket = serverSocket.accept();

                new ClientHandler(this, socket);


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            if (authService != null) {
                authService.stop();

            }
        }

    }

    public synchronized void sendMessageToClients(String message) {
        for(ClientHandler c : clientsList) {
            c.sendMessage(message);
        }
    }

    public synchronized AuthenticationService getAuthenticationService() {
        return this.authService;
    }

    public synchronized void subscribe(ClientHandler c) {
        clientsList.add(c);
    }

    public synchronized void unSubscribe(ClientHandler c) {
        clientsList.remove(c);
    }

    public synchronized boolean isNickBusy(String nick) {
        /*for (ClientHandler c : clientsList) {
            if (c.getName().equals(nick)) {
                return true;
            }
        }
        return false;*/

        return clientsList.stream()
                .anyMatch(a -> a.getName()
                        .equals(nick));
    }

    public synchronized void sendMessageToClient(ClientHandler clientHandler, String toNick, String message) {
        for (ClientHandler c : clientsList) {
            if (c.getName().equals(toNick)) {
                c.sendMessage(clientHandler.getName() + ": send private message " + " " + message);
                clientHandler.sendMessage("You send private message: " + toNick + ": " + message);
                return;
            } else {
            clientHandler.sendMessage("SERVER NOTIFICATION: " + toNick + " is offline");
            }
        }
    }

    public synchronized void sendOnlineClientLists(ClientHandler c) {
        c.sendMessage("SERVER NOTIFICATION: now online " + clientsList.toString());
    }
}
