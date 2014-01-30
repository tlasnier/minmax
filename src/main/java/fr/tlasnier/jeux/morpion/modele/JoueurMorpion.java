package fr.tlasnier.jeux.morpion.modele;

import fr.tlasnier.minmax.Joueur;

/**
 * Created by Thibault on 25/01/14.
 */
public class JoueurMorpion implements Joueur {
    private int camp;

    public JoueurMorpion(int camp) {
        this.camp = camp;
    }

    @Override
    public int getCamp() {
        return camp;
    }
}
