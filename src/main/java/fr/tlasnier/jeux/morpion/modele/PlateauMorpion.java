package fr.tlasnier.jeux.morpion.modele;

import fr.tlasnier.jeux.morpion.modele.exception.CaseDejaJoueeException;
import fr.tlasnier.jeux.morpion.modele.exception.CaseInexistanteException;
import fr.tlasnier.minmax.Clonable;

/**
 * Created by Thibault on 24/01/14.
 */
public class PlateauMorpion implements Clonable{
    private int plateau[][] = new int[3][3];

    public PlateauMorpion() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                plateau[i][j] = 0;
    }

    public PlateauMorpion(PlateauMorpion original) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                plateau[i][j] = original.get(i,j);
    }

    public int get(int i, int j) {
        return plateau[i][j];
    }

    public boolean ligneGagnee() {
        return (ligneGagnee(0) || ligneGagnee(1) || ligneGagnee(2));
    }

    public boolean ligneGagnee(int i) {
        int premiereCase = plateau[i][0];
        return (premiereCase != Morpion.EMPTY && premiereCase == plateau[i][1] && premiereCase == plateau[i][2]);
    }

    public boolean colonneGagnee() {
        return (colonneGagnee(0) || colonneGagnee(1) || colonneGagnee(2));

    }

    public boolean colonneGagnee(int j) {
        int premiereCase = plateau[0][j];
        return (premiereCase != Morpion.EMPTY && premiereCase == plateau[1][j] && premiereCase == plateau[2][j]);
    }

    public boolean diagonaleGagnee() {
        return premiereDiagonaleGagnee() || deuxiemeDiagonaleGagnee();
    }

    public boolean premiereDiagonaleGagnee() {
        return (plateau[1][1] != Morpion.EMPTY && plateau[0][0] == plateau[1][1] && plateau[1][1] == plateau[2][2]);
    }

    public boolean deuxiemeDiagonaleGagnee() {
        return (plateau[1][1] != Morpion.EMPTY && plateau[2][0] == plateau[1][1] && plateau[1][1] == plateau[0][2]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 3 ; i++)
        {
            for(int j = 0; j < 3 ; j++)
                builder.append(" | ").append(plateau[i][j]);
            builder.append(" |\n");
        }

        return builder.toString();
    }

    public void set(int i, int j, int val) throws CaseDejaJoueeException, CaseInexistanteException {
        if( i < 0 || i >= 3 || j < 0 || j >= 3 )
            throw new CaseInexistanteException("La case [" + i + "," + j + "] n'existe pas !");
        if(get(i,j) != Morpion.EMPTY)
            throw new CaseDejaJoueeException("Case [" + i + "," + j + "] contient déjà la valeur " + get(i,j));
        plateau[i][j] = val;
    }

    @Override
    public Clonable getClone() {
        return new PlateauMorpion(this);
    }

    public boolean estPlein() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (plateau[i][j] == Morpion.EMPTY)
                    return false;

        return true;
    }
}
