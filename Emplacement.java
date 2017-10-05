import Flore.Vegetal;

public class Emplacement {

	private Vegetal vegetal;

	public Emplacement(Vegetal vegetal) {
		this.vegetal = vegetal;
	}

	@Override
	public String toString() {
		return String.format("%s \n %d","Emplacement contient :",this.vegetal);
	}

	public Vegetal getVegetal() {
		return vegetal;
	}

	public void setVegetal(Vegetal vegetal) {
		this.vegetal = vegetal;
	}

	
	
}
