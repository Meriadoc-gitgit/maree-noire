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
 * Type : public class ExceptionsCapacite extends Exception
 * Obj : definir l'Exception a ratrapper au cas ou la capacite des conteneurs baisseraient en dessous de sa capacite minimale (soit 50)
 */

public class ExceptionsCapacite extends Exception {
    public ExceptionsCapacite() {
        super("La capacite de ce tank est en dessous de sa capacite minimal");
    }
}
