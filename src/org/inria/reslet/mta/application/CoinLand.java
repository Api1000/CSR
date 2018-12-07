package org.inria.reslet.mta.application;

import java.util.ArrayList;
import org.inria.reslet.mta.internals.*;

public class CoinLand {
	
	private ArrayList<Attraction> attractions;
	private ArrayList<Clients> clients;
	private ArrayList<Navette> navettes1;
	private ArrayList<Navette> navettes2;
	private ResponsableBilleterie rb;
	private Billeterie billeterie;

	public CoinLand() {
		init();
	}

	public void init() {
		this.billeterie = new Billeterie(20);
		this.clients = new ArrayList<Clients>();
		this.navettes1 = new ArrayList<Navette>();
		this.navettes2 = new ArrayList<Navette>();

		Attraction VieuxChienFantôme = new Attraction(1, 400, 200);
		Navette n1 = new Navette(1, 10, VieuxChienFantôme);
		Navette n2 = new Navette(2, 10, VieuxChienFantôme);
		navettes1.add(n1);
		navettes1.add(n2);
		VieuxChienFantôme.setListeNavettes(navettes1);

		Attraction CoinCoin = new Attraction(2, 400, 200);
		Navette n3 = new Navette(15, 3, CoinCoin);
		Navette n4 = new Navette(15, 4, CoinCoin);
		navettes2.add(n3);
		navettes2.add(n4);
		CoinCoin.setListeNavettes(navettes2);

		attractions.add(VieuxChienFantôme);
		attractions.add(CoinCoin);
		this.rb = new ResponsableBilleterie(1, this.billeterie);
		for (int i = 1; i < 2; i++) {
			clients.add(new Clients(1, this.attractions, i, this.billeterie));
		}

	}

	public void depart() {
		for (Navette n : navettes1) {
			n.start();
		}
		for (Navette n : navettes2) {
			n.start();
		}
		for (Clients c : clients) {
			c.start();
		}
		rb.start();
	}

	public static void main(String[] args) {
		CoinLand p = new CoinLand();
		p.depart();

	}

}
