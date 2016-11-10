package robot.io;

import robot.map.*;
import robot.entities.*;
import robot.*;
import robot.graph.*;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

import gui.GUISimulator;

public class DonneesSimulation{
    private Carte donneesCarte;
    private Incendie donneesIncendie[];
    private Robot donneesRobot[];
    
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
    public void TestPlusCourtChemin(){
        Robot robot = new Patte(donneesCarte.getCaseAt(2,6));
        Graphe g = new Graphe(robot,donneesCarte);
        Dijkstra dijkstra = new Dijkstra(g);
        System.out.println(g);
        dijkstra.traiterGraphe(robot.getPosition());
        // attention : robot.getPosition() return a Casei
        Case destination = donneesCarte.getCaseAt(7,1);
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

    //partie get tous les donnees d'une carte

    public Carte getCarte() {
	return donneesCarte;
    }
    
    public int getCarteNbLigne(){
	return donneesCarte.getNbLigne();
    }

    public int getCarteNbColonne(){
	return donneesCarte.getNbColonne();
    }

    public int getCarteTailleCases() {
	return donneesCarte.getTailleCases();
    }

    public boolean isInMapBound(Position pos) {
	return donneesCarte.isInMapBound(pos);
    }

    public Case getCaseAt(Position pos) {
	return donneesCarte.getCaseAt(pos);
    }

    public boolean voisinExiste(Case source, Direction dir) {
	return donneesCarte.isInMapBound(source.getPosition().deplace(dir));
    }

    public Case getVoisin(Case source, Direction dir) {
	if(!voisinExiste(source,dir)) {
	    throw new IllegalArgumentException("Illegal request of Case outside of map's bounds");
	}
	return getCaseAt(source.getPosition().deplace(dir));
    }
    /*
    public void drawCarte(int ligne, int colonne, GUISimulator gui, int ratio, int pixel){
	donneesCarte.getCaseAt(ligne, colonne).draw(gui, ratio, pixel);
    }
*/
    //partie tous les donnees des incendies

    public Incendie[] getIncendies() {
	return donneesIncendie;
    }

    public int getNbIncendie(){
	return donneesIncendie.length;
    }
    
    /**
     * return la position de ieme incendie
     * @param i
     */
    public Case getIncendiePosition(int i){
	return donneesIncendie[i].getPosition();
    }
       

    /**
     * return nb litres eau pour extinction de ieme incendie
     * @param i
     */
    public int getIncendieNbLitresEauPourExtinction(int i) {
	return donneesIncendie[i].getNbLitresEauPourExtinction();
    }


    public int getNbRobots(){
	return donneesRobot.length;
    }

    public Robot[] getRobots() {
	return donneesRobot;
    }

    /**
     * Retourne : la volume de l'eau dans le ieme Robot
     * @param i
     */
    public int getRobotVolumeEau(int i){
	return donneesRobot[i].getVolumeEau();
    }

    /**
     * return la position de ieme robot
     * @param i
     */
    public Case getRobotPosition(int i){
	return donneesRobot[i].getPosition();
    }
       

    /**
     * Définit la case de ieme robot
     * @param i
     * @param cas
     */
    public void setRobotPosition(int i,Case cas){
	donneesRobot[i].setPosition(cas);
    }

    /**
     * Modifier la volume de l'eau dans le ieme robot
     * @param i
     * @param volumeEau
     */
    public void setRobotVolumeEau(int i,int volumeEau){
	donneesRobot[i].setVolumeEau(volumeEau);
    }

    /**
     * Modifier la vitesse de déplacement de ieme robot
     * @param i
     * @param vitesse
     */
    public void setRobotVitesseDeplacement(int i, int vitesse){
	donneesRobot[i].setVitesseDeplacement(vitesse);
    }

    /**
     * Retourne : la volume maximale de ieme dont le robot peut contenir
     * @param i
     */
    public int getRobotVolumeMax(int i){
	return donneesRobot[i].getVolumeMax();
    }

    /**
     * Retourne : la vitesse de déplacement par défault de ieme robot
     * @param i
     */
    public double getRobotVitesseDeplacementDefault(int i){
	return donneesRobot[i].getVitesseDeplacementDefault();
    }

    /**
     * Retourne : la vitesse de remplissage de ieme robot
     * @param i
     */
    public double getVitesseRemplissage(int i){
	return donneesRobot[i].getVitesseRemplissage();
    }

    /**
     * Retourne : la volume de déversement de ieme robot
     * @param i
     */
    public double getRobotVitesseDeversement(int i){
	return donneesRobot[i].getVitesseDeversement();
    }

    /**
     * Le ieme robot deverser sur un incendie
     * @param i
     * @param vol
     */
    public void robotDeverserEau(int i, int vol){
	donneesRobot[i].deverserEau(vol);
    }

	
    /**
     * Retourn le temps nécessaire pour deverser vol litres de l'eau, unitaire s de ieme robot
     * @param i
     * @param vol
     */
    public int getRobotTempsDeversement(int i,int vol){
	return donneesRobot[i].tempsDeversement(vol);
    }

    /**
     * Le ieme robot remplit son réservoir 
     * @param i
     */
    public void RobotRemplirReservoir(int i){
	donneesRobot[i].remplirReservoir();
    }

    /**
     * Retourne le temps nécessaire pour remplir completement, unitaire s de ieme robot
     * @param i
     */
    public int getRobotTempsRemplissage(int i){
	return donneesRobot[i].tempsRemplissage();
    }

}
