package fr.tlasnier.jeux.puissance4.modele;

import fr.tlasnier.jeux.puissance4.modele.exception.CaseDejaJoueeException;
import fr.tlasnier.jeux.puissance4.modele.exception.CaseInexistanteException;
import fr.tlasnier.jeux.puissance4.modele.exception.ColonnePleineException;
import fr.tlasnier.minmax.Clonable;

/**
 * Created by Thibault on 07/02/14.
 */
public class PlateauPuissance4 implements Clonable{
    private int plateau[][] = new int[Puissance4.NB_LIGNES][Puissance4.NB_COLONNES];

    public PlateauPuissance4() {
        for(int i = 0; i < Puissance4.NB_LIGNES; i++)
            for(int j = 0; j < Puissance4.NB_COLONNES; j++)
                plateau[i][j] = Puissance4.VIDE;
    }

    public PlateauPuissance4(PlateauPuissance4 plateauOriginal) {
        for(int i = 0; i < Puissance4.NB_LIGNES; i++)
            for(int j = 0; j < Puissance4.NB_COLONNES; j++)
                try {
                    plateau[i][j] = plateauOriginal.get(i,j);
                } catch (CaseInexistanteException e) {
                    e.printStackTrace();
                }
    }

    public int get(int i, int j) throws CaseInexistanteException {
        if(i < 0 || i >= Puissance4.NB_LIGNES || j < 0 || j >= Puissance4.NB_COLONNES)
            throw new CaseInexistanteException("La case [" + i + "," + j + "] n'existe pas !");
        return plateau[i][j];
    }

    public int getLigneVide(int colonne) throws ColonnePleineException {
        for(int i = 0; i < Puissance4.NB_LIGNES; i++)
            try {
                if(get(i, colonne) == Puissance4.VIDE)
                    return i;
            } catch (CaseInexistanteException e) {
                e.printStackTrace();
            }

        throw new ColonnePleineException("La colonne "+ colonne +" est déjà pleine!");
    }

    public void set(int i, int j, int val) throws CaseInexistanteException, CaseDejaJoueeException {
        if( i < 0 || i >= Puissance4.NB_LIGNES || j < 0 || j >= Puissance4.NB_COLONNES )
            throw new CaseInexistanteException("La case [" + i + "," + j + "] n'existe pas !");
        if(get(i,j) != Puissance4.VIDE)
            throw new CaseDejaJoueeException("La case [" + i + "," + j + "] contient déjà la valeur " + get(i,j));
        plateau[i][j] = val;
    }

    @Override
    public Clonable getClone() {
        return new PlateauPuissance4(this);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 | \n\n");
        for(int i = Puissance4.NB_LIGNES - 1; i >= 0 ; i--)
        {
            for(int j = 0; j < Puissance4.NB_COLONNES ; j++)
                builder.append(" | ").append(plateau[i][j]);
            builder.append(" |\n");
        }

        return builder.toString();
    }

    public boolean estPlein() {

        for(int j = 0; j < Puissance4.NB_COLONNES; j++)
            for(int i = Puissance4.NB_LIGNES - 1; i >= 0; i--)
                try {
                    if (get(i,j) != Puissance4.VIDE)
                        return false;
                } catch (CaseInexistanteException e) {
                    e.printStackTrace();
                }

        return true;
    }
}
