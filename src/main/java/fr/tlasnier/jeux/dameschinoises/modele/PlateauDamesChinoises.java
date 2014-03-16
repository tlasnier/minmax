package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.jeux.dameschinoises.modele.exception.CaseInexistanteException;
import fr.tlasnier.minmax.Clonable;

import static fr.tlasnier.jeux.dameschinoises.modele.DamesChinoises.*;

/**
 * Created by Thibault on 16/03/14.
 */
public class PlateauDamesChinoises implements Clonable {

    private int[][] plateau = new int[LARGEUR][LARGEUR];

    public PlateauDamesChinoises() {
        for (int i = 0; i < LARGEUR; i++)
            for (int j = 0; j < LARGEUR; j++)
                if (i + j < 4)
                    try {
                        set(i, j, ROUGE);
                    } catch (CaseInexistanteException e) {
                        e.printStackTrace();
                    }
                else if (i + j < 9)
                    try {
                        set(i, j, VIDE);
                    } catch (CaseInexistanteException e) {
                        e.printStackTrace();
                    }
                else
                    try {
                        set(i, j, BLEU);
                    } catch (CaseInexistanteException e) {
                        e.printStackTrace();
                    }
    }

    public PlateauDamesChinoises(PlateauDamesChinoises plateauOriginal) {
        for (int i =0; i < LARGEUR; i++)
            for (int j =0; j < LARGEUR; j++)
                try {
                    set(i, j, plateauOriginal.get(i, j));
                } catch (CaseInexistanteException e) {
                    e.printStackTrace();
                }
    }

    public void set(int i, int j, int valeur) throws CaseInexistanteException {
        if ( i < 0 || i >= LARGEUR || j < 0 || j >= LARGEUR )
            throw new CaseInexistanteException("La case ("+ i + "," + j +") n'existe pas!");
        else plateau[i][j] = valeur;
    }

    public int get(int i, int j) throws CaseInexistanteException {
        if ( i < 0 || i >= LARGEUR || j < 0 || j >= LARGEUR )
            throw new CaseInexistanteException("La case ("+ i + "," + j +") n'existe pas!");
        else return plateau[i][j];
    }

    @Override
    public Clonable getClone() {
        return new PlateauDamesChinoises(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = LARGEUR - 1; i >= 0 ; i--)
        {
            for(int j = 0; j < LARGEUR ; j++)
                builder.append(" | ").append(plateau[i][j]);
            builder.append(" |\n");
        }

        return builder.toString();
    }
}
