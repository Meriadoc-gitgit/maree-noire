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
 * Type : public class Simulation
 * Obj : 
 * simuler l'environnement etudie
 * stocker les actions necessaires de l'ensemble des elements sur le Terrain, tels que les Ressources et les Agents
 */

import java.util.ArrayList;

public class Simulation {
    
    /* constant variables definition */
    public static final int temp_repousse_algue = -15;
    public static final int temp_repousse_corail = -5;
    private int nbAnimauxMort, Peche;

    /* variables definition */
    private Terrain ter;
    private Ressource[] res;
    private EnsBird bird = new EnsBird();
    private EnsFish fish = new EnsFish();
    private ArrayList<FishingBoat> fboat = new ArrayList<FishingBoat>();
    private ArrayList<Tank> tank = new ArrayList<Tank>();

    /* Tableau de Ressource */
    private String[] elements = {"Algues", "Coraux", "Rocher"};
    private String[] fishSpecies = {"Thon", "Saumon", "Capelan", "Maquereau", "Anguille", "Gaspareau"}; 
    private String[] birdSpecies = {"Fregate", "Mouette", "Pelican", "Phaethon", "Cormorant", "Petrel", "Manchot", "Fou"}; 

    /* Constructeur */
    public Simulation(int m, int n) {
        
        /* Terrain ter */
        this.ter = new Terrain();

        /* Initialisation de m ressources */
        this.res = new Ressource[n]; 
        for (int i=0;i<res.length;i++) {
            res[i] = new Ressource(
                elements[(int)Math.floor(Math.random()*elements.length)],
                (int)Math.floor(Math.random()*100)
            );

            ter.setCase(
                (int)Math.floor(Math.random()*ter.nbLignes),
                (int)Math.floor(Math.random()*ter.nbColonnes),
                res[i]
            );
        }

        /* Initialisation des animaux (m poissons et m oiseaux) */
        for (int i=0;i<m;i++) 
            fish.add(new Fish(
                (int)Math.floor(Math.random()*ter.nbLignes),
                (int)Math.floor(Math.random()*ter.nbColonnes),
                (double)Math.floor(Math.random()*100),
                fishSpecies[(int)Math.floor(Math.random()*fishSpecies.length)],
                (double)Math.floor(Math.random()*10)));
        
        for (int i=0;i<m*10;i++) 
            bird.add(new Bird(
                (int)Math.floor(Math.random()*ter.nbLignes),
                (int)Math.floor(Math.random()*ter.nbColonnes),
                (double)Math.floor(Math.random()*100),
                birdSpecies[(int)Math.floor(Math.random()*birdSpecies.length)]));
    }

    /* Accesseurs */
    public EnsBird getBird() { return bird; }
    public EnsFish getFish() { return fish; }

    /* Methods */

    /* Ajout de bateaux */
    public void ajoutBoat(Boat b) throws ExceptionsPlacement {
        if (b.x>=ter.nbLignes || b.y>=ter.nbColonnes) {
            b.seDeplacer(0, 0);
            throw new ExceptionsPlacement();
        }
        else {
            if (b instanceof FishingBoat)
                fboat.add((FishingBoat)b);
            else 
                tank.add((Tank)b);
        }
    }

    /* Methods pour rafraichir les poissons
     * step 1 : faire bouger les poissons
     * step 2 : baisser l'energie des poissons, au cas ou il y a des algues dans le meme case que ces poissons, leurs energies sera augmente d'un nombre d'algue, et la quantite d'algue sera initialise a temp_repousse_algue
     * step 3 : faire reproduire les poissons
     */
    public void rafraichir_fish() {

        /* bouger les poissons */
        fish.bouger(ter);

        /* Parcourir */
        for (Fish f : fish) {
            if (!ter.caseEstVide(f.x, f.y) && ter.getCase(f.x, f.y).getQuantite()>0 && ter.getCase(f.x, f.y).type=="Algues") {
                f.setEnergie(f.energie + ter.getCase(f.x,f.y).getQuantite());
                ter.getCase(f.x,f.y).setQuantite(Simulation.temp_repousse_algue);
            }
            else f.setEnergie(f.energie-1);

            if (f.energie<0) {
                f.setEnergie(-100);
            }
        }

        /* Refresh proie (remove null) */
        int s = 0;
        EnsFish tmp = fish.clone();
        for (Fish p : tmp) {
            if (p.energie<0) {
                fish.remove(s);
                s--;
            }
            s++;
        }

        /* Reproduce */
        fish.reproduce();
    }


    /* Methods (pour les ressources)
     * augmenter la quantite des ressources a chaque appel a cette fonction
     */
    public void rafraichir_monde() {
        for (int i=0;i<ter.nbLignes;i++) {
            for (int j=0;i<ter.nbColonnes;i++) {
                if (ter.getCase(i,j)!=null) {
                    if (ter.getCase(i,j).type == "Algues" || ter.getCase(i,j).type == "Coraux") {
                        ter.getCase(i,j).setQuantite(
                            ter.getCase(i,j).getQuantite()+1
                        );
                    }
                    else continue;
                }
                else continue;
            }
        }
    }

    public void setPeche(int n) { Peche = n; }
    public int getPeche() { return Peche; }

    /* Methods pour les bateaux
     * Si la quantite des bateaux baisse jusqu'a 0, les bateaux retournent au port (0,0)
     */
    public void fishing() {
        int s = 0;
        // String[] speciesEarn = new String[6];

        int peche = 0;
        for (FishingBoat b : fboat) {
            /* Au cas ou la quantite des bateaux baisse jusqu'a 0 */
            if (b.getCapacite()<=0) 
                b.seDeplacer(0, 0);

            else {
                if (fish.animal_en_XY(b.x, b.y)) {
                    for (Fish f : fish) {
                        if (f.x==b.x && f.y==b.y) {
                            // speciesEarn+=" "+f.species+" ";
                            f.setEnergie(-100);
    
                            s++;
                        }
                    }
                    System.out.println(b.toString()+" a peche "+s+" poissons");
                    b.setCapacite(b.getCapacite()-s);
    
                    peche += s;
                    /* Reset */
                    s = 0;
                    // speciesEarn = "";
                }
                else b.seDeplacer((int)Math.floor(Math.random()*ter.nbLignes),(int)Math.floor(Math.random()*ter.nbColonnes));
            }
        }

        /* Enlever les poissons deja peches de l'ArrayList */
        int count = 0;
        EnsFish tmp = fish.clone();
        for (Fish p : tmp) {
            if (p.energie<0) {
                fish.remove(count);
                count--;
            }
            count++;
        }        
        this.setPeche(peche);
    }

    /* Nombre d'animaux affectes lors de la maree noire */
    public void setNbAnimauxAffecte(int nb) {
        nbAnimauxMort = nb;
    }
    public int getNbMort() { return nbAnimauxMort; }
    public String getDim() { return "["+ter.nbLignes+","+ter.nbColonnes+"]"; }

    /* La Marée noire */
    public void tragedy() throws ExceptionsCapacite, ExceptionsDepart {
        
        int choice = (int)Math.floor(Math.random()*2);
        
        if (choice!=0) {
            int numb = (int)Math.floor(Math.random()*tank.size());

            /* Exceptions */
            if (tank.get(numb).capacite<=50) {
                throw new ExceptionsCapacite();
            }
            else if (tank.get(numb).x==0 && tank.get(numb).y==0) {
                throw new ExceptionsDepart();
            }

            else {
                tank.get(numb).capacite/=2;
                System.out.println("Le navire "+tank.get(numb).toString()+" a eu un déversement d'hydrocarbures pendant son transit!");

                /* Consequence */
                int tmpx = tank.get(numb).x, tmpy = tank.get(numb).y;
                int nbFishMort = 0, nbBirdMort = 0;
                
                /* Ramene au port */
                tank.get(numb).seDeplacer(0, 0);

                for (FishingBoat b : fboat) {
                    if (b.x==tmpx && b.y==tmpy) 
                        b.seDeplacer(0, 0);
                }

                if (fish.animal_en_XY(tmpx, tmpy)) {
                    for (Fish f : fish) {
                        if (f.x==tmpx && f.y==tmpy) {
                            f.setEnergie(-100);;
                            nbFishMort++;
                        }
                    }
                }
                if (bird.animal_en_XY(tmpx, tmpy)) {
                    for (Bird f : bird) {
                        if (f.x==tmpx && f.y==tmpy) {
                            f.setEnergie(-100);
                            nbBirdMort++;
                        }
                    }
                }
                if (!ter.caseEstVide(tmpx, tmpy) && ter.getCase(tmpx, tmpy).getQuantite()>0 && ter.getCase(tmpx, tmpy).type=="Algues") 
                    ter.getCase(tmpx, tmpy).setQuantite(Simulation.temp_repousse_algue);
                
                else if (!ter.caseEstVide(tmpx, tmpy) && ter.getCase(tmpx, tmpy).getQuantite()>0 && ter.getCase(tmpx, tmpy).type=="Coraux") 
                    ter.getCase(tmpx, tmpy).setQuantite(Simulation.temp_repousse_corail);


                if (nbBirdMort==0 && nbFishMort!=0) 
                    System.out.println("Ce deversement a cause la mort de "+nbFishMort+" poissons, pas d'oiseaux sur mer et a detruit une grande espace maritime !");
                else if (nbBirdMort!=0 && nbFishMort==0) 
                    System.out.println("Ce deversement a cause la mort d'aucun poisson, "+ nbBirdMort+" oiseaux sur mer et a detruit une grande espace maritime !");
                else if (nbBirdMort==0 && nbFishMort==0) 
                    System.out.println("Ce deversement n'a pas d'effets sur les animaux mais a detruit une grande espace maritime !");
                else 
                    System.out.println("Ce deversement a cause la mort de "+nbFishMort+" poissons, "+ nbBirdMort+" oiseaux sur mer et a detruit une grande espace maritime !");


                /* Enlever les animaux morts des ArrayList */

                int count = 0;
                EnsFish tmp = fish.clone();
                for (Fish p : tmp) {
                    if (p.energie<0) {
                        fish.remove(count);
                        count--;
                    }
                    count++;
                }
                
                int count2 = 0;
                EnsBird tmp2 = bird.clone();
                for (Bird p : tmp2) {
                    if (p.energie<0) {
                        bird.remove(count2);
                        count2--;
                    }
                    count2++;
                }
                System.out.println(tank.get(numb).toString()+" est ramene urgemment au port par l'equipe securite");

                this.setNbAnimauxAffecte(nbBirdMort+nbFishMort);
            }   
        }
    }

    /* Check s'il y a un bateau en (x,y) */
    public boolean fBoat_en_XY(int x, int y) {
        for (FishingBoat b : fboat) {
            if (b.x==x && b.y==y)
                return true;
        }
        return false;
    }

    public boolean tank_en_XY(int x, int y) {
        for (Tank t : tank) {
            if (t.x==x && t.y==y)
                return true;
        }
        return false;
    }

    public void depart() {
        for (FishingBoat b : fboat) {
            if (b.x==0 && b.y==0)
                b.seDeplacer(
                    (int)Math.floor(Math.random()*ter.nbLignes), 
                    (int)Math.floor(Math.random()*ter.nbColonnes));
        }
        for (Tank b : tank) {
            if (b.x==0 && b.y==0)
                b.seDeplacer(
                    (int)Math.floor(Math.random()*ter.nbLignes), 
                    (int)Math.floor(Math.random()*ter.nbColonnes));
        }
    }

    /* Affichage */
    public String toString() {
        String s = "";
        
        /* First line */
        s += "|";
        for (int j=0;j<ter.nbColonnes;j++) 
            s += "---";
        s += "|\n";
        
        /* Content */
        for (int i=0;i<ter.nbLignes;i++) {
            s += "|";
            for (int j=0;j<ter.nbColonnes;j++) {

                /* Afficher les bateaux */
                if (this.tank_en_XY(i, j) || this.fBoat_en_XY(i, j)) {
                    if (tank_en_XY(i, j) && fBoat_en_XY(i, j))
                        s += " B ";
                    else if (tank_en_XY(i, j) && !fBoat_en_XY(i, j)) 
                        s += "XXX";
                    
                    else if (!tank_en_XY(i, j) && fBoat_en_XY(i, j)) 
                        s += " F ";
                }
                else {
                    /* Afficher les ressources */
                    if (fish.animal_en_XY(i,j) || bird.animal_en_XY(i,j)) {
                        /* Afficher les agents */
                        if (fish.animal_en_XY(i,j) && bird.animal_en_XY(i,j))
                            s += " @ "; 
                        else if (fish.animal_en_XY(i,j) && !bird.animal_en_XY(i,j)) 
                            s += " O ";
                        else if (!fish.animal_en_XY(i,j) && bird.animal_en_XY(i,j)) 
                            s += " * ";
                    }
                    else {
                        if (!ter.caseEstVide(i,j)){
                            /* Afficher les ressources */
                            if (ter.getCase(i,j).type == "Algues") 
                                s += "Alg";
                            else if (ter.getCase(i,j).type == "Coraux") 
                                s += "Cor";
                            else if (ter.getCase(i,j).type == "Rocher") 
                                s += "Roc";
                        }
                        else s += "   ";
                    }
                }
            }
            s += "|\n";
        }

        /* Last line */
        s += "|";
        for (int j=0;j<ter.nbColonnes;j++) 
            s += "---";
        s += "|\n\n";

        /* Compteur d'animaux */
        s += "Nombre de Poissons : "+fish.size()+"\n";
        s += "Nombre d'Oiseaux : "+bird.size()+"\n\n";

        /* Notation pour les signes */
        s += "B : Fishing Boat + Tank\n";
        s += "XXX : Tank\n";
        s += "F : Fishing Boat\n";
        s += "@ : Poissons + Oiseaux\n";
        s += "* : Oiseaux\n";
        s += "O : Poissons\n";
        s += "Alg : Algue\n";
        s += "Cor : Coraux\n";
        s += "Roc : Rocher\n";

        return s;
    }
}
