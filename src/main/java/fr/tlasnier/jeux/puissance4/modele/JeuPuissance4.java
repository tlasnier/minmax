package fr.tlasnier.jeux.puissance4.modele;

import fr.tlasnier.jeux.puissance4.modele.exception.CaseDejaJoueeException;
import fr.tlasnier.jeux.puissance4.modele.exception.CaseInexistanteException;
import fr.tlasnier.jeux.puissance4.modele.exception.ColonnePleineException;
import fr.tlasnier.minmax.Clonable;
import fr.tlasnier.minmax.JeuIA;
import fr.tlasnier.minmax.Joueur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibault on 07/02/14.
 */
public class JeuPuissance4 implements JeuIA<CoupPuissance4, JoueurPuissance4> {
    private PlateauPuissance4 plateau = new PlateauPuissance4();
    private JoueurPuissance4 joueur1 = new JoueurPuissance4(Puissance4.ROUGE);
    private JoueurPuissance4 joueur2 = new JoueurPuissance4(Puissance4.JAUNE);
    private CoupPuissance4 dernierCoup;
    private boolean joueurCourant = true; //vrai si le joueur 1 est le joueur courant

    public JeuPuissance4() {}

    public JeuPuissance4(JeuPuissance4 jeuPuissance4) {
        plateau = (PlateauPuissance4) jeuPuissance4.plateau.getClone();
        dernierCoup = jeuPuissance4.dernierCoup;
        joueurCourant = jeuPuissance4.joueurCourant;
    }

    @Override
    public JeuIA jouerLeCoup(CoupPuissance4 coup) {

        try {
            coup.setLigne(plateau.getLigneVide(coup.getColonne()));
            plateau.set(coup.getLigne(), coup.getColonne(), getJoueurCourant().getCamp());
            dernierCoup = coup;
            joueurCourant = !joueurCourant;
        } catch (ColonnePleineException e) {
            System.out.println(e.getMessage());
        } catch (CaseDejaJoueeException e) {
            System.out.println(e.getMessage());
        } catch (CaseInexistanteException e) {
            System.out.println(e.getMessage());
        }

        return this;
    }

    @Override
    public boolean estFini() {
        //début de partie, aucun coup n'a été joué
        if (dernierCoup == null)
            return false;
        return partieGagnee(dernierCoup) || plateau.estPlein();
    }

    private boolean partieGagnee(CoupPuissance4 dernierCoup) {
        int ligne = dernierCoup.getLigne();
        int colonne = dernierCoup.getColonne();
        int camp = Puissance4.EMPTY;

        try {
            camp = plateau.get(ligne, colonne);
        } catch (CaseInexistanteException e) {
            e.printStackTrace();
            System.out.println("Problème interne : impossible d'accéder à la case voulue.");
        }

        //checker la ligne :
        int jetonsAlignes = 0;
        for (int j = colonne - 3; j <= colonne + 3; j++) {
            try {
                if (plateau.get(ligne, j) == camp)
                    jetonsAlignes ++;
                else
                    jetonsAlignes = 0;
            } catch (CaseInexistanteException e) {
                //On essaye d'accéder à une case qui n'existe pas. Il ne faut rien faire
            }
            finally {
                if (jetonsAlignes == 4)
                    return true;
            }
        }

        //checker la colonne :
        jetonsAlignes = 0;
        for (int i = ligne - 3; i <= ligne; i++) {
            try {
                if (plateau.get(i, colonne) == camp)
                    jetonsAlignes ++;
                else
                    jetonsAlignes = 0;
            } catch (CaseInexistanteException e) {
                //On essaye d'accéder à une case qui n'existe pas. Il ne faut rien faire
            }
            finally {
                if (jetonsAlignes == 4)
                    return true;
            }
        }

        //checker une diagonale :
        jetonsAlignes = 0;
        for (int j = colonne - 3; j <= colonne + 3; j++) {
            try {
                if (plateau.get(ligne - (colonne - j), j) == camp)
                    jetonsAlignes ++;
                else
                    jetonsAlignes = 0;
            } catch (CaseInexistanteException e) {
                //On essaye d'accéder à une case qui n'existe pas. Il ne faut rien faire
            }
            finally {
                if (jetonsAlignes == 4)
                    return true;
            }
        }

        //checker l'autre diagonale :
        jetonsAlignes = 0;
        for (int j = colonne - 3; j <= colonne + 3; j++) {
            try {
                if (plateau.get(ligne + (colonne - j), j) == camp)
                    jetonsAlignes ++;
                else
                    jetonsAlignes = 0;
            } catch (CaseInexistanteException e) {
                //On essaye d'accéder à une case qui n'existe pas. Il ne faut rien faire
            }
            finally {
                if (jetonsAlignes == 4)
                    return true;
            }
        }

        return false;
    }

    @Override
    public List<CoupPuissance4> listerLesCoups() {
        List<CoupPuissance4> coups = new ArrayList<CoupPuissance4>();

        for (int j = 0; j < Puissance4.NB_COLONNES; j++) {
            try {
                CoupPuissance4 coup = new CoupPuissance4(j);
                coup.setLigne(plateau.getLigneVide(j));
                coups.add(coup);
            } catch (ColonnePleineException e) {
                //Ne pas ajouter le coup dans la liste
            }
        }

        return coups;
    }

    @Override
    public JoueurPuissance4 getJoueurCourant() {
        return (joueurCourant?joueur1:joueur2);
    }

    @Override
    public Clonable getClone() {
        return new JeuPuissance4(this);
    }

    @Override
    public double evaluer(Joueur joueur) {
        //int camp = joueur.getCamp();
        //int compteur2_j1 = 0, compteur3_j1 = 0,  compteur4_j1 = 0, compteur2_j2 = 0, compteur3_j2 = 0,  compteur4_j2 = 0;

        //compteur de 2 jetons alignés
        //compteur de 3 jetons alignés
        //compteur de 4 jetons alignés
        if (dernierCoup == null) return 0;

        if (partieGagnee(dernierCoup))
            if (getJoueurCourant().getCamp() == joueur.getCamp())  //joueur à évaluer a perdu la partie
                return - 100;
            else
                return 100;
        else
            return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("C'est au joueur " + getJoueurCourant().getCamp() + " de jouer ! \n");
        builder.append(plateau + "\n######################################\n\n");
        return builder.toString();
    }
}
