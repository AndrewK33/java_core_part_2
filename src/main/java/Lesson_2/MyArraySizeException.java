package Lesson_2;



public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super();
    }

    public MyArraySizeException(String message) {
        super(message);
    }

    public MyArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArraySizeException(Throwable cause) {
        super(cause);
    }

    protected MyArraySizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
