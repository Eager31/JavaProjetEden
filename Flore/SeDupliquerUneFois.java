package Flore;

import java.util.AbstractMap.SimpleEntry;

public class SeDupliquerUneFois implements ComportementOGM {

	@Override // On crée deux nouvelles positions (1 pour le moment)
	public SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) {
		int newX = (int) (Math.random() * longueur );
		int newY = (int) (Math.random() * largeur );//Pour un entier entre 0 et longueurMax  
		SimpleEntry<Integer,Integer> refonte = new SimpleEntry<>(newX,newY); 
		return refonte;

	}
}
