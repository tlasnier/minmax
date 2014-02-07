package fr.tlasnier.jeux.morpion.modele.exception;

/**
 * Created by Thibault on 25/01/14.
 */
public class CaseInexistanteException extends Exception {
    public CaseInexistanteException() {
        super();
    }

    public CaseInexistanteException(String message) {
        super(message);
    }

    public CaseInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaseInexistanteException(Throwable cause) {
        super(cause);
    }
}
