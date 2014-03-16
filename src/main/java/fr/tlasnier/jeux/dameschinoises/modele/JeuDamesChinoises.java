package fr.tlasnier.jeux.dameschinoises.modele;

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
        //TODO
        return null;
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
}
