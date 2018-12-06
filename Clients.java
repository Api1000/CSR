package Classes;

import java.util.ArrayList;

public class Clients extends Thread {
	public int nb_billets_achetes;
	public ArrayList<Attraction> attractions = new ArrayList<Attraction>();
	public int id_client;
	public Billeterie billeterie;
	
	public Clients(int nb_billets_achetes, ArrayList<Attraction> attractions, int id_client) {
		this.nb_billets_achetes = nb_billets_achetes;
		this.attractions = attractions;
		this.id_client = id_client;
	}
	
	public synchronized void run() {
		try {
			this.billeterie.venteTickets(nb_billets_achetes, id_client);
			for(Attraction a : attractions) {
				Navette navette = a.getIDNavetteQuai().monterDansNavette(id_client);
				if(a.getIDNavetteQuai().monterDansNavette(id_client)==null) {
					sleep(a.getTempsAQuai());
				}
				else {
					navette.monterDansNavette(id_client);
					Clients.sleep(a.getTempsAttraction());
					navette.descendreDeNavette(id_client);
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
