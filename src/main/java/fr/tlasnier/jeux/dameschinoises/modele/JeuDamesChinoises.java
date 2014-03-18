package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.jeux.dameschinoises.modele.exception.*;
import fr.tlasnier.minmax.Clonable;
import fr.tlasnier.minmax.JeuIA;
import fr.tlasnier.minmax.Joueur;
import fr.tlasnier.minmax.Pair;

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
            verifierDeplacements(coup);

            //jouer le coup
            coup.setCamp(getJoueurCourant().getCamp());
            plateau.set(i1, j1, DamesChinoises.VIDE);
            plateau.set(i2, j2, coup.getCamp());
            dernierCoup = coup;
            joueurCourant = !joueurCourant;
        } catch (CaseInexistanteException e) {
            System.out.println(e.getMessage());
        } catch (CaseVideException e) {
            System.out.println(e.getMessage());
        } catch (CaseOccupeeException e) {
            System.out.println(e.getMessage());
        } catch (MauvaisJoueurException e) {
            System.out.println(e.getMessage());
        } catch (DeplacementNonAutoriseException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    private void verifierDeplacements(CoupDamesChinoises coup) throws CaseInexistanteException, CaseVideException, CaseOccupeeException, MauvaisJoueurException, DeplacementNonAutoriseException {
        int i1 = coup.getI_depart(), i2 = coup.getI_arrivee(), j1 = coup.getJ_depart(), j2 = coup.getJ_arrivee();

        if (plateau.get(i1, j1) == DamesChinoises.VIDE)
            throw new CaseVideException("La case ("+ i1 + "," + j1 +") est vide!");
        if (plateau.get(i2, j2) != DamesChinoises.VIDE)
            throw new CaseOccupeeException("La case d'arrivée("+ i2 + "," + j2 +") est occupee!");
        if (plateau.get(i1, j1) != getJoueurCourant().getCamp())
            throw new MauvaisJoueurException("La case de départ ("+ i1 + "," + j1 +") ne vous appartient pas!");

        //TODO vérifier chaque déplacement
        boolean first = true;
        boolean second = true; //second deplacement n'est pas encore passé
        boolean firstDirect = false;
        boolean firstIndirect = false;
        Pair<Integer,Integer> depPrec = null; //deplacement précédent
        for (Pair<Integer,Integer> dep : coup.getDeplacements()) {
            if (first)
                first = false;
            else if (second) {
                second = false;
                if (plateau.estVoisinDirect(depPrec.getKey(), depPrec.getValue(), dep.getKey(), dep.getValue()))
                    firstDirect = true;
                else if (plateau.estVoisinSaute(depPrec.getKey(), depPrec.getValue(), dep.getKey(), dep.getValue()))
                    firstIndirect = true;
                else
                    throw new DeplacementNonAutoriseException("Ce n'est pas un déplacement valide");
            }
            else {
                if (firstDirect)
                    throw new DeplacementNonAutoriseException("Si le premier déplacement ne saute pas de pion, il ne peut pas y en avoir d'autre!");
                else if (!plateau.estVoisinSaute(depPrec.getKey(), depPrec.getValue(), dep.getKey(), dep.getValue()))
                    throw new DeplacementNonAutoriseException("Si vous avez commencé une séquence de plusieurs mouvements, tous les mouvements doivent être valides!");
            }
            //dans tous les cas:
            depPrec = dep;
        }

    }

    @Override
    public boolean estFini() {
        return dernierCoup != null && plateau.pionsPlaces(dernierCoup.getCamp());
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
