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

    public Carte getCarte() {
	return donneesCarte;
    }
    
    public Incendie[] getIncendies() {
	return donneesIncendie;
    }
 
    public Robot[] getRobots() {
	return donneesRobot;
    }

    private Incendie[] copyIncendies(){
	Incendie[] fire = new Incendie[getIncendies().length];
	for(int i=0; i < getIncendies().length; i++){
	    fire[i] = new Incendie(getIncendies()[i].getPosition(),getIncendies()[i].getNbLitresEauPourExtinction());
	}
	return fire;
    }

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

    public DonneesSimulation copy(){
	return new DonneesSimulation(getCarte(),copyIncendies(),copyRobots());
    }

    //Je ne sais pas où je dois mettre ces methodes ci-dessous -Hariz-

    public Direction whichDirection(Case before, Case after){
	int ligBefore = before.getPosition().getLigne();
	int colBefore = before.getPosition().getColonne();
	int ligAfter = after.getPosition().getLigne();
	int colAfter = after.getPosition().getColonne();
	if(ligAfter == ligBefore){
	    if(colAfter > colBefore){
		System.out.println("EST");
		return Direction.EST;
	    }else{
		System.out.println("OUEST");
		return Direction.OUEST;
	    }
	}else{
	    if(ligAfter > ligBefore){
		System.out.println("SUD");
		return Direction.SUD;
	    }else{
		System.out.println("NORD");
		return Direction.NORD;
	    }
	}
    }

    public void goToCourtChemin(Simulateur sim, int i, int DestLigne, int DestColonne){
	Graphe g = new Graphe(getRobots()[i],getCarte());
	Dijkstra dijkstra = new Dijkstra(g);
	dijkstra.traiterGraphe(getRobots()[i].getPosition());
	Case dest = getCarte().getCaseAt(DestLigne,DestColonne);
	List<Case> chemin = dijkstra.getChemin(dest);
	Case[] listChemin = chemin.toArray(new Case[chemin.size()]);
	List<Integer> t = dijkstra.getListTime(dest);
	Integer[] listT = t.toArray(new Integer[t.size()]);

	long dernierDate = 0;
	if(sim.getListeEvenement().length > 0){
	    dernierDate = sim.getListeEvenement()[sim.getListeEvenement().length-1].getDate();
	    dernierDate++;
	}

	try{
	    for(int j=0; j < listChemin.length - 1; j++){
		System.out.println("size: " + listChemin.length);
		sim.ajouteEvenement(new Deplacement(dernierDate + (long)listT[j+1],i,whichDirection(listChemin[j],listChemin[j+1]))); 
	    }
	    getRobots()[i].setPosition(dest);
        } catch( Exception e){
            System.out.println("Can't get to  from this actual robot position :" );
        }
    }

    public void interventionParSecond(Simulateur sim, int i){
	Robot bot = getRobots()[i];
	Case pos = bot.getPosition();
	long dernierDate = 0;

	if(sim.getListeEvenement().length > 0){
	    dernierDate = sim.getListeEvenement()[sim.getListeEvenement().length-1].getDate();
	    dernierDate++;
	}
	int j = 0;
	int k = bot.getVolumeEau();
	while(k > 0){
	    sim.ajouteEvenement(new Intervention(dernierDate + (long)j, i, (int)bot.getVitesseDeversement()));
	    k -= bot.getVitesseDeversement();
	    j++;
	}
    }

      public void remplirParSecond(Simulateur sim, int i){
	Robot bot = getRobots()[i];
	Case pos = bot.getPosition();
	long dernierDate = 0;

	if(sim.getListeEvenement().length > 0){
	    dernierDate = sim.getListeEvenement()[sim.getListeEvenement().length-1].getDate();
	    dernierDate++;
	}
	int j = 0;
	int k = 0;
	System.out.println(bot.getVolumeMax());
	while(k < bot.getVolumeMax()){
	    System.out.println("in");
		sim.ajouteEvenement(new Remplissage(dernierDate + (long)j, i, bot.getVitesseRemplissage()));
		k += bot.getVitesseRemplissage();
		j++;
	}
    }
}

