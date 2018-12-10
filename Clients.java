package Classes;

import java.util.List;

public class Clients extends Thread {
	public int id_client;
	public int nb_billets_achete;
	public int nb_attraction_effectue;
	public boolean a_achete;
	public List<Attraction> attractions;
	public Billeterie billeterie;

	public Clients(int id_client, int nb_billets_achete, Billeterie billeterie, List<Attraction> attractions) {
		this.id_client = id_client;
		this.nb_billets_achete = nb_billets_achete;
		this.nb_attraction_effectue = 0;
		this.a_achete = false;
		this.billeterie = billeterie;
		this.attractions = attractions;
	}

	/*public synchronized void monteDansNavette(Navette navette) throws InterruptedException {
		while(navette.attraction.quai_vide){};
		if(navette.capacite_navette == navette.nb_clients_navette) {
			System.out.println("La navette " + navette.getIdNavette() + " est pleine.");
			wait();
		}
		navette.nb_clients_navette ++;
		//System.out.println("IL Y A " + navette.nb_clients_navette + " CLIENTS DANS LA NAVETTE");
		System.out.println("Le client " + this.id_client + " monte dans la navette " + navette.getIdNavette());
	}


	public synchronized void descendreNavette(Navette navette) {
		while(navette.attraction.quai_vide){};
		navette.nb_clients_navette--;
		System.out.println("Le client " + this.id_client + " descend de la navette " +  navette.getIdNavette());
		this.nb_attraction_effectue ++;
	}*/

	public int getIDClient() {
		return this.id_client;
	}

	public int getNbAttractionEffectuees() {
		return this.nb_attraction_effectue;
	}
	


	@Override
	public String toString() {
		return "Je suis le client n° " + this.id_client;
	}

	public void run() {
		try {
			billeterie.vendreBillets(nb_billets_achete, this);
			for(Attraction a : attractions) {
				//System.out.println(a.nb_places_navette);
				while(nb_attraction_effectue != 2) {
					Navette n = a.getNavette();
					n.monteDansNavette(this);
					while(!a.quai_vide) {}; //tant que le quai n'est pas vide, le client ne sleep pas le temps de l'attraction
					sleep(a.get_temps_attraction());
					n.descendreNavette(this);
					while(a.quai_vide) {};
					//System.out.println(nb_attraction_effectue);
				}
			}

			System.out.println("Le client " + id_client + "a effectué " + nb_attraction_effectue + " attractions.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
