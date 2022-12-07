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
 * Type : public class EnsBird extends ArrayList<Bird> implements Animaux
 */

import java.util.ArrayList;

public class EnsBird extends ArrayList<Bird> implements Animaux {

    /* Methods */
    public EnsBird clone() {
        EnsBird tmp = new EnsBird();
        for (Bird p : this) 
            tmp.add(p.clone());
        return tmp;
    }

    @Override
    public void bouger(Terrain t) {
        for (Bird b : this) 
            b.seDeplacer(
                (int)Math.floor(Math.random()*t.nbLignes), 
                (int)Math.floor(Math.random()*t.nbColonnes));
    }
    public boolean animal_en_XY(int x, int y) {
        for (Bird f : this) {
            if (f.x==x && f.y==y) 
                return true;
        }
        return false;
    }
    public String toString() {
        String s = "";
        for (Bird f : this) 
            s += f.toString()+"\n";   
        return s;
    }
}
