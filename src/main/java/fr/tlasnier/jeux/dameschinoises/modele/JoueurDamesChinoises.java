package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.minmax.Joueur;

/**
 * Created by Thibault on 16/03/14.
 */
public class JoueurDamesChinoises implements Joueur {
    private int camp;

    public JoueurDamesChinoises(int camp) {
        this.camp = camp;
    }

    @Override
    public int getCamp() {
        return camp;
    }
}
