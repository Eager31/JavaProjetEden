package Flore;

import java.util.AbstractMap.SimpleEntry;

public class Betterave extends Vegetal implements IOgm{

	public Betterave() {
		super();
		this.dessin[3] = 'b';
		this.dessin[4] = 'B';
	}

	@Override
	public SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) { //La longueur et la largeur sont ceux du p.p
		
		int newX = (int) (Math.random() * longueur );
		int newY = (int) (Math.random() * largeur );//Pour un entier entre 0 et longueurMax  
		SimpleEntry<Integer,Integer> refonte = new SimpleEntry<>(newX,newY); 
		return refonte;
	}


}
