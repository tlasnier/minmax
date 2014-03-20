package fr.tlasnier.jeux.morpion.modele;

import fr.tlasnier.minmax.Evaluateur;

/**
 * Created by Thibault on 20/03/14.
 */
public class EvaluateurMorpion implements Evaluateur<JeuMorpion,JoueurMorpion> {
    private JeuMorpion jeu;

    @Override
    public double evaluer(JoueurMorpion joueur) {
        int campDuJoueur = joueur.getCamp();

        double scoreX = 1, scoreO = 1;
        boolean victoireX = false, victoireO = false;
        //par lignes
        for(int i = 0; i < 3 ; i++) {
            int compteVide = 0, compteX = 0, compteO = 0;
            for (int j = 0; j < 3; j++){
                int _case = jeu.getPlateau().get(i, j);
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
                int _case = jeu.getPlateau().get(i, j);
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
        int _case = jeu.getPlateau().get(0, 0);
        int tmp = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = jeu.getPlateau().get(1, 1);
        int tmp2 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = jeu.getPlateau().get(2, 2);
        int tmp3 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        switch (compteVide) {
            case 3 : scoreX++; scoreO++; break;
            case 2 : if (compteX == 1) scoreX+=5; else scoreO+=5; break;
            case 1 : if (compteX == 2) scoreX+=20; else if (compteO == 2) scoreO+=20; break;
            case 0 : if (compteX == 3) victoireX = true; else if (compteO == 3) victoireO = true; break;
        }
        //diagonale 2 :
        compteVide = 0; compteX = 0; compteO = 0;
        _case = jeu.getPlateau().get(2, 0);
        int tmp4 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = jeu.getPlateau().get(1, 1);
        int tmp5 = (_case == Morpion.EMPTY ? compteVide++ : (_case == Morpion.X ? compteX++ : compteO++));
        _case = jeu.getPlateau().get(0, 2);
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
            if (jeu.getJoueurCourant().getCamp() == Morpion.X)
                scoreX*=2;
            else
                scoreO*=2;
        }

        return (campDuJoueur == Morpion.X ? scoreX/scoreO : scoreO/scoreX);
    }

    @Override
    public void setJeu(JeuMorpion jeuIA) {
        jeu = jeuIA;
    }

    @Override
    public JeuMorpion getJeu() {
        return jeu;
    }
}
