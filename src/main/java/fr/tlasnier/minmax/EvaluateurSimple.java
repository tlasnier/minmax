package fr.tlasnier.minmax;

/**
 * Created by Thibault on 20/03/14.
 */
public class EvaluateurSimple implements Evaluateur<JeuIA, Joueur> {
    private JeuIA jeu;

    @Override
    public double evaluer(Joueur joueur) {
        if (jeu.estFini())
            if (jeu.getJoueurCourant().getCamp() == joueur.getCamp())  //joueur à évaluer a perdu la partie
                return - 100;
            else
                return 100;
        else
            return 0;
    }

    @Override
    public void setJeu(JeuIA jeuIA) {
        jeu = jeuIA;
    }

    @Override
    public JeuIA getJeu() {
        return jeu;
    }
}
