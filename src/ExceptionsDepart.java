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
 * Type : public class ExceptionsDepart extends Exception
 * Obj : definir l'Exception a ratrapper au cas ou le tank resterait encore au port et n'aurait pas parti
 */

public class ExceptionsDepart extends Exception {
    public ExceptionsDepart() {
        super("Bateau reste encore au port");
    }
}
