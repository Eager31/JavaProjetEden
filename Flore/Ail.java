package Flore;


public class Ail extends Vegetal{

	
	
	public Ail() {
		super();
		this.dessin[3] = 'a';
		this.dessin[4] = 'A';
		comportementOGM = new SeDupliquerUneFois();
		comportementRacePure = new DeuxGraines(); //Permet de définir que la plante va donner 2 graines
	}	
}
