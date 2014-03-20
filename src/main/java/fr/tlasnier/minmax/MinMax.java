package fr.tlasnier.minmax;

import java.util.Collections;
import java.util.List;

/**
 * Created by Thibault on 25/01/14.
 */
public class MinMax<C extends Coup, J extends Joueur> {

    private final int MAX = 1;
    private final int MIN = -1;

    private JeuIA<C, J> _jeu;

    private Evaluateur<JeuIA<C,J>, J> evaluateur;

    public MinMax(JeuIA<C,J> jeu) {
        this._jeu = jeu;
    }

    public MinMax(JeuIA<C,J> jeu, Evaluateur<JeuIA<C,J>, J> eval) {
        this._jeu = jeu;
        this.evaluateur = eval;
    }

    public Evaluateur getEvaluateur() {
        return evaluateur;
    }

    public void setEvaluateur(Evaluateur<JeuIA<C,J>, J> eval) {
        evaluateur = eval;
    }

    public C minmax(int profondeur) {
        return minmaxRec(_jeu, profondeur, MAX, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getKey();
    }

    public Coup negamax(int profondeur) {
        return negamaxRec(_jeu, profondeur, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getKey();
    }

    private Pair<C,Double> minmaxRec(JeuIA<C,J> jeu, int profondeur, int minmax, double alpha, double beta) {
        if(profondeur == 0 || jeu.estFini()) { //évaluation paresseuse plus efficace
            evaluateur.setJeu(jeu);
            double  score =  evaluateur.evaluer(_jeu.getJoueurCourant());
            return new Pair<C, Double>(null, score);
        }
        C meilleurCoup = null;
        double meilleurScore = minmax * Double.NEGATIVE_INFINITY; //+Inf si on doit minimiser, -Inf si on doit maximiser
        List<C> coups = jeu.listerLesCoups();
        Collections.shuffle(coups);
        for(C coup : coups) {
            double score = minmaxRec (( ((JeuIA) jeu.getClone()).jouerLeCoup(coup)), //produit le jeu "fils"
                                        profondeur - 1, -minmax, alpha, beta)
                                .getValue();

            if (minmax == MIN) {
                if (alpha >= score) {
                    return new Pair<C, Double>(coup, score);
                }
                beta = (beta < score ? beta : score);
            }

            if (minmax == MAX) {
                if (score >= beta) {
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

    private Pair<C,Double> negamaxRec(JeuIA jeu, int profondeur, double alpha, double beta) {
        if(profondeur == 0 || jeu.estFini()) { //évaluation paresseuse plus efficace
            evaluateur.setJeu(jeu);
            double  score =  evaluateur.evaluer(_jeu.getJoueurCourant());
            return new Pair<C, Double>(null, score);
        }
        C meilleurCoup = null;
        double meilleurScore = Double.NEGATIVE_INFINITY;

        List<C> coups = jeu.listerLesCoups();
        Collections.shuffle(coups);
        for(C coup : coups) {
            double score =  - negamaxRec(((JeuIA) jeu.getClone()).jouerLeCoup(coup), profondeur - 1, -beta, -alpha).getValue();

            if (score >= alpha) {
                alpha = score ;
                meilleurCoup = coup ;
                if (alpha >= beta)
                    return new Pair<C, Double>(meilleurCoup, meilleurScore);
            }

            if(score > meilleurScore) {
                meilleurCoup = coup;
                meilleurScore = score;
            }

        }

        return new Pair<C, Double>(meilleurCoup, meilleurScore);
    }
}
