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
 * Type : public class Animal
 * Obj : 
 * definir l'instance de Animal
 * initier les methodes necessaires pour les poissons et oiseaux
 * initier les accesseurs caracteristiques
 */
public class Animal {

    /* variables definition */
    protected int x, y;
    protected double energie;
    protected String species;
    protected static int cpt = 0;

    /* Constructeur */
    public Animal(int x, int y, double energie, String species) {
        this.x = x;
        this.y = y;
        this.energie = energie;
        this.species = species;
        cpt++;
    }
    /* Accesseurs */
    public int getX() { return x; }
    public int getY() { return y; }
    public double getEnergie() { return energie; }
    public void setEnergie(double e) { energie = e; }
    public String getSpecies() { return species; }
    public static int getCpt() { return cpt; }

    /* Methods utiles */
    public void seDeplacer(int xnew, int ynew) {
        x = xnew;
        y = ynew;
    }
    public String toString() {
        return species+" - Position : ["+x+", "+y+"]";
    }
}