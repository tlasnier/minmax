package fr.tlasnier.jeux.dameschinoises.modele.exception;

/**
 * Created by Thibault on 18/03/14.
 */
public class DeplacementNonAutoriseException extends Exception {
    public DeplacementNonAutoriseException() {
        super();
    }

    public DeplacementNonAutoriseException(String message) {
        super(message);
    }

    public DeplacementNonAutoriseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeplacementNonAutoriseException(Throwable cause) {
        super(cause);
    }
}
