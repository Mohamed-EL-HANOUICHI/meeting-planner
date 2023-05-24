package meeting.planner.exceptions;

public class NoRoomsAvailableException extends RuntimeException{

    public NoRoomsAvailableException() {
        super();
    }

    public NoRoomsAvailableException(String message) {
        super(message);
    }

    public NoRoomsAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRoomsAvailableException(Throwable cause) {
        super(cause);
    }

    protected NoRoomsAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
