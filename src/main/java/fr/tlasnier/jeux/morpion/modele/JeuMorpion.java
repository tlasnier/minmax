package fr.tlasnier.jeux.morpion.modele;

import fr.tlasnier.jeux.morpion.modele.exception.CaseDejaJoueeException;
import fr.tlasnier.jeux.morpion.modele.exception.CaseInexistanteException;
import fr.tlasnier.minmax.Clonable;
import fr.tlasnier.minmax.JeuIA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibault on 24/01/14.
 */
public class JeuMorpion implements JeuIA<CoupMorpion, JoueurMorpion> {
    private PlateauMorpion plateau;
    private JoueurMorpion joueur1 = new JoueurMorpion(Morpion.X);
    private JoueurMorpion joueur2 = new JoueurMorpion(Morpion.O);
    private CoupMorpion dernierCoup;
    private boolean joueurCourant; //vrai si joueur 1 est le joueur courant.

    public JeuMorpion() {
        plateau = new PlateauMorpion();
        joueurCourant = true;
    }

    public JeuMorpion(JeuMorpion jeuMorpion) {
        plateau = (PlateauMorpion)jeuMorpion.plateau.getClone();
        dernierCoup = jeuMorpion.dernierCoup;
        joueurCourant = jeuMorpion.joueurCourant;
    }

    @Override
    public JeuIA jouerLeCoup(CoupMorpion coup) {
        try {
            plateau.set(coup.getI(), coup.getJ(), getJoueurCourant().getCamp());
            dernierCoup = coup;
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
        if (getDernierCoup() == null)
            return false;
        return  plateau.estPlein() ||
                plateau.diagonaleGagnee(getDernierCoup()) ||
                plateau.ligneGagnee(getDernierCoup()) ||
                plateau.colonneGagnee(getDernierCoup());
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

    public PlateauMorpion getPlateau() {
        return plateau;
    }

    @Override
    public JoueurMorpion getJoueurCourant() {
        return (joueurCourant? joueur1 : joueur2);
    }

    public CoupMorpion getDernierCoup() {
        return dernierCoup;
    }

    @Override
    public Clonable getClone() {
        return new JeuMorpion(this);
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
