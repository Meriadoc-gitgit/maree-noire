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
 * Type : public class Fish extends Animal
 */

public class Fish extends Animal {

    /* variables definition */
    protected double poids; 

    /* Constructeur */
    public Fish(int x, int y, double energie, String species, double poids) {
        super(x, y, energie, species);
        this.poids = poids;
    }

    /* Accesseurs */
    public double getPoids() { return poids; }

    /* Methods clone() */
    public Fish clone() { return new Fish(x, y, energie, species, poids); }
}
