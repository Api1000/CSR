package Classes;
public class Navette extends Thread {
	public int places_navette;
	public Attraction attraction;
	public int personnes_dans_navette;
	public int id_navette;
	public boolean fin_du_tour;
	
	public Navette(int places_navette,Attraction attraction,int id_navette) {
		this.places_navette = places_navette;
		this.attraction = attraction;
		this.personnes_dans_navette = 0;
		this.id_navette = id_navette;
		this.fin_du_tour = false;
		this.setDaemon(true);
	}
	
	public synchronized Navette monterDansNavette(int id_client) {
		if(this.personnes_dans_navette == this.places_navette) {
			System.out.println("Navette pleine!");
			return null;
		}
		else {
			this.personnes_dans_navette ++;
			this.places_navette --;
			System.out.println("Le client " + id_client + " monte dans la navette " + id_navette +
					"dans l'attraction" + attraction.getIDAttraction());
		}
		return this;
	}
	
	public synchronized void descendreDeNavette(int id_client) throws InterruptedException {
		while(!fin_du_tour) {
			wait();
		}
		this.personnes_dans_navette--;
		this.places_navette ++;
		System.out.println("Le client " + id_client + " descend de la navette " + id_navette +
					" dans l'attraction " + attraction.getIDAttraction());
		notifyAll();
	}
	
	public int getPlacesNavette() {
		return this.places_navette;
	}
	
	public int getPersonnesDansNavette() {
		return this.personnes_dans_navette;
	}
	
	public int getIDNavette() {
		return this.id_navette;
	}
}
