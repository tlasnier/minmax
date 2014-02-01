package fr.tlasnier.minmax;

import javafx.util.Pair;

/**
 * Created by Thibault on 25/01/14.
 */
public class MinMax<C extends Coup, J extends Joueur> {

    private final int MAX = 1;
    private final int MIN = -1;

    private JeuIA<C, J> _jeu;

    public MinMax(JeuIA<C,J> jeu) {
        this._jeu = jeu;
    }

    public C minmax(int profondeur) {
        return minmaxRec(_jeu, profondeur, MAX, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getKey();
    }
/*
    public Coup negamax(int profondeur) {
        return negamaxRec(_jeu, profondeur, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getKey();
    }
*/
    private Pair<C,Double> minmaxRec(JeuIA<C,J> jeu, int profondeur, int minmax, double alpha, double beta) {
        if(profondeur == 0 || jeu.estFini()) { //évaluation paresseuse plus efficace
            return new Pair<C, Double>(null, jeu.evaluer(_jeu.getJoueurCourant()));
        }
        C meilleurCoup = null;
        double meilleurScore = minmax * Double.NEGATIVE_INFINITY; //+Inf si on doit minimiser, -Inf si on doit maximiser

        for(C coup : jeu.listerLesCoups()) {
            double score = minmaxRec (( ((JeuIA) jeu.getClone()).jouerLeCoup(coup)), //produit le jeu "fils"
                                        profondeur - 1, -minmax, alpha, beta)
                                .getValue();

            if (minmax == MIN) {
                if (alpha > score) {
                    return new Pair<C, Double>(coup, score);
                }
                beta = (beta < score ? beta : score);
            }

            if (minmax == MAX) {
                if (score > beta) {
                    return new Pair<C, Double>(coup, score);
                }
                alpha = (alpha > score ? alpha : score);
            }

            if( (minmax == MAX && score > meilleurScore) || (minmax == MIN && score < meilleurScore)) {
                meilleurCoup = coup;
                meilleurScore = score;
            }
        }
        return new Pair<C, Double>(meilleurCoup, meilleurScore);
    }
/*
    private Pair<Coup,Double> negamaxRec(JeuIA jeu, int profondeur, double alpha, double beta) {
        if(jeu.estFini() || profondeur == 0)
            return new Pair<Coup, Double>(null, jeu.evaluer(_jeu.getJoueurCourant()));

        Coup meilleurCoup = null;
        double meilleurScore = Double.NEGATIVE_INFINITY;

        for(Coup coup : jeu.listerLesCoups()) {
            double score =  - negamaxRec(((JeuIA) jeu.getClone()).jouerLeCoup(coup), profondeur - 1, -beta, -alpha).getValue();

            if (score >= alpha) {
                alpha = score ;
                meilleurCoup = coup ;
                if (alpha >= beta)
                    return new Pair<Coup, Double>(meilleurCoup, meilleurScore);
            }

            if(score > meilleurScore) {
                meilleurCoup = coup;
                meilleurScore = score;
            }

        }

        return new Pair<Coup, Double>(meilleurCoup, meilleurScore);
    }  */
}
