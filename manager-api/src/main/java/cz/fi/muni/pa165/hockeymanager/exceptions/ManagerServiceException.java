package cz.fi.muni.pa165.hockeymanager.exceptions;

public class ManagerServiceException  extends RuntimeException {
    public ManagerServiceException() {
        super();
    }

    public ManagerServiceException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ManagerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerServiceException(String message) {
        super(message);
    }

    public ManagerServiceException(Throwable cause) {
        super(cause);
    }
}
