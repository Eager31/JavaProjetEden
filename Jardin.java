import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Observable;

import Flore.Ail;
import Flore.Betterave;
import Flore.Carotte;
import Flore.Etat;
import Flore.Tomate;
public class Jardin extends Observable {

	private int longueur;
	private int largeur;
	private Emplacement[][] emplacements;
	private HashMap<String, Integer> panier;
	
	
	public Jardin(int longueur, int largeur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.emplacements = new Emplacement[this.longueur][this.largeur];
		this.panier = new HashMap<String, Integer>(); 
		
		//On ajoute des graines dans le panier de notre Jardin qui est dans notre serre
		this.AjouterPanier("Tomate", 5);
		this.AjouterPanier("Carotte", 5);
		this.AjouterPanier("Ail", 10);
		this.AjouterPanier("Betterave", 5);
	}
	
	public void AjouterPanier(String nomDuVegetal,int nbGraines) {
		this.panier.put(nomDuVegetal, nbGraines); 
	}

	
	public void semer(int positionX, int positionY,int choix ) {
		//Exception de position		
		switch (choix) {
		case 1 :
			this.emplacements[positionX][positionY] = new Emplacement(new Ail()); //Ail
			this.panier.put("Ail", this.panier.get("Ail")-1);
		break;
		case 2 :
			this.emplacements[positionX][positionY] = new Emplacement(new Betterave()); //Betterave
			this.panier.put("Ail", this.panier.get("Betterave")-1);
		break;
		case 3:
			this.emplacements[positionX][positionY] = new Emplacement(new Carotte()); //Carotte
			this.panier.put("Ail", this.panier.get("Carotte")-1);
		break;
		case 4 :
			this.emplacements[positionX][positionY] = new Emplacement(new Tomate()); //Tomate
			this.panier.put("Ail", this.panier.get("Tomate")-1);
		break;
		}
	}
	
	

	public String RetournerJardin() {
		String chaineTemp;
		String chaineFinale = "\n";
		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {
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
				}
			}
		}
	}
	
	public void recolter(int x, int y) {
				if (this.emplacements[x][y]  == null)//On vérifie que les emplacements comportent des plantes
					return;
					if (this.emplacements[x][y].getVegetal().getEtat() == (Etat.FLEUR)){ // On vérifie l'état des fleurs				
						
						//On récupère le nom du légume
						String chaine = this.emplacements[x][y].getVegetal().getClass().getName();
						String chaineASupprimer="Flore."; //On retire le nom 
						chaine = chaine.replace(chaineASupprimer, "");
						this.emplacements[x][y].getVegetal().FaireRacePure(panier, chaine); //On fais l'action des races pures
						SimpleEntry<Integer, Integer> nouvellePosition = this.emplacements[x][y].getVegetal().FaireOGM(this.longueur, this.largeur); //On fais l'action des OGM
						
						if (nouvellePosition != null) {
							switch (this.emplacements[x][y].getVegetal().getChar(4)) { //Comment le créer sans le char ?
								case 'A' :
									this.emplacements[nouvellePosition.getKey()][nouvellePosition.getValue()] = new Emplacement(new Ail());
								break;
								case 'B' :
									this.emplacements[nouvellePosition.getKey()][nouvellePosition.getValue()] = new Emplacement(new Betterave());
								break;
								case 'C' :
									this.emplacements[nouvellePosition.getKey()][nouvellePosition.getValue()] = new Emplacement(new Carotte());
								break;
								case 'T' :
									this.emplacements[nouvellePosition.getKey()][nouvellePosition.getValue()] = new Emplacement(new Tomate());
								break;
								//Possibilité de modifier le comportement en exploitant le pattern Stratégie
							}
						}
						this.emplacements[x][y] = null; 
					}
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

	public HashMap<String, Integer> getPanier() {
		return panier;
	}

	public void setPanier(HashMap<String, Integer> panier) {
		this.panier = panier;
	}

	
	//Va être déclanché toutes les cinq secondes
	public void actualiserMesures() {
		setChanged(); //L'état à changé = Notification ! C'est lui le bijoux pour indiquer qu'on autorise
		notifyObservers(); //Va MAJ si setChanged == true
	}
	
	public void setMAJPousse(Emplacement[][] emplacement) {
		this.emplacements = emplacement;
		actualiserMesures();
	}
	
	
}
