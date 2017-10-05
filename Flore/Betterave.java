package Flore;


public class Betterave extends Vegetal{

	public Betterave() {
		super();
		this.dessin[3] = 'b';
		this.dessin[4] = 'B';
		//Attribution des propriétés
		comportementOGM = new SeDupliquerUneFois(); //Peut se dupliquer deux fois
		comportementRacePure = new ZeroGraines(); //Peut donner deux graines
	}
}
