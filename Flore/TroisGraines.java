package Flore;

import java.util.HashMap;

public class TroisGraines implements ComportementRacePure {

	@Override //On ajoute trois graines au panier
	public void seReproduire(HashMap<String, Integer> panier, String nomVegetal) {
		panier.put(nomVegetal, panier.get(nomVegetal) + 3);  // Ajoute trois Graines de vegetal pour le légume choisis
	}

}
