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
 * Type : public class ExceptionsPlacement extends Exception
 * Obj : definir l'Exception a ratrapper au cas ou les bateaux sont hors de la zone etudiee
 */

public class ExceptionsPlacement extends Exception {
    public ExceptionsPlacement() {
        super("Bateau hors du terrain");
    }
}
