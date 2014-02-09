package fr.tlasnier.jeux.puissance4.modele;

import fr.tlasnier.jeux.puissance4.modele.exception.CaseDejaJoueeException;
import fr.tlasnier.jeux.puissance4.modele.exception.CaseInexistanteException;
import fr.tlasnier.jeux.puissance4.modele.exception.ColonnePleineException;
import fr.tlasnier.minmax.Clonable;

/**
 * Created by Thibault on 07/02/14.
 */
public class PlateauPuissance4 implements Clonable{
    private int plateau[][] = new int[8][8];

    public PlateauPuissance4() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                plateau[i][j] = Puissance4.EMPTY;
    }

    public PlateauPuissance4(PlateauPuissance4 plateauOriginal) {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                try {
                    plateau[i][j] = plateauOriginal.get(i,j);
                } catch (CaseInexistanteException e) {
                    e.printStackTrace();
                }
    }

    public int get(int i, int j) throws CaseInexistanteException {
        if(i < 0 || i > 7 || j < 0 || j > 7)
            throw new CaseInexistanteException("La case [" + i + "," + j + "] n'existe pas !");
        return plateau[i][j];
    }

    public int getLigneVide(int colonne) throws ColonnePleineException {
        for(int i = 0; i < 8; i++)
            try {
                if(get(i, colonne) == Puissance4.EMPTY)
                    return i;
            } catch (CaseInexistanteException e) {
                e.printStackTrace();
            }

        throw new ColonnePleineException("La colonne "+ colonne +" est déjà pleine!");
    }

    public void set(int i, int j, int val) throws CaseInexistanteException, CaseDejaJoueeException {
        if( i < 0 || i >= 8 || j < 0 || j >= 8 )
            throw new CaseInexistanteException("La case [" + i + "," + j + "] n'existe pas !");
        if(get(i,j) != Puissance4.EMPTY)
            throw new CaseDejaJoueeException("La case [" + i + "," + j + "] contient déjà la valeur " + get(i,j));
        plateau[i][j] = val;
    }

    @Override
    public Clonable getClone() {
        return new PlateauPuissance4(this);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | \n\n");
        for(int i = 7; i >= 0 ; i--)
        {
            for(int j = 0; j < 8 ; j++)
                builder.append(" | ").append(plateau[i][j]);
            builder.append(" |\n");
        }

        return builder.toString();
    }

    public boolean estPlein() {

        for(int j = 0; j < 8; j++)
            for(int i = 7; i >= 0; i--)
                try {
                    if (get(i,j) != Puissance4.EMPTY)
                        return false;
                } catch (CaseInexistanteException e) {
                    e.printStackTrace();
                }

        return true;
    }
}
