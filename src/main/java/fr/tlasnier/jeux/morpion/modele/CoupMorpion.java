package fr.tlasnier.jeux.morpion.modele;

import fr.tlasnier.minmax.Coup;

/**
 * Created by Thibault on 24/01/14.
 */
public class CoupMorpion implements Coup {
    private int i;
    private int j;

    public CoupMorpion(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
