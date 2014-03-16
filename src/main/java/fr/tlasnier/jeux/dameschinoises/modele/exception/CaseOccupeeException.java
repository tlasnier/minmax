package fr.tlasnier.jeux.dameschinoises.modele.exception;

/**
 * Created by Thibault on 16/03/14.
 */
public class CaseOccupeeException extends Exception {
    public CaseOccupeeException() {
        super();
    }

    public CaseOccupeeException(String message) {
        super(message);
    }

    public CaseOccupeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaseOccupeeException(Throwable cause) {
        super(cause);
    }
}
