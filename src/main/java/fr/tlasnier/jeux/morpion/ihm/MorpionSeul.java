package fr.tlasnier.jeux.morpion.ihm;

import fr.tlasnier.jeux.morpion.modele.CoupMorpion;
import fr.tlasnier.jeux.morpion.modele.EvaluateurMorpion;
import fr.tlasnier.jeux.morpion.modele.JeuMorpion;
import fr.tlasnier.jeux.morpion.modele.JoueurMorpion;
import fr.tlasnier.minmax.MinMax;

/**
 * Created by Thibault on 25/01/14.
 */
public class MorpionSeul {
    public static void main(String[] args) {
        JeuMorpion jeu = new JeuMorpion();
        MinMax<CoupMorpion, JoueurMorpion> minMax = new MinMax(jeu, new EvaluateurMorpion());
        CoupMorpion coup;
        while (!jeu.estFini()) {
            System.out.println(jeu);
            coup = minMax.minmax(9);
            jeu.jouerLeCoup(coup);
        }
        System.out.println(jeu);
    }
}
