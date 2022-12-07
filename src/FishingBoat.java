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
 * Type : public class FishingBoat extends Boat
 */

public class FishingBoat extends Boat {

    /* Variables definition */
    private int capacite;
    private static int cpt = 0;
    public final String id;

    /* Constructeur */
    public FishingBoat(int x, int y, int capacite) {
        super(x, y);
        cpt++;
        this.capacite = capacite;
        id = "Fishing Boat no."+cpt;
    }
    public FishingBoat(int x, int y) {
        this(x, y, (int)Math.floor(Math.random()*200));
    }

    /* Accesseurs */
    public static int getCpt() { return cpt; }
    public int getCapacite() { return capacite; }
    public void setCapacite(int c) { capacite = c; }

    /* Methods */
    public String toString() {
        return id+" - Capacite : "+capacite+", Position : ["+x+", "+y+"]";
    }
    public FishingBoat clone() { return new FishingBoat(x, y, capacite); }
}
