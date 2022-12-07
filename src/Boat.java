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
 * Type : public abstract class Boat
 * Obj : 
 * definir une class abstract Boat qui facilite l'instantiation des bateaux de 2 types : FishingBoat et Tank
 * initier les variables caracteristiques et les methodes necessaires, y compris la methode abstraite toString qui differe entre ces 2 types de bateaux
 */

public abstract class Boat {

    /* Variables definition */
    protected int x, y;
    private static int cpt = 0;

    /* Constructeur */
    public Boat(int x, int y) {
        this.x = x;
        this.y = y;
        cpt++;
    }

    /* Accesseurs */
    public int getX() { return x; }
    public int getY() { return y; }
    public static int getCpt() { return cpt; }

    /* Methods */
    public abstract String toString();
    public void seDeplacer(int xnew, int ynew) {
        x = xnew;
        y = ynew;
    }
}