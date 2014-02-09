package fr.tlasnier.jeux.puissance4.modele.exception;

/**
 * Created by Thibault on 07/02/14.
 */
public class ColonnePleineException extends Exception {
    public ColonnePleineException() {
        super();
    }

    public ColonnePleineException(String message) {
        super(message);
    }

    public ColonnePleineException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColonnePleineException(Throwable cause) {
        super(cause);
    }
}
