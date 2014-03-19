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

    public CoupDamesChinoises(CoupDamesChinoises coup) {
        for (Pair<Integer, Integer> dep : coup.deplacements)
            deplacements.add(new Pair<Integer, Integer>(dep.getKey(), dep.getValue()));
    }

    public CoupDamesChinoises(int i_depart, int j_depart, int i_arrivee, int j_arrivee) {
        deplacements.add(new Pair<Integer,Integer>(i_depart,j_depart));
        deplacements.add(new Pair<Integer,Integer>(i_arrivee,j_arrivee));
    }

    public CoupDamesChinoises addDeplacement(int i, int j) {
        deplacements.add(new Pair<Integer, Integer>(i, j));
        return this;
    }

    public CoupDamesChinoises addDeplacement(Pair<Integer,Integer> dep) {
        deplacements.add(dep);
        return this;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (Pair<Integer,Integer> dep : deplacements)
            builder.append("[" + dep.getKey() + ":" + dep.getValue() + "], ");
        builder.delete(builder.length()-2, builder.length());
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int res = 13 * getI_depart() +  3 *getJ_depart() + 29 * getI_arrivee() + 51 * getJ_arrivee();
        res = res * (camp - 2);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoupDamesChinoises)) return false;

        CoupDamesChinoises that = (CoupDamesChinoises) o;

        if (camp != that.camp) return false;
        if (deplacements == null && that.deplacements != null || deplacements != null && that.deplacements == null) return false;
        if (!(getI_depart() == that.getI_depart() && getJ_depart() == that.getJ_depart() && getI_arrivee() == that.getI_arrivee() && getJ_arrivee() == that.getJ_arrivee() )) return false;

        return true;
    }
}
