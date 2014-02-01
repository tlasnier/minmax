package fr.tlasnier.jeux.morpion.ihm;

import fr.tlasnier.jeux.morpion.modele.CoupMorpion;
import fr.tlasnier.jeux.morpion.modele.JeuMorpion;
import fr.tlasnier.jeux.morpion.modele.JoueurMorpion;
import fr.tlasnier.minmax.MinMax;

import java.util.Scanner;

/**
 * Created by Thibault on 25/01/14.
 */
public class MorpionMain {
    public static void main(String[] args) {
        JeuMorpion jeu = new JeuMorpion();
        MinMax<CoupMorpion, JoueurMorpion> minMax = new MinMax(jeu);
/*
        jeu.jouerLeCoup(new CoupMorpion(0,1));
        jeu.jouerLeCoup(new CoupMorpion(0,0));
        jeu.jouerLeCoup(new CoupMorpion(1,2));
        jeu.jouerLeCoup(new CoupMorpion(1,0));
*/
        CoupMorpion coup;
        while (!jeu.estFini()) {
            System.out.println(jeu);
            int i = 0, j = 0;
            Scanner clavier = new Scanner(System.in);
            i = clavier.nextInt();
            if (i != -1 ) { j = clavier.nextInt(); coup = new CoupMorpion(i,j);}
            else coup = minMax.minmax(9);//new CoupMorpion(i,j);
            jeu.jouerLeCoup(coup);
        }
        System.out.println(jeu);
    }
}
