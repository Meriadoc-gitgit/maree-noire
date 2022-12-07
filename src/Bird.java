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
 * Type : public class Bird extends Animal
 */
public class Bird extends Animal {

    /* Constructeur */
    public Bird(int x, int y, double energie, String species) {
        super(x, y, energie, species);
    }

    /* Methods clone() */
    public Bird clone() { return new Bird(x, y, energie, species); }
}