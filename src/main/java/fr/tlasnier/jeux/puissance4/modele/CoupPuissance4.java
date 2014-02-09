package fr.tlasnier.jeux.puissance4.modele;

import fr.tlasnier.minmax.Coup;

/**
 * Created by Thibault on 07/02/14.
 */
public class CoupPuissance4 implements Coup {
    private int colonne;
    private int ligne;

    public CoupPuissance4(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
