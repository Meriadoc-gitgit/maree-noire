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
 * Type : public class Tank extends Boat
 */

public class Tank extends Boat {
    
    /* variables definition */
    public static final int minCapa = 50;
    public static final int maxCapa = 300;

    protected double capacite;
    private static int cpt = 0;
    public final String id;

    /* Constructeur */
    public Tank(int x, int y, double capacite) {
        super(x, y);
        this.capacite = capacite;
        cpt++;
        id = "TANK no."+cpt;
    }
    public Tank(int x, int y) {
        this(x, y, (double)Math.floor(Math.random()*maxCapa+minCapa)); 
    }

    /* Accesseurs */
    public static int getCpt() { return cpt; }
    public double getCapacite() { return capacite; }

    /* Methods */
    public String toString() {
        return id+" - Capacite : "+capacite+", Position : ["+x+", "+y+"]";
    }
    public Tank clone() { return new Tank(x, y, capacite); }
}
