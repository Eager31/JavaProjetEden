package Flore;

public class Tomate extends Vegetal {

	public Tomate() {
		super();
		this.dessin[3] = 't';
		this.dessin[4] = 'T';
		
		comportementOGM = new SeDupliquerUneFois(); //Peut se dupliquer deux fois
		comportementRacePure = new ZeroGraines(); //Ne pas planter plus de graines
	}

}
