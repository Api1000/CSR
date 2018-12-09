package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parc extends Thread{
	public static List<Clients> clients;
	public List<Attraction> attractions;
	public Billeterie billeterie;
	public ResponsableBilleterie rb;


	public Parc() {
		this.billeterie = new Billeterie(3);
		this.rb = new ResponsableBilleterie(50, this.billeterie);
		this.clients = new ArrayList<Clients>();
		this.attractions = new ArrayList<Attraction>();
		initParc();
	}

	public void initParc() {
		Attraction a1 = new Attraction(1, 2, 5, 7000,7000);
		a1.addNavettetoAttraction(new Navette(0, 5, 0, a1));
		a1.addNavettetoAttraction(new Navette(1, 5, 0, a1));
		attractions.add(a1);

		for(int i=1; i<6;i++) {
			clients.add(new Clients(i,1,this.billeterie, attractions));
			//System.out.println("client " + i + " créé");
		}
	}

	public synchronized void start() {
		this.rb.start();
		for(Clients c : clients) {
			c.start();
		}
		for(Attraction a : attractions) {
			//a.start();
			for(Navette n : a.navettes) {
				n.start();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Parc p = new Parc();
		p.start();
	}
}

