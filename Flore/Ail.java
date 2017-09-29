package Flore;

import java.util.HashMap;

public class Ail extends Vegetal implements IRacePure{

	public Ail() {
		super();
		this.dessin[3] = 'a';
		this.dessin[4] = 'A';
	}

	@Override
	public void seReproduire(HashMap<String, Integer> panier) {
		panier.put("Ail", panier.get("Ail") + 3); 
	}

	
	
}
