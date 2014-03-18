package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.minmax.Coup;
import fr.tlasnier.minmax.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibault on 16/03/14.
 */
public class CoupDamesChinoises implements Coup {
    private int camp;
    private List<Pair<Integer,Integer>> deplacements = new ArrayList<Pair<Integer, Integer>>();

    public CoupDamesChinoises(int i_depart, int j_depart, int i_arrivee, int j_arrivee) {
        deplacements.add(new Pair<Integer,Integer>(i_depart,j_depart));
        deplacements.add(new Pair<Integer,Integer>(i_arrivee,j_arrivee));
    }

    public void addDeplacement(int i, int j) {
        deplacements.add(new Pair<Integer, Integer>(i, j));
    }

    public List<Pair<Integer,Integer>> getDeplacements() {
        return deplacements;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int c) {
        camp = c;
    }

    public int getI_depart() {
        return deplacements.get(0).getKey();
    }

    public int getJ_depart() {
        return deplacements.get(0).getValue();
    }

    public int getI_arrivee() {
        return deplacements.get(deplacements.size()-1).getKey();
    }

    public int getJ_arrivee() {
        return deplacements.get(deplacements.size()-1).getValue();
    }
}
