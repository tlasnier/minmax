package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.jeux.dameschinoises.modele.exception.CaseInexistanteException;
import fr.tlasnier.jeux.dameschinoises.modele.exception.CaseOccupeeException;
import fr.tlasnier.jeux.dameschinoises.modele.exception.CaseVideException;
import fr.tlasnier.minmax.Clonable;
import fr.tlasnier.minmax.JeuIA;
import fr.tlasnier.minmax.Joueur;

import java.util.List;

/**
 * Created by Thibault on 16/03/14.
 */
public class JeuDamesChinoises implements JeuIA<CoupDamesChinoises, JoueurDamesChinoises> {
    private PlateauDamesChinoises plateau = new PlateauDamesChinoises();
    private JoueurDamesChinoises joueur1 = new JoueurDamesChinoises(DamesChinoises.ROUGE);
    private JoueurDamesChinoises joueur2 = new JoueurDamesChinoises(DamesChinoises.BLEU);
    private boolean joueurCourant = true;
    private CoupDamesChinoises dernierCoup;

    public JeuDamesChinoises() {}

    public JeuDamesChinoises(JeuDamesChinoises jeuOriginal) {
       plateau = (PlateauDamesChinoises) jeuOriginal.plateau.getClone();
       joueurCourant = jeuOriginal.joueurCourant;
       dernierCoup = jeuOriginal.dernierCoup;
    }

    @Override
    public JeuIA jouerLeCoup(CoupDamesChinoises coup) {
        try {
            //vérifications
            int i1 = coup.getI_depart(), i2 = coup.getI_arrivee(), j1 = coup.getJ_depart(), j2 = coup.getJ_arrivee();
            plateau.verifierExistence(i1, j1);
            plateau.verifierExistence(i2, j2);
            if (plateau.get(i1, j1) == DamesChinoises.VIDE)
                throw new CaseVideException("La case ("+ i1 + "," + j1 +") est vide!");
            if (plateau.get(i2, j2) != DamesChinoises.VIDE)
                throw new CaseOccupeeException("La case d'arrivée("+ i2 + "," + j2 +") est occupee!");
            if (plateau.get(i1, j1) != getJoueurCourant().getCamp())
                throw new CaseVideException("La case de départ ("+ i1 + "," + j1 +") ne vous appartient pas!");

            //jouer le coup
            coup.setCamp(getJoueurCourant().getCamp());
            plateau.set(i1, j1, DamesChinoises.VIDE);
            plateau.set(i2, j2, coup.getCamp());
            dernierCoup = coup;
            joueurCourant = !joueurCourant;
        } catch (CaseInexistanteException e) {
            System.out.println(e.getMessage());
        } catch (CaseVideException e) {
            System.out.println(e.getMessage());;
        } catch (CaseOccupeeException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    @Override
    public boolean estFini() {
        plateau.pionsPlaces(dernierCoup.getCamp());
        return false;
    }

    @Override
    public List<CoupDamesChinoises> listerLesCoups() {
        //TODO
        return null;
    }

    @Override
    public JoueurDamesChinoises getJoueurCourant() {
        if (joueurCourant)
            return joueur1;
        else
            return joueur2;
    }

    @Override
    public Clonable getClone() {
        return new JeuDamesChinoises(this);
    }

    @Override
    public double evaluer(Joueur joueur) {
        //TODO
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("C'est au joueur " + getJoueurCourant().getCamp() + " de jouer ! \n");
        builder.append(plateau + "\n######################################\n\n");
        return builder.toString();
    }
}
