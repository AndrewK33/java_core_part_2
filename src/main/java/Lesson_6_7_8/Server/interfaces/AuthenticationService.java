package Lesson_6_7_8.Server.interfaces;

public interface AuthenticationService {

    void start();
    void stop();

    String getNickByLoginAndPass(String login, String pass);

}
