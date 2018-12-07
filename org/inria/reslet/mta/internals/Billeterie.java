package Classes;

public class Billeterie extends Thread {
	public int nb_billets_billeterie;
	public boolean achat_en_cours;
	//public boolean rupture;
	
	
	public Billeterie(int nb_billets_billeterie) {
		this.nb_billets_billeterie = nb_billets_billeterie;
	//	this.rupture = false;
		this.achat_en_cours = false;
	}
	
	public synchronized void venteTickets(int nb_billets, int id_client) throws InterruptedException {
		if(nb_billets_billeterie < nb_billets) {
			achat_en_cours  = true;
			wait();
		}
	}
	
	public synchronized void rechargementTickets(int nb_billets) {
		if(achat_en_cours) {
			this.nb_billets_billeterie += nb_billets;
			achat_en_cours = false;
			System.out.println("Le responsable a remis " + nb_billets);
			System.out.println("Il reste " + this.nb_billets_billeterie + " tickets dans la billeterie");
		}
		notifyAll();
	}
	
	public int getBilleterie() {
		return this.nb_billets_billeterie;
	}
	
}
