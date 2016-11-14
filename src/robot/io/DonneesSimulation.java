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
    private Incendie donneesIncendie[];
    private Robot donneesRobot[];

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
	this.donneesIncendie = fireData;
	this.donneesRobot = bot;
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
	System.out.println("Nb d'incendies :" + donneesIncendie.length);
	for(int i = 0; i < donneesIncendie.length; i++){
	    System.out.println("Incendie " + i + ": position = (" + 
			       donneesIncendie[i].getPosition().getPosition().getLigne() + "," + 
			       donneesIncendie[i].getPosition().getPosition().getColonne() + "); Intensite :" 
			       + donneesIncendie[i].getNbLitresEauPourExtinction() );
	}
    }

    /**
     * Affiche tous les donnees concernant les robots
     */
    public void afficheDonneesRobots(){
	System.out.println("Nb de Robots :" + donneesRobot.length);
	for(int i = 0; i < donneesRobot.length; i++){
	    System.out.println("Robot " + i + ": position = (" + 
			       donneesRobot[i].getPosition().getPosition().getLigne() + "," + 
			       donneesRobot[i].getPosition().getPosition().getColonne() + "); Type :" 
			       + donneesRobot[i].toString() + "; Vitesse :" + donneesRobot[i].getVitesseDeplacementDefault() + " km/h");
	}
    }
    /**
     * Retourne la Carte 
     */
    public Carte getCarte() {
	return donneesCarte;
    }
    
    /**
     * Retourne les incendies sur la carte
     * @return La table des incendies
     */
    public Incendie[] getIncendies() {
	return donneesIncendie;
    }
 
    /**
     * Retourne les robots sur la carte
     * @return La table des robots
     */
    public Robot[] getRobots() {
	return donneesRobot;
    }
    /**
     * Retourne les incendies qui semblent aux incendies sur la carte 
     * @return Une table remblant la tables des incendies
     */
    private Incendie[] copyIncendies(){
	Incendie[] fire = new Incendie[getIncendies().length];
	for(int i=0; i < getIncendies().length; i++){
	    fire[i] = new Incendie(getIncendies()[i].getPosition(),getIncendies()[i].getNbLitresEauPourExtinction());
	}
	return fire;
    }

    /**
     * Retourne les robots qui semblent aux robots sur la carte 
     * @return Une table remblant la tables des robots
     */
    private Robot[] copyRobots(){
	Robot[] bot = new Robot[getRobots().length];
	for(int i=0; i < getRobots().length; i++){
	    String type = getRobots()[i].getClass().getSimpleName();
	    Type typeValue = Type.valueOf(type);
	    switch(typeValue){
	    case Drone:
		bot[i] = new Drone(getRobots()[i].getPosition());
		break;
	    case Chenille:
		bot[i] = new Chenille(getRobots()[i].getPosition());
		break;
	    case Roue:
		bot[i] = new Roue(getRobots()[i].getPosition());
		break;
	    case Patte:
		bot[i] = new Patte(getRobots()[i].getPosition());
		break;
	    }
	    bot[i].setVitesseDeplacement(getRobots()[i].getVitesseDeplacementDefault());
	}
	return bot;
    }

    /**
     * Retourne une donnée de simulation qui semble à la donnée de simulation sur la carte 
     * @return Une DonneesSimulation remblant la DonneesSimulation
     */
    public DonneesSimulation copy(){
	return new DonneesSimulation(getCarte(),copyIncendies(),copyRobots());
    }

}

