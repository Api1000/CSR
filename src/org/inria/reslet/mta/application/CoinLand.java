package org.inria.reslet.mta.application;

import java.util.ArrayList;
import java.util.Iterator;

import org.inria.reslet.mta.internals.*;

public class CoinLand {

	private static ArrayList<Attraction> attractions;
	private static ArrayList<Clients> clients;
	private static ArrayList<Navette> navettesVCF;
	private static ArrayList<Navette> navettesCC;
	private static ResponsableBilleterie respo;
	private static Billeterie billeterie;

	public CoinLand() {

		CoinLand.billeterie = new Billeterie(20);
		CoinLand.clients = new ArrayList<Clients>();
		CoinLand.navettesVCF = new ArrayList<Navette>();
		CoinLand.navettesCC = new ArrayList<Navette>();
	}

	
	public static void main(String[] args) {
		/*CoinLand p = new CoinLand();
		p.depart();*/

		respo = new ResponsableBilleterie(1, billeterie);
		for (int i = 1; i < 2; i++) {
			clients.add(new Clients(1, attractions, i, billeterie));
		}
		
		Attraction VCF = new Attraction("VieuxChienFantôme", 400, 200);
		Navette navette1VCF = new Navette(1, 10, VCF);
		Navette navette2VCF = new Navette(2, 10, VCF);
		navettesVCF.add(navette1VCF);
		navettesVCF.add(navette2VCF);
		VCF.setListeNavettes(navettesVCF);
		attractions.add(VCF);

		Attraction CC = new Attraction("CoinCoin", 400, 200);
		Navette navette1CC = new Navette(1, 15, CC);
		Navette navette2CC = new Navette(2, 15, CC);
		Navette navette3CC = new Navette(3, 15, CC);
		navettesCC.add(navette1CC);
		navettesCC.add(navette2CC);
		navettesCC.add(navette3CC);
		CC.setListeNavettes(navettesCC);
		attractions.add(CC);
		
		
		/*for (Navette nav : navettesVCF) {
			nav.start();
		}
		for (Navette nav : navettesCC) {
			nav.start();
		}
		for (Clients clts : clients) {
			clts.start();
		}
		respo.start();
	*/
		Iterator<Navette> i1 = navettesVCF.iterator();
		Iterator<Navette> i2 = navettesCC.iterator();
		while (i1.hasNext() && i2.hasNext()) {
			navette1CC.start();
			navette2CC.start();
			navette3CC.start();
			navette1VCF.start();
			navette2VCF.start();
			i1.next();
			i2.next();
		}
	}

}
