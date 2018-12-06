package Classes;

public class ResponsableBilleterie extends Thread {
	public int nb_ticket_rechargement;
	public Billeterie billeterie;

	public ResponsableBilleterie(int nb_ticket_rechargement, Billeterie billeterie) {
		this.nb_ticket_rechargement = nb_ticket_rechargement;
		this.billeterie = billeterie;
		this.setDaemon(true);
	}

	public synchronized void run() {
		while(true) {
			billeterie.rechargementTickets(nb_ticket_rechargement);
		}
	}
}
