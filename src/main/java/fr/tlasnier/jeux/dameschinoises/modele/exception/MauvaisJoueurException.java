package fr.tlasnier.jeux.dameschinoises.modele.exception;

/**
 * Created by Thibault on 16/03/14.
 */
public class MauvaisJoueurException extends Exception {
    public MauvaisJoueurException() {
        super();
    }

    public MauvaisJoueurException(String message) {
        super(message);
    }

    public MauvaisJoueurException(String message, Throwable cause) {
        super(message, cause);
    }

    public MauvaisJoueurException(Throwable cause) {
        super(cause);
    }
}
