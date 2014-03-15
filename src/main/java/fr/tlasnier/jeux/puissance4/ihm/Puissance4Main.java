package fr.tlasnier.jeux.puissance4.ihm;

import fr.tlasnier.jeux.puissance4.modele.CoupPuissance4;
import fr.tlasnier.jeux.puissance4.modele.JeuPuissance4;
import fr.tlasnier.jeux.puissance4.modele.JoueurPuissance4;
import fr.tlasnier.minmax.MinMax;

import java.util.Scanner;

/**
 * Created by Thibault on 25/01/14.
 */
public class Puissance4Main {
    public static void main(String[] args) {
        JeuPuissance4 jeu = new JeuPuissance4();
        MinMax<CoupPuissance4, JoueurPuissance4> minMax = new MinMax(jeu);

        CoupPuissance4 coup;
        while (!jeu.estFini()) {
            System.out.println(jeu);
            int j = 0;
            Scanner clavier = new Scanner(System.in);
            j = clavier.nextInt();

            if (j != -1 ) coup = new CoupPuissance4(j);
            else {
                long time = System.currentTimeMillis();
                coup = minMax.minmax(12);
                System.out.println("Coup joué : " + coup.getColonne() + ", en " + ((System.currentTimeMillis()-time)/1000) +"s");
            }

            jeu.jouerLeCoup(coup);
        }
        System.out.println(jeu);
    }
}
