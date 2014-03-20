package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.jeux.dameschinoises.modele.exception.CaseInexistanteException;
import fr.tlasnier.minmax.Evaluateur;

/**
 * Created by Thibault on 20/03/14.
 */
public class EvaluateurDamesChinoises implements Evaluateur<JeuDamesChinoises, JoueurDamesChinoises> {
    private JeuDamesChinoises jeu;
    private static int[][] eval = {
            {0,  0,  0,  0,  11, 18, 27},
            {0,  0,  0,  7,  12, 19, 28},
            {0,  0,  4,  8,  13, 20, 29},
            {0,  7,  8,  9,  14, 21, 30},
            {11, 12, 13, 14, 16, 23, 32},
            {18, 19, 20, 21, 23, 25, 34},
            {27, 28, 29, 30, 32, 34, 36}};

    @Override
    public double evaluer(JoueurDamesChinoises joueur) {
        if (jeu.estFini())
            if (joueur.getCamp() == jeu.getJoueurCourant().getCamp())
                return -10000;
            else
                return 10000;
        int sumJ1 = 0, sumJ2 = 0;
        for (int i = 0; i < DamesChinoises.LARGEUR; i++) {
            for (int j = 0; j < DamesChinoises.LARGEUR; j++) {
                try {
                    if (jeu.getPlateau().get(i,j) == DamesChinoises.ROUGE)
                        sumJ1 += distanceJ1(i,j);
                    else if (jeu.getPlateau().get(i,j) == DamesChinoises.BLEU)
                        sumJ2 += distanceJ2(i,j);
                } catch (CaseInexistanteException e) {
                    e.printStackTrace();
                }
            }
        }
        return joueur.getCamp() == DamesChinoises.ROUGE ? sumJ2-sumJ1 : sumJ1-sumJ2;
    }

    private int distanceJ1(int i, int j) {
        return eval[DamesChinoises.LARGEUR-1 -i][DamesChinoises.LARGEUR-1-j];
    }
    public int distanceJ2(int i, int j) {
        return eval[i][j];
    }

    @Override
    public void setJeu(JeuDamesChinoises jeuIA) {
        jeu = jeuIA;
    }

    @Override
    public JeuDamesChinoises getJeu() {
        return jeu;
    }
}
