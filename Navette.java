package Classes;
public class Navette extends Thread {
	public int id_navette;
	public int capacite_navette;
	public int nb_clients_navette;
	public Attraction attraction;
	public boolean navette_a_quai;

	public Navette(int id_navette, int capacite_navette,int nb_clients_navette, Attraction attraction ) {
		this.id_navette = id_navette;
		this.capacite_navette = capacite_navette;
		this.nb_clients_navette = 0;
		this.attraction = attraction;
		this.navette_a_quai = false;
	}

	public synchronized Navette monteDansNavette(Clients c) {
		System.out.println("coin");
		//while(attraction.quai_vide) {};
		if(capacite_navette == nb_clients_navette) {
			System.out.println("La navette " + getIdNavette() + " est pleine.");
			return null;
		}
		nb_clients_navette ++;
		//System.out.println("IL Y A " + navette.nb_clients_navette + " CLIENTS DANS LA NAVETTE");
		System.out.println("Le client " + c.id_client + " monte dans la navette " + getIdNavette());
		return this;
	}


	public synchronized void descendreNavette(Clients c) {
		while(attraction.quai_vide){};
		nb_clients_navette--;
		System.out.println("Le client " + c.id_client + " descend de la navette " +  getIdNavette());
		c.nb_attraction_effectue ++;
	}

	/*public synchronized void navette_arrive(Attraction attraction) throws InterruptedException {
		if(!isQuaiVide(attraction)) {
			System.out.println("Le quai est inaccessible pour la navette " + id_navette);
			wait();
		}
		//attraction.navette_courante = id_navette-1;
		System.out.println("La navette " + this.id_navette  + " arrive à quai de l'attraction " + attraction.getIDAttraction());
		attraction.quai_vide = false;
		navette_a_quai = true;
	}

	public synchronized void navette_part(Attraction attraction) throws InterruptedException {
		System.out.println("La navette " + this.id_navette  + " part du quai de l'attraction " + attraction.id_Attraction);
		attraction.quai_vide = true;
		navette_a_quai = false;
		System.out.println("La navette " + this.id_navette  + " est en cours d'attraction...");
		notifyAll();
	}
	 */

	public int getIdNavette() {
		return this.id_navette;
	}



	public synchronized void run() {
		try {
			attraction.navetteArriveQuai(this);
			while(true) {
				System.out.println("La navette " + this.id_navette  + " patiente à quai...");
				sleep(attraction.duree_a_quai);
				attraction.navetteSorsQuai(this);
				sleep(attraction.duree_attraction);
				attraction.navetteArriveQuai(this);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
