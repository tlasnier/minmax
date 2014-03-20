package fr.tlasnier.jeux.dameschinoises.ihm;

import fr.tlasnier.jeux.dameschinoises.modele.CoupDamesChinoises;
import fr.tlasnier.jeux.dameschinoises.modele.JeuDamesChinoises;
import fr.tlasnier.jeux.dameschinoises.modele.JoueurDamesChinoises;
import fr.tlasnier.minmax.MinMax;

import java.util.Scanner;

/**
 * Created by Thibault on 16/03/14.
 */
public class DamesChinoisesMain {
    public static void main(String[] args) {
        JeuDamesChinoises jeu = new JeuDamesChinoises();
        MinMax<CoupDamesChinoises, JoueurDamesChinoises> minMax = new MinMax(jeu);

        CoupDamesChinoises coup = null;
        while (!jeu.estFini()) {
            System.out.println(jeu);
            //System.out.println("Coups possibles : " + jeu.listerLesCoups());
            int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
            Scanner clavier = new Scanner(System.in);
            i1 = clavier.nextInt();
            if (i1 != -1 ) {
                j1 = clavier.nextInt();
                i2 = clavier.nextInt();
                j2 = clavier.nextInt();
                coup = new CoupDamesChinoises(i1,j1,i2,j2);
                i1 = clavier.nextInt();
                while (i1 != -1) {
                    j1 = clavier.nextInt();
                    coup.addDeplacement(i1, j1);
                    i1 = clavier.nextInt();
                }
            }
            else {
                long time = System.currentTimeMillis();
                coup = minMax.minmax(5);
                System.out.println("Coup joué : " + coup + ", en " + ((System.currentTimeMillis()-time)/1000.0) +"s");
            }
            jeu.jouerLeCoup(coup);
        }
        System.out.println(jeu);
    }
}
