package Classes;

import java.util.ArrayList;
import java.util.List;

public class Attraction extends Thread{
	public int id_Attraction;
	public List<Navette> navettes;
	public long duree_attraction;
	public long duree_a_quai;
	public boolean quai_vide;
	public int nb_places_navette;
	public int nb_navettes;
	public int navette_courante;

	public Attraction(int id_Attraction, int nb_navettes ,int nb_places_navette, long duree_attraction, long duree_a_quai ) {
		this.id_Attraction = id_Attraction;
		this.nb_navettes = nb_navettes;
		this.nb_places_navette = nb_places_navette;
		this.duree_attraction = duree_attraction;
		this.duree_a_quai = duree_a_quai;
		this.navettes = new ArrayList<Navette>();
		this.quai_vide = true;
		this.navette_courante = 0;
	}

	public synchronized void addNavettetoAttraction(Navette n) {
		this.navettes.add(n);
		System.out.println("La navette " + n.id_navette + " a été ajouté à la liste des navettes");
	}


	public synchronized void navetteArriveQuai(Navette n) throws InterruptedException {
		while(!quai_vide) {
			wait();
		}
		navette_courante = n.id_navette;
		n.navette_a_quai = true;
		System.out.println("La navette numéro " + n.getIdNavette() + " arrive au quai de l'attraction " + this.id_Attraction);
		this.quai_vide = false;
	}

	public synchronized void navetteSorsQuai(Navette n) {
		System.out.println("La navette numéro " + n.getIdNavette() + " pars du quai de l'attraction " + this.id_Attraction);
		this.quai_vide = true;
		n.navette_a_quai = false;
		notifyAll();
	}


	public Navette getNavette() {
		//System.out.println("La navette courante est " + navettes.get(navette_courante));
		return navettes.get(navette_courante);	
	}

	public long get_temps_a_quai() {
		return this.duree_a_quai;
	}

	public long get_temps_attraction() {
		return this.duree_attraction;
	}

	public int getIDAttraction() {
		return this.id_Attraction;
	}

}