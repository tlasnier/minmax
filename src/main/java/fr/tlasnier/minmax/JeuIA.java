package fr.tlasnier.minmax;

import java.util.List;

/**
 * Created by Thibault on 25/01/14.
 */
public interface JeuIA<C extends Coup, J extends Joueur> extends Clonable {
    /**
     * Retourne l'objet lui-même pour pouvoir faire un pipe.
     * @param coup
     * @return
     */
    public JeuIA jouerLeCoup(C coup);

    public boolean estFini();

    public List<C> listerLesCoups();

    public J getJoueurCourant();
}
