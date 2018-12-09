package Classes;

public class ResponsableBilleterie extends Thread {
	public int nb_ticket_a_recharger;
	public Billeterie billeterie;

	public ResponsableBilleterie(int nb_ticket_a_recharger, Billeterie b) {
		this.nb_ticket_a_recharger =nb_ticket_a_recharger;
		this.billeterie = b;
	}
	
	/*public synchronized void rechargerBilleterie(int nb_billets_a_recharger) throws InterruptedException {
		if(billeterie.rupture) {
			System.out.println("Rechargement en cours....");
			sleep(this.temps_rechargement); 
			billeterie.nb_billets += nb_billets_a_recharger;
			System.out.println("La billeterie a été rechargée.");
			notifyAll();
			billeterie.rupture = false;
		}
	}*/
	
	public void run() {
		while(true) {
			try {
				billeterie.rechargerBilleterie(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
