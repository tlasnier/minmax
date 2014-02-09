package fr.tlasnier.jeux.puissance4.modele;

import fr.tlasnier.minmax.Joueur;

/**
 * Created by Thibault on 07/02/14.
 */
public class JoueurPuissance4 implements Joueur {
    private int camp;

    public JoueurPuissance4(int camp) {
        this.camp = camp;
    }

    @Override
    public int getCamp() {
        return camp;
    }
}
