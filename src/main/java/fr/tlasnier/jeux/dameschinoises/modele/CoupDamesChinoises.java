package fr.tlasnier.jeux.dameschinoises.modele;

import fr.tlasnier.minmax.Coup;

/**
 * Created by Thibault on 16/03/14.
 */
public class CoupDamesChinoises implements Coup {
    private int i_depart;
    private int j_depart;
    private int i_arrivee;
    private int j_arrivee;

    public CoupDamesChinoises(int i_depart, int j_depart, int i_arrivee, int j_arrivee) {
        this.i_depart = i_depart;
        this.j_depart = j_depart;
        this.i_arrivee = i_arrivee;
        this.j_arrivee = j_arrivee;
    }

    public int getI_depart() {
        return i_depart;
    }

    public int getJ_depart() {
        return j_depart;
    }

    public int getI_arrivee() {
        return i_arrivee;
    }

    public int getJ_arrivee() {
        return j_arrivee;
    }
}
