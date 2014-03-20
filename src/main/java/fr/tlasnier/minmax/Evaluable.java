package fr.tlasnier.minmax;

/**
 * Created by Thibault on 24/01/14.
 */
@Deprecated
public interface Evaluable {
    /**
     * Evalue la position du joueur donné par rapport au reste du plateau
     * @param joueur
     * @return
     */
    @Deprecated
    double evaluer(Joueur joueur);
}
