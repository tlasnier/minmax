package fr.tlasnier.minmax;

import java.util.List;

/**
 * Created by Thibault on 25/01/14.
 */
public interface JeuIA extends Evaluable, Clonable {
    /**
     * Retourne l'objet lui-même pour pouvoir faire un pipe.
     * @param coup
     * @return
     */
    public JeuIA jouerLeCoup(Coup coup);

    public boolean estFini();

    public List<Coup> listerLesCoups();

    public Joueur getJoueurCourant();
}
