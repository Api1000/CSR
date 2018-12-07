package org.inria.reslet.mta.internals;

import java.util.ArrayList;
import java.util.Arrays;

public class Clients extends Thread {
	public int nb_billets_achetes;
	public ArrayList<Attraction> attractions = new ArrayList<Attraction>();
	public int id_client;
	public Billeterie billeterie;

	public Clients(int nb_billets_achetes, ArrayList<Attraction> attractions, int id_client, Billeterie billeterie) {
		this.nb_billets_achetes = nb_billets_achetes;
		this.attractions = attractions;
		this.id_client = id_client;
		this.billeterie = billeterie;
	}

	public void run() {
		try {
			this.billeterie.venteTickets(nb_billets_achetes, id_client);
			System.out.println(nb_billets_achetes + " billets ont été vendues.");
			for(Attraction a : attractions) {
				Navette navette = a.getIDNavetteQuai();
				navette.monterDansNavette(id_client);
				navette.descendreDeNavette(id_client);			
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Le client numero : " + id_client + " sort du Parc\n");
	}
}
