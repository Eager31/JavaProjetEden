package Flore;

import java.util.HashMap;

public class DeuxGraines implements ComportementRacePure {

	@Override //On ajoute deux graines au panier
	public void seReproduire(HashMap<String, Integer> panier, String nomVegetal) {
		panier.put(nomVegetal, panier.get(nomVegetal) + 2);  // Ajoute deux Graines de vegetal pour le légume choisis
	}

}
