package fr.tlasnier.jeux.dameschinoises.modele.exception;

/**
 * Created by Thibault on 16/03/14.
 */
public class CaseVideException extends Exception {
    public CaseVideException() {
        super();
    }

    public CaseVideException(String message) {
        super(message);
    }

    public CaseVideException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaseVideException(Throwable cause) {
        super(cause);
    }
}
