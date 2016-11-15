package robot.io;

import robot.map.*;
import robot.entities.*;
import robot.graph.*;

import java.util.*;

public class DonneesSimulation{
    private Carte donneesCarte;
    private List<Incendie> donneesIncendie;
    private List<Robot> donneesRobot;

    
    /**
     * Crée une nouvelle DonnesSimulation avec tous les donnees d'une carte, des robots et des incendies
     * @param mapData
     * @param fireData[]
     * @param bot[]
     */
    public DonneesSimulation(Carte mapData, Incendie fireData[], Robot bot[]){
	this.donneesCarte = mapData;
	this.donneesIncendie = new ArrayList<Incendie>();
	for(Incendie i : fireData) {
	    donneesIncendie.add(i);
	}
	this.donneesRobot = new ArrayList<Robot>();
	for(Robot r : bot) {
	    donneesRobot.add(r);
	}
    }

    public DonneesSimulation(Carte mapData, List<Incendie> incendies, List<Robot> robots){
	this.donneesCarte = mapData;
	this.donneesIncendie = new ArrayList<Incendie>(incendies);
	this.donneesRobot = new ArrayList<Robot>(robots);
    }

    
    /*
     * Tester le plus cour chemin
     */
    public void testPlusCourtChemin(){
        Robot robot = new Roue(donneesCarte.getCaseAt(6,5));
        Graphe g = new Graphe(robot,donneesCarte);
        Dijkstra dijkstra = new Dijkstra(g);
        System.out.println(g);
        dijkstra.traiterGraphe(robot.getCase());
        // attention : robot.getCase() return a Casei
        Case destination = donneesCarte.getCaseAt(5,5);
        List<Case> chemin = dijkstra.getChemin(destination);
        List<Integer> t = dijkstra.getListTime(destination);
	System.out.println("\n Le plus court chemin: \n");
	System.out.println("Le temps nécessaire : "+ dijkstra.getTime(destination));
        try{
        for(Case cas : chemin){
            System.out.println(cas);
        }
	for(int time : t){
	    System.out.println(time);
	}
        } catch( Exception e){
            System.out.println("Can't get to "+ destination+" from this actual robot position :" +robot.getCase() );
        }

    } 
    /**
     * Affiche tous les donnees concernant la carte
     */
    public void afficheDonneesCarte(){
	System.out.println("Carte " + this.donneesCarte.getNbLigne() + "x" + this.donneesCarte.getNbColonne() + "; Taille des Cases = " + this.donneesCarte.getTailleCases());
	for(int i = 0; i < donneesCarte.getNbLigne(); i++){
	    for(int j = 0; j < donneesCarte.getNbColonne(); j++){
		Case box = donneesCarte.getCaseAt(i,j);
		System.out.println("Case(" + i + "," + j + "): Nature = " + box.getNatureType()); 
	    }
	}
    }

    /**
     * Affiche tous les donnees concernant les incendies
     */
    public void afficheDonneesIncendies(){
	System.out.println("Nb d'incendies :" + donneesIncendie.size());
	for(Incendie i : donneesIncendie ) {
	    System.out.println("Incendie " + i + ": position = (" + 
			       i.getCase().getPosition().getLigne() + "," + 
			       i.getCase().getPosition().getColonne() + "); Intensite :" 
			       + i.getNbLitresEauRestantPourExtinction() );
	}
    }

    /**
     * Affiche tous les donnees concernant les robots
     */
    public void afficheDonneesRobots(){
	System.out.println("Nb de Robots :" + donneesRobot.size());
	for(Robot r : donneesRobot) {
	    System.out.println("Robot " + r + ": position = (" + 
			       r.getCase().getPosition().getLigne() + "," + 
			       r.getCase().getPosition().getColonne() + "); Type :" 
			       + r.toString() + "; Vitesse :" + r.getVitesseDeplacementDefault() + " km/h");
	}
    }

    
    public Carte getCarte() {
	return donneesCarte;
    }
    
    /**
     * Retourne les incendies sur la carte
     *
     * @return La table des incendies
     */
    public List<Incendie> getIncendies() {
        return new ArrayList<Incendie>(donneesIncendie);
    }

    private static Incendie getIncendieAt(Case caze, List<Incendie> incendies) {
	for(Incendie inc : incendies) {
	    if(inc.getCase().getPosition().equals(caze.getPosition())){
		return inc;
	    }
	}
	return null;
    }

    /**
     * Retourne l'incendie présent sur la case <code>caze</code>.
     * Si il n'y a pas d'incendie sur cette case alors null est retourner.
     *
     * @param  caze la case ou l'incendie se trouve
     * @return l'incendie si présent, null sinon.
     */
    public Incendie getIncendieAt(Case caze) {
	return getIncendieAt(caze, getIncendies());
    }

 
    /**
     * Retourne les robots sur la carte
     * @return La table des robots
     */
    public List<Robot> getRobots() {
	return new ArrayList<Robot>(donneesRobot);
    }

}

