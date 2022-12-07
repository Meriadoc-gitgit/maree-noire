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
 * Type : public class Test
 * Obj : simuler des jeux de test
 */

/* Import require classes and packages */
import javax.swing.*;

public class Test {
    public static void main(String[] args) throws ExceptionsDepart, ExceptionsCapacite, ExceptionsPlacement {

        /* creer un tab pour stocker les donnees des animaux */
        int[] data = new int[30];
        
        /* Declaration de simulation et de bateaux */
        Simulation s1 = new Simulation(10, 20);
        FishingBoat fb1 = new FishingBoat(2, 12);
        FishingBoat fb2 = new FishingBoat(8, 12);
        FishingBoat fb3 = new FishingBoat(5, 10);
        FishingBoat fb4 = new FishingBoat(12, 8);

        Tank t1 = new Tank(14, 14, 0);
        Tank t2 = new Tank(15, 5);
        Tank t3 = new Tank(6, 8);
        Tank t4 = new Tank(3, 5);

        System.out.println(s1.toString());

        /* Ajout de bateaux */
        s1.ajoutBoat(fb1);
        s1.ajoutBoat(fb2);
        s1.ajoutBoat(fb3);
        s1.ajoutBoat(fb4);

        s1.ajoutBoat(t1);
        s1.ajoutBoat(t2);
        s1.ajoutBoat(t3);
        s1.ajoutBoat(t4);

        System.out.println(s1.toString());
        System.out.println();

        s1.rafraichir_fish();
        s1.rafraichir_monde();
        System.out.println(s1.toString());

        /* Test par tour de jeu */
        int som = 0, peche = 0;

        for (int i=0;i<30;i++) {
            System.out.print("========================");
            System.out.print("TOUR no."+i);
            System.out.print("========================\n");
            System.out.print("Situation :\n");

            s1.rafraichir_fish();
            s1.rafraichir_monde();

            s1.fishing();

            /* Exceptions */
            try {
                s1.tragedy();
            } catch (ExceptionsCapacite except) {
                System.out.println(except.getMessage());
                System.out.println("Tout va bien\n");
                // break;
            } catch (ExceptionsDepart depart) {
                System.out.println(depart.getMessage());
                // break;
            } 

            // System.out.println(s1.toString());
            s1.depart();

            /* Stock data */
            data[i] = s1.getBird().size() + s1.getFish().size();
            som += s1.getNbMort();
            peche += s1.getPeche();
        }
        
        System.out.println(s1.toString());

        System.out.println(som+" animaux a mort apres "+data.length+" jours dans l'espace de dimension "+s1.getDim());
        System.out.println(peche+" poissons peches apres "+data.length+" jours dans l'espace de dimension "+s1.getDim());



        /* Graph */
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Plot(data));
        frame.setSize(400,400);
        frame.setLocation(200,200);
        frame.setVisible(true);
    }
}
