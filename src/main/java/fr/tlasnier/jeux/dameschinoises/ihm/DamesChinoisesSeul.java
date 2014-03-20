package fr.tlasnier.jeux.dameschinoises.ihm;

import fr.tlasnier.jeux.dameschinoises.modele.CoupDamesChinoises;
import fr.tlasnier.jeux.dameschinoises.modele.EvaluateurDamesChinoises;
import fr.tlasnier.jeux.dameschinoises.modele.JeuDamesChinoises;
import fr.tlasnier.jeux.dameschinoises.modele.JoueurDamesChinoises;
import fr.tlasnier.minmax.MinMax;

/**
 * Created by Thibault on 16/03/14.
 */
public class DamesChinoisesSeul {
    public static void main(String[] args) {
        JeuDamesChinoises jeu = new JeuDamesChinoises();
        MinMax<CoupDamesChinoises, JoueurDamesChinoises> minMax = new MinMax(jeu, new EvaluateurDamesChinoises());

        CoupDamesChinoises coup = null;
        while (!jeu.estFini()) {
            System.out.println(jeu);
            coup = minMax.minmax(5);
            jeu.jouerLeCoup(coup);
        }
        System.out.println(jeu);
    }
}
