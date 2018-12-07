package org.inria.reslet.mta.internals;

import java.util.ArrayList;

public class Parc {
	
	ArrayList<Attraction> attractions ;
	ArrayList<Clients> clients;
	ArrayList<Navette> navettes1;
	ArrayList<Navette> navettes2;
	ResponsableBilleterie rb;
	Billeterie billeterie;
	
	public Parc() {
		init();
	}
	
	public void init() {
		this.billeterie = new Billeterie(20);
		this.attractions = new ArrayList<Attraction>();
		this.clients = new ArrayList<Clients>();
		Attraction a1 = new Attraction(1, 400, 200);
		Attraction a2 = new Attraction(2, 400, 200);
		this.navettes1 = new ArrayList<Navette>();
		this.navettes2 = new ArrayList<Navette>();
		
		Navette n1 = new Navette(10, a1, 1);
		Navette n2 = new Navette(10, a1, 2);
		navettes1.add(n1);
		navettes1.add(n2);
		a1.setListeNavettes(navettes1);
		
		Navette n3 = new Navette(15, a2, 3);
		Navette n4 = new Navette(15, a2, 4);
		navettes2.add(n3);
		navettes2.add(n4);
		a2.setListeNavettes(navettes2);
		
		attractions.add(a1);
		attractions.add(a2);
		this.rb = new ResponsableBilleterie(1, this.billeterie);
		for(int i=1; i<2; i++) {
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
		for(Clients c : clients) {
			c.start();
		}
		rb.start();
	}

	public static void main(String[] args) {
		Parc p = new Parc();
		p.depart();
		

	}

}
