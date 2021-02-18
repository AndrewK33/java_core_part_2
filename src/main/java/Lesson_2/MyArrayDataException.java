package Lesson_2;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int i, int j) {
        super();
    }

    public MyArrayDataException(String message) {
        super(message);
    }

    public MyArrayDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArrayDataException(Throwable cause) {
        super(cause);
    }

    protected MyArrayDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
