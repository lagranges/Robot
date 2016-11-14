package robot.io;

import robot.map.*;
import robot.entities.*;
import robot.*;
import robot.graph.*;
import robot.simulateur.*;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

import gui.GUISimulator;

public class DonneesSimulation{
    private Carte donneesCarte;
    private List<Incendie> donneesIncendie;
    private List<Robot> donneesRobot;

    private enum Type{
	Drone,
	Chenille,
	Patte,
	Roue,
    }
    
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
        dijkstra.traiterGraphe(robot.getPosition());
        // attention : robot.getPosition() return a Casei
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
            System.out.println("Can't get to "+ destination+" from this actual robot position :" +robot.getPosition() );
        }

    } 
    /*
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
			       i.getPosition().getPosition().getLigne() + "," + 
			       i.getPosition().getPosition().getColonne() + "); Intensite :" 
			       + i.getNbLitresEauPourExtinction() );
	}
    }

    /**
     * Affiche tous les donnees concernant les robots
     */
    public void afficheDonneesRobots(){
	System.out.println("Nb de Robots :" + donneesRobot.size());
	for(Robot r : donneesRobot) {
	    System.out.println("Robot " + r + ": position = (" + 
			       r.getPosition().getPosition().getLigne() + "," + 
			       r.getPosition().getPosition().getColonne() + "); Type :" 
			       + r.toString() + "; Vitesse :" + r.getVitesseDeplacementDefault() + " km/h");
	}
    }

    public Carte getCarte() {
	return donneesCarte;
    }
    
    public List<Incendie> getIncendies() {
	return new ArrayList<Incendie>(donneesIncendie);
    }

    private static Incendie getIncendieAt(Case caze, List<Incendie> incendies) {
	for(Incendie inc : incendies) {
	    if(inc.getPosition().equals(caze.getPosition())){
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

 
    public List<Robot> getRobots() {
	return new ArrayList<Robot>(donneesRobot);
    }

    private List<Incendie> copyIncendies(){
	List<Incendie> copy = new ArrayList<Incendie>();
	for(Incendie inc : getIncendies()) {
	    copy.add(new Incendie(inc.getPosition(),inc.getNbLitresEauPourExtinction()));
	}
	return copy;
    }

    private List<Robot> copyRobots(){
	List<Robot> copy = new ArrayList<Robot>();
	for(Robot rob : getRobots()) {
	    String type = rob.getClass().getSimpleName();
	    Type typeValue = Type.valueOf(type);
	    Robot cpy = null;
	    switch(typeValue){
	    case Drone:
		cpy = new Drone(rob.getPosition());
		break;
	    case Chenille:
		cpy = new Chenille(rob.getPosition());
		break;
	    case Roue:
		cpy = new Roue(rob.getPosition());
		break;
	    case Patte:
		cpy = new Patte(rob.getPosition());
		break;
	    }
	    if(cpy != null) {
		cpy.setVitesseDeplacement(rob.getVitesseDeplacementDefault());
		copy.add(cpy);
	    }
	}
	return copy;
    }

    public DonneesSimulation copy(){
	return new DonneesSimulation(getCarte(),copyIncendies(),copyRobots());
    }

}

