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
 * Type : public interface Animal
 * Obj : 
 * definir un interface pour stocker les actions des animaux dans l'ArrayList
 */

public interface Animaux {
    
    /* Methodes utiles */
    public void bouger(Terrain t);
    // public void reproduce(); car seule les poissons l'ont besoin
    public boolean animal_en_XY(int x, int y);
}
