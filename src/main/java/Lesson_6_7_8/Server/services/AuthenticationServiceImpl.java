package Lesson_6_7_8.Server.services;

import Lesson_6_7_8.Server.interfaces.AuthenticationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationServiceImpl implements AuthenticationService {


    private List<User> usersList;

    public AuthenticationServiceImpl() {
        usersList = new ArrayList<>();
        usersList.add(new User("MsA", "MsA", "MsA"));
        usersList.add(new User("MsB", "MsB", "MsB"));
        usersList.add(new User("MsC", "MsC", "MsC"));
    }

    @Override
    public void start() {
        System.out.println("Start");
    }

    @Override
    public void stop() {
        System.out.println("Stop");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        /*for (User u : usersList) {
            if (u.login.equals(login) && u.pass.equals(pass)) {
                return u.nick;
            }
        }
        return "";*/


        return usersList.stream()
                .map (a -> {
                    if (a.login.equals(login) && a.pass.equals(pass)) {
                        return a.nick;
                    }
                    return "";
                }).collect(Collectors.joining());
    }


    private class User {

        private String login;
        private String pass;
        private String nick;

        public User(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }


}
