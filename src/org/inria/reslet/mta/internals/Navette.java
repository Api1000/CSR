package org.inria.reslet.mta.internals;

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
			System.out.println("Navette pleine! car " + this.personnes_dans_navette + " = " + this.places_navette);
			return null;
		}
		else {
			System.out.println("Le client " + id_client + " monte dans la navette " + id_navette +
					" dans l'attraction " + attraction.getIDAttraction());
			this.personnes_dans_navette ++;
			System.out.println("Il y a " + this.personnes_dans_navette + " personnes dans la navette " + this.id_navette + " "+
			"pour un total de " + this.places_navette + " places dans la navette");
		}
		return this;
	}
	
	public synchronized void descendreDeNavette(int id_client) throws InterruptedException {
		while(!fin_du_tour) {
			wait();
		}
		System.out.println("Le client " + id_client + " descend de la navette " + this.id_navette +
				" dans l'attraction " + attraction.getIDAttraction());
		this.personnes_dans_navette--;
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
	
	public synchronized void setFinDuTour(boolean fin) {
		fin_du_tour = fin;
		if(fin_du_tour) {
			System.out.println("La navette " + this.id_navette + " a fini son tour");
			notifyAll();
		}
	}
	
	public void run() {
		try {
			attraction.arriveAQuai(id_navette);
			while(true) {
				sleep(attraction.temps_a_quai);
				attraction.parsDuQuai(id_navette);
				sleep(attraction.temps_attraction);
				attraction.arriveAQuai(id_navette);
				setFinDuTour(true);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
