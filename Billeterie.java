package Classes;


public class Billeterie extends Thread {
	public int nb_billets;
	public long temps_impression;
	public ResponsableBilleterie rb;
	public boolean rupture;
	public long temps_rechargement;


	public Billeterie(int nb_billets) {
		this.nb_billets = nb_billets;
		this.temps_impression = 20;
		this.rupture = false;
		this.temps_rechargement = 200;
	}

	public synchronized void vendreBillets(int nb_billets_demande, Clients c) throws InterruptedException {
		if(nb_billets_demande > this.nb_billets) {
			this.rupture = true;
			wait();
			this.nb_billets -= nb_billets_demande;
			c.a_achete = true;
			System.out.println(nb_billets_demande + " billets achetés par "+ c.getIDClient());
		}
		else {
			this.nb_billets -= nb_billets_demande;
			System.out.println(nb_billets_demande + " billets achetés par "+ c.getIDClient());
			sleep(this.temps_impression); // Impression ticket
			c.nb_billets_achete = nb_billets_demande;
			c.a_achete = true;
		}
	}

	public synchronized void rechargerBilleterie(int nb_billets_a_recharger) throws InterruptedException {
		//System.out.println(this.rupture);
		if(this.rupture) {
			System.out.println("Rechargement en cours....");
			sleep(this.temps_rechargement); 
			this.nb_billets += nb_billets_a_recharger;
			System.out.println("La billeterie a été rechargée.");
			this.rupture = false;
			notifyAll();
		}
	}


}
