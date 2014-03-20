package fr.tlasnier.jeux.puissance4.modele;

import fr.tlasnier.minmax.Evaluateur;

/**
 * Created by Thibault on 20/03/14.
 */
public class EvaluateurPuissance4 implements Evaluateur<JeuPuissance4, JoueurPuissance4> {
    private JeuPuissance4 jeu;

    @Override
    public double evaluer(JoueurPuissance4 joueur) {
        if (jeu.partieGagnee())
            if (jeu.getJoueurCourant().getCamp() == joueur.getCamp())  //joueur à évaluer a perdu la partie
                return - 100;
            else
                return 100;
        else
            return 0;
    }

    @Override
    public void setJeu(JeuPuissance4 jeuIA) {
        jeu = jeuIA;
    }

    @Override
    public JeuPuissance4 getJeu() {
        return jeu;
    }
}
