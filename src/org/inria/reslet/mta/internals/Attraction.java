package org.inria.reslet.mta.internals;

import java.util.ArrayList;

public class Attraction extends Thread  {
	public ArrayList<Navette> navettes = new ArrayList<Navette>();
	public boolean est_a_quai;
	public long temps_attraction;
	public long temps_a_quai;
	public int id_attraction;
	public int num_navette_quai;


	public Attraction(int id_attraction,long temps_attraction,long temps_a_quai ) {
		this.id_attraction = id_attraction;
		this.temps_a_quai = temps_a_quai;
		this.temps_attraction = temps_attraction;
		this.est_a_quai = false;
		this.num_navette_quai = 0;
	}
	
	public synchronized void arriveAQuai(int navette_a_quai) throws InterruptedException {
		while(est_a_quai) {
			wait();
		}
		//this.num_navette_quai = navette_a_quai;
		System.out.println("La navette numéro " + getIDNavetteQuai().id_navette + " de l'attraction "+
		this.id_attraction + " arrive à quai.");
		this.est_a_quai = true;
	}
	
	public synchronized void parsDuQuai(int navette_a_quai) {
		System.out.println("La navette numéro " + getIDNavetteQuai().id_navette + " de l'attraction "+
				this.id_attraction + " pars du quai" );
		this.est_a_quai = false;
		notifyAll();
	}
	
	public long getTempsAQuai() {
		return this.temps_a_quai;
	}
	
	public long getTempsAttraction() {
		return this.temps_attraction;
	}
	
	public void setListeNavettes(ArrayList<Navette> navettes) {
		this.navettes = navettes;
	}
	
	public Navette getIDNavetteQuai() {
		return navettes.get(num_navette_quai);
	}
	
	public int getIDAttraction() {
		return this.id_attraction;
	}
}