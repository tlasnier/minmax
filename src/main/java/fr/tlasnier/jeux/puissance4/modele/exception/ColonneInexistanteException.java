package fr.tlasnier.jeux.puissance4.modele.exception;

/**
 * Created by Thibault on 07/02/14.
 */
public class ColonneInexistanteException extends Exception {
    public ColonneInexistanteException() {
        super();
    }

    public ColonneInexistanteException(String message) {
        super(message);
    }

    public ColonneInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColonneInexistanteException(Throwable cause) {
        super(cause);
    }
}
