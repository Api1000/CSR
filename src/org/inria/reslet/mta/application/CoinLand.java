package org.inria.reslet.mta.application;

import java.util.ArrayList;
import org.inria.reslet.mta.internals.*;

public class CoinLand {

	private ArrayList<Attraction> attractions;
	private ArrayList<Clients> clients;
	private ArrayList<Navette> navettesVCF;
	private ArrayList<Navette> navettesCC;
	private ResponsableBilleterie rb;
	private Billeterie billeterie;

	public CoinLand() {

		this.billeterie = new Billeterie(20);
		this.clients = new ArrayList<Clients>();
		this.navettesVCF = new ArrayList<Navette>();
		this.navettesCC = new ArrayList<Navette>();

		Attraction VCF = new Attraction("VieuxChienFantôme", 400, 200);
		Navette navette1VCF = new Navette(1, 10, VCF);
		Navette navette2VCF = new Navette(2, 10, VCF);
		navettesVCF.add(navette1VCF);
		navettesVCF.add(navette2VCF);
		VCF.setListeNavettes(navettesVCF);

		Attraction CC = new Attraction("CoinCoin", 400, 200);
		Navette navette1CC = new Navette(1, 15, CC);
		Navette navette2CC = new Navette(2, 15, CC);
		Navette navette3CC = new Navette(3, 15, CC);
		navettesCC.add(navette1CC);
		navettesCC.add(navette2CC);
		navettesCC.add(navette3CC);
		CC.setListeNavettes(navettesCC);

		attractions.add(VCF);
		attractions.add(CC);
		this.rb = new ResponsableBilleterie(1, this.billeterie);
		for (int i = 1; i < 2; i++) {
			clients.add(new Clients(1, this.attractions, i, this.billeterie));
		}

	}

	public void depart() {
		for (Navette n : navettesVCF) {
			n.start();
		}
		for (Navette n : navettesCC) {
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
