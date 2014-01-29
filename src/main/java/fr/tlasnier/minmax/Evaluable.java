package fr.tlasnier.minmax;

/**
 * Created by Thibault on 24/01/14.
 */
public interface Evaluable {
    /**
     * Evalue la position du joueur donné par rapport au reste du plateau
     * @param joueur
     * @return
     */
    double evaluer(Joueur joueur);
}
