package fr.tlasnier.jeux.morpion.modele;

import fr.tlasnier.jeux.morpion.modele.exception.CaseDejaJoueeException;
import fr.tlasnier.jeux.morpion.modele.exception.CaseInexistanteException;
import fr.tlasnier.minmax.Clonable;
import fr.tlasnier.minmax.JeuIA;
import fr.tlasnier.minmax.Joueur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibault on 24/01/14.
 */
public class JeuMorpion implements JeuIA<CoupMorpion, JoueurMorpion> {
    private PlateauMorpion plateau;
    private JoueurMorpion joueur1 = new JoueurMorpion(Morpion.X);
    private JoueurMorpion joueur2 = new JoueurMorpion(Morpion.O);
    private boolean joueurCourant; //vrai si joueur 1 est le joueur courant.

    public JeuMorpion() {
        plateau = new PlateauMorpion();
        joueurCourant = true;
    }

    public JeuMorpion(JeuMorpion jeuMorpion) {
        plateau = (PlateauMorpion)jeuMorpion.plateau.getClone();
        joueurCourant = jeuMorpion.joueurCourant;
    }

    @Override
    public JeuIA jouerLeCoup(CoupMorpion coup) {
        try {
            plateau.set(coup.getI(), coup.getJ(), getJoueurCourant().getCamp());
            joueurCourant = !joueurCourant;
        } catch (CaseDejaJoueeException e) {
            System.out.println(e.getMessage());
        } catch (CaseInexistanteException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    @Override
    public boolean estFini() {
        return plateau.estPlein() || plateau.diagonaleGagnee() || plateau.ligneGagnee() || plateau.colonneGagnee();
    }

    @Override
    public List<CoupMorpion> listerLesCoups() {
        List<CoupMorpion> liste = new ArrayList<CoupMorpion>();
        for (int i = 0; i < 3 ; i ++)
            for (int j = 0; j < 3 ; j++)
                if (plateau.get(i,j) == Morpion.EMPTY)
                    liste.add(new CoupMorpion(i,j));
        return liste;
    }

    @Override
    public JoueurMorpion getJoueurCourant() {
        return (joueurCourant? joueur1 : joueur2);
    }

    @Override
    public Clonable getClone() {
        return new JeuMorpion(this);
    }

    @Override
    public double evaluer(Joueur joueur) {
        int campDuJoueur = joueur.getCamp();

        double scoreX = 1, scoreO = 1;
        boolean victoireX = false, victoireO = false;
        //par lignes
        for(int i = 0; i < 3 ; i++) {
            int compteVide = 0, compteX = 0, compteO = 0;
            for (int j = 0; j < 3; j++){
                int _case = plateau.get(i,j);
                int tmp = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
            }
            switch (compteVide) {
                case 3 : scoreX++; scoreO++; break;
                case 2 : if (compteX == 1) scoreX+=5; else scoreO+=5; break;
                case 1 : if (compteX == 2) scoreX+=20; else if (compteO == 2) scoreO+=20; break;
                case 0 : if (compteX == 3) victoireX = true; else if (compteO == 3) victoireO = true; break;
            }
        }

        //par colonnes
        for(int j = 0; j < 3 ; j++) {
            int compteVide = 0, compteX = 0, compteO = 0;
            for (int i = 0; i < 3; i++){
                int _case = plateau.get(i,j);
                int tmp = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
            }
            switch (compteVide) {
                case 3 : scoreX++; scoreO++; break;
                case 2 : if (compteX == 1) scoreX+=5; else scoreO+=5; break;
                case 1 : if (compteX == 2) scoreX+=20; else if (compteO == 2) scoreO+=20; break;
                case 0 : if (compteX == 3) victoireX = true; else if (compteO == 3) victoireO = true; break;
            }
        }

        //diagonale 1:
        int compteVide = 0, compteX = 0, compteO = 0;
        int _case = plateau.get(0,0);
        int tmp = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = plateau.get(1,1);
        int tmp2 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
         _case = plateau.get(2,2);
        int tmp3 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        switch (compteVide) {
            case 3 : scoreX++; scoreO++; break;
            case 2 : if (compteX == 1) scoreX+=5; else scoreO+=5; break;
            case 1 : if (compteX == 2) scoreX+=20; else if (compteO == 2) scoreO+=20; break;
            case 0 : if (compteX == 3) victoireX = true; else if (compteO == 3) victoireO = true; break;
        }
        //diagonale 2 :
        compteVide = 0; compteX = 0; compteO = 0;
        _case = plateau.get(2,0);
        int tmp4 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = plateau.get(1,1);
        int tmp5 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = plateau.get(0,2);
        int tmp6 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        switch (compteVide) {
            case 3 : scoreX++; scoreO++; break;
            case 2 : if (compteX == 1) scoreX+=5; else scoreO+=5; break;
            case 1 : if (compteX == 2) scoreX+=20; else if (compteO == 2) scoreO+=20; break;
            case 0 : if (compteX == 3) victoireX = true; else if (compteO == 3) victoireO = true; break;
        }

        if (victoireX)
            return (campDuJoueur == Morpion.X ? 10000 : 0);
        if (victoireO)
            return (campDuJoueur == Morpion.O ? 10000 : 0);

        {
            if (getJoueurCourant().getCamp() == Morpion.X)
                scoreX*=2;
            else
                scoreO*=2;
        }

        return (campDuJoueur == Morpion.X ? scoreX/scoreO : scoreO/scoreX);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------------------\n").
                append("C'est au joueur ").append(getJoueurCourant().getCamp()).append(" de jouer!\n").
                append(plateau);
        return builder.toString();
    }
}
