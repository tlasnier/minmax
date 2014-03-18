package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.jeux.dameschinoises.modele.exception.CaseInexistanteException;
import fr.tlasnier.jeux.dameschinoises.modele.exception.DeplacementNonAutoriseException;
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
        verifierExistence(i, j);
        plateau[i][j] = valeur;
    }

    public int get(int i, int j) throws CaseInexistanteException {
        verifierExistence(i, j);
        return plateau[i][j];
    }

    public boolean pionsPlaces(int camp) {
        if (camp == BLEU) {
            for (int i = 0; i < 4; i++ )
                for (int j = 0; i + j < 4; j++)
                    try {
                        if (get(i,j) != BLEU)
                            return false;
                    } catch (CaseInexistanteException e) {
                        e.printStackTrace();
                    }
            return true;

        }

        else if (camp == ROUGE) {
            for (int i = LARGEUR-1; i > 2; i-- )
                for (int j = LARGEUR-1; i + j > 8; j--)
                    try {
                        if (get(i,j) != ROUGE)
                            return false;
                    } catch (CaseInexistanteException e) {
                        e.printStackTrace();
                    }
            return true;
        }

        else return false;
    }

    public boolean estVoisinDirect(int i1, int j1, int i2, int j2) {
        return ((Math.abs(i1-i2) == 1) && (j1 == j2)) || (Math.abs(j1-j2) == 1 && (i1 == i2)) || (i1+1==i2 && j1+1 == j2) || (i1-1 == i2 && j1-1 == j2);
    }

    public boolean estVoisinSaute(int i1, int j1, int i2, int j2) {
        try {

            if(i1+2 == i2 && j1 == j2) { //deux cases au-dessus
                if (get(i1+1,j1) != VIDE && get(i2,j2) == VIDE)
                    return true;
                else
                    throw new DeplacementNonAutoriseException("Vous ne pouvez pas passer par la case (" + i2 + "," + j2 +")");
            }
            if(i1-2 == i2 && j1 == j2) { //deux cases en-dessous
                if (get(i1-1,j1) != VIDE && get(i2,j2) == VIDE)
                    return true;
                else
                    throw new DeplacementNonAutoriseException("Vous ne pouvez pas passer par la case (" + i2 + "," + j2 +")");
            }
            if(i1 == i2 && j1+2 == j2) { //deux cases à droite
                if (get(i1,j1+1) != VIDE && get(i2,j2) == VIDE)
                    return true;
                else
                    throw new DeplacementNonAutoriseException("Vous ne pouvez pas passer par la case (" + i2 + "," + j2 +")");
            }
            if(i1 == i2 && j1-2 == j2) { //deux cases à gauche
                if (get(i1,j1-1) != VIDE && get(i2,j2) == VIDE)
                    return true;
                else
                    throw new DeplacementNonAutoriseException("Vous ne pouvez pas passer par la case (" + i2 + "," + j2 +")");
            }
            if(i1+2 == i2 && j1+2 == j2) { //deux cases au-dessus à droite
                if (get(i1+1,j1+1) != VIDE && get(i2,j2) == VIDE)
                    return true;
                else
                    throw new DeplacementNonAutoriseException("Vous ne pouvez pas passer par la case (" + i2 + "," + j2 +")");
            }
            if(i1-2 == i2 && j1-2 == j2) { //deux cases en-dessous à gauche
                if (get(i1-1,j1-1) != VIDE && get(i2,j2) == VIDE)
                    return true;
                else
                    throw new DeplacementNonAutoriseException("Vous ne pouvez pas passer par la case (" + i2 + "," + j2 +")");
            }
        }
        catch (CaseInexistanteException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (DeplacementNonAutoriseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public void verifierExistence(int i, int j) throws CaseInexistanteException {
        if ( i < 0 || i >= LARGEUR || j < 0 || j >= LARGEUR )
            throw new CaseInexistanteException("La case ("+ i + "," + j +") n'existe pas!");

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
