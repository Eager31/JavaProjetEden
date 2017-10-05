package Flore;


public class Carotte extends Vegetal{

	public Carotte() {
		super();
		this.dessin[3] = 'c';
		this.dessin[4] = 'C';
		comportementOGM = new SeDupliquerNone();
		comportementRacePure = new DeuxGraines(); //Permet de d�finir que la plante va donner 2 graines
	}
}
