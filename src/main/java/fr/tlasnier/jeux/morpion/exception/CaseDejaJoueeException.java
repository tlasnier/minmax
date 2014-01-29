package fr.tlasnier.jeux.morpion.exception;

/**
 * Created by Thibault on 24/01/14.
 */
public class CaseDejaJoueeException extends Exception {
    public CaseDejaJoueeException() {
        super();
    }

    public CaseDejaJoueeException(String message) {
        super(message);
    }

    public CaseDejaJoueeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaseDejaJoueeException(Throwable cause) {
        super(cause);
    }
}
