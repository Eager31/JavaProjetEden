package Flore;

public abstract class Vegetal {
	
	protected  Etat etat;
	protected  char[] dessin;
	
	public Vegetal() {
		this.etat = Etat.GRAINE; 
		this.dessin = new char[6];
		char tableauCaractere[] = {'_','.','i',' ',' ','#'}; //Création du tableau
		this.dessin = tableauCaractere; //Assignation à dessin
	}

	
	
	/*ACCESSEUR*/
	public Etat getEtat() { //Renvois tout les Etats
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public char[] getDessin() { //Renvois un tableau de caractère qui va contenir les dessins
		return dessin;
	}
	
	public char getChar(int i) {//Renvois un tableau de caractère qui va contenir les dessins
		return dessin[i];
	}

	public void setDessin(char[] dessin) {
		this.dessin = dessin;
	}

	
	public void grandir() {
		this.etat = Etat.values()[this.etat.ordinal() + 1]; //Pemet de passer à l'étape suivante d'une énumération
	}
	
	
}
