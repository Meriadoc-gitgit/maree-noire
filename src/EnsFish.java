/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221
 * Groupe : 7
 * 
 * 
 * 
 * =====================
 * Information de class 
 * =====================
 * Type : public class EnsFish extends ArrayList<Fish> implements Animaux
 */

import java.util.ArrayList;

public class EnsFish extends ArrayList<Fish> implements Animaux {

    /* Constant variable */
    public static final int RAND_MAX = 32676;
    public static final double p_reproduce_fish = 0.15;

    /* Methods */
    public EnsFish clone() {
        EnsFish tmp = new EnsFish();
        for (Fish p : this) 
            tmp.add(p.clone());
        return tmp;
    }
    
    @Override
    public void bouger(Terrain t) {
        for (Fish f : this) 
            f.seDeplacer(
                (int)Math.floor(Math.random()*t.nbLignes), 
                (int)Math.floor(Math.random()*t.nbColonnes));
    }
    public void reproduce() {
        EnsFish tmp = this.clone();
        int choice = (int)Math.floor(Math.random()*2);
        for (int i=0;i<tmp.size();i++) {
            if (choice==1) {
                this.add(new Fish(
                    tmp.get(i).x, tmp.get(i).y, tmp.get(i).energie, tmp.get(i).species, tmp.get(i).poids
                ));
                tmp.get(i).energie /= 2;
            }
        }
    }
    public boolean animal_en_XY(int x, int y) {
        for (Fish f : this) {
            if (f.x==x && f.y==y) 
                return true;
        }
        return false;
    }
    public String toString() {
        String s = "";
        for (Fish f : this) 
            s += f.toString()+"\n";   
        return s;
    }
}
