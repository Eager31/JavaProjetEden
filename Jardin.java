import java.util.HashMap;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;

import Flore.Ail;
import Flore.Betterave;
import Flore.Carotte;
import Flore.Etat;
import Flore.IOgm;
import Flore.IRacePure;
import Flore.Tomate;
public class Jardin {

	private int longueur;
	private int largeur;
	private Emplacement[][] emplacements;
	private HashMap<String, Integer> panier;
	private Scanner scanner;
	
	
	public Jardin(int longueur, int largeur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.emplacements = new Emplacement[this.longueur][this.largeur];
		this.panier = new HashMap<String, Integer>(); 
		this.panier.put("Ail", 10);
		this.panier.put("Betterave", 10);
		this.panier.put("Carotte", 10);
		this.panier.put("Tomate", 10);
		
	}
	
	public void AjouterPanier(String nomDuVegetal,int nbGraines) {
		this.panier.put(nomDuVegetal, nbGraines); 
	}

	
	public void semer(int positionX, int positionY, int choix) {
		//scanner = new Scanner(System.in);
		//int positionX =1 ,positionY = 1, choix = 1;
		//Exception de position
		//try {
		/*
		System.out.println("Sélectionnez la position X de votre jardin");
		positionX = scanner.nextInt();
		System.out.println("Sélectionnez la position Y de votre jardin");
		positionY = scanner.nextInt();
		System.out.println("Quel végétal souhaitez vous planter ?");
		System.out.println(" 1 - Planter de l'Ail ?");
		System.out.println(" 2 - Planter de la Betterave ?");
		System.out.println(" 3 - Planter de la Carotte ?");
		System.out.println(" 4 - Planter de la Tomate ?");
		*/
		switch (choix) {
		case 1 :
			this.emplacements[positionX][positionY] = new Emplacement(new Ail()); //Ail
			this.panier.put("Ail", this.panier.get("Ail")-1);
		break;
		case 2 :
			this.emplacements[positionX][positionY] = new Emplacement(new Betterave()); //Betterave
			this.panier.put("Betterave", this.panier.get("Betterave")-1);
		break;
		case 3:
			this.emplacements[positionX][positionY] = new Emplacement(new Carotte()); //Carotte
			this.panier.put("Carotte", this.panier.get("Carotte")-1);
		break;
		case 4 :
			this.emplacements[positionX][positionY] = new Emplacement(new Tomate()); //Tomate
			this.panier.put("Tomate", this.panier.get("Tomate")-1);
		break;
		}
		//}catch(Exception e) {
		//	System.out.println("Seul les chiffres sont autorisés dans cette application");
		//}
	}
	

	public String RetournerJardin() {
		String chaineTemp;
		String chaineFinale = "\n";
		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {
					//if (!this.emplacements[x][y].getVegetal().getChar(i).lenght() != 0)
					//if (!this.emplacements[x][y].getVegetal().getChar(i).equals(' '))
					if (this.emplacements[x][y]  != null) { //Si le caractère existe ><
						chaineTemp = String.format("%c", this.emplacements[x][y].getVegetal().getChar( this.emplacements[x][y].getVegetal().getEtat().ordinal() ));//On affiche le caractère
						chaineFinale = chaineFinale.concat(chaineTemp);
					}else {
						chaineTemp = String.format("%c", 'o'); //Sinon on affiche o
						chaineFinale = chaineFinale.concat(chaineTemp);
					}
			}
			chaineFinale = chaineFinale.concat("\n");
		}
		return chaineFinale;
	}
	
	public void saisonSuivante() {
		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {
				if (this.emplacements[x][y]  != null) {
					if (this.emplacements[x][y].getVegetal().getEtat() != (Etat.MORT)) {
						this.emplacements[x][y].getVegetal().grandir(); 
					}
					
				}else {
					//do nothing
				}
			}
		}
	}
	
	private SimpleEntry<Integer,Integer> NouvellesVal;
	
	public SimpleEntry<Integer,Integer> recolter() {
		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {
				if (this.emplacements[x][y]  != null) { //On vérifie que les emplacements comportent des plantes
					if (this.emplacements[x][y].getVegetal().getEtat() == (Etat.FLEUR)){ // On vérifie l'état des fleurs
						if(this.emplacements[x][y].getVegetal() instanceof IRacePure) { //Si le vegetal est de type IRacePure
							IRacePure v = (IRacePure) this.emplacements[x][y].getVegetal(); //On fais un cast {Compliqué}
							v.seReproduire(this.panier); //On ajoute au panier les graines
						}
						if(this.emplacements[x][y].getVegetal() instanceof IOgm) { //Si le vegetal est de type IOgm
							IOgm g = (IOgm) this.emplacements[x][y].getVegetal(); //On fais un cast {Compliqué}
							NouvellesVal = g.seDupliquer(this.longueur,this.largeur); //On récupère la nouvelle longueur et la nouvelle largeur
						    int NouveauX = NouvellesVal.getKey();
						    int NouveauY = NouvellesVal.getValue();
						    this.emplacements[NouveauX][NouveauY] = new Emplacement(new Betterave());
						    return NouvellesVal;
						}
						this.emplacements[x][y] = null; 
					}
				}
			}
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return String.format("Voici notre jardin :\n %s\n Et notre panier contient :\n Tomate : %d graine(s) \n Carotte : %d graine(s)\n Ail : %d graine(s)\n Betterave :  %d graine(s)",RetournerJardin()				
					,this.panier.get("Tomate"),this.panier.get("Carotte"),this.panier.get("Ail"),this.panier.get("Betterave"));
	}

	
	/*ACCESSEURS*/
	public Emplacement[][] getEmplacements() {
		return emplacements;
	}

	public void setEmplacements(Emplacement[][] emplacements) {
		this.emplacements = emplacements;
	}
	public HashMap<String, Integer> getPanier() {
		return panier;
	}

	public void setPanier(HashMap<String, Integer> panier) {
		this.panier = panier;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	
	
	
}
