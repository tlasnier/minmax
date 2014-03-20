package fr.tlasnier.minmax;

/**
 * Created by Thibault on 20/03/14.
 */
public interface Evaluateur<Jeu extends JeuIA, TJoueur extends Joueur> {
    public double evaluer(TJoueur joueur);

    public void setJeu(Jeu jeuIA);

    public Jeu getJeu();

}
