package robot.entities;

import robot.simulateur.evenements.Remplissage;
import robot.simulateur.evenements.Deplacement;
import robot.simulateur.evenements.Evenement;
import java.awt.Color;
import java.util.*;

import robot.map.*;
import robot.graph.*;
import robot.simulateur.*;
import robot.io.*;
import robot.*;

import gui.GUISimulator;

/**
 * Un robot présente sur une case, dans une carte.
 *
 * @see robot.map.case
 * @see robot.map.carte
 */

public abstract class Robot extends Entity {	
      
	  
    /**
     * Le nombre de litres d'eau dont le robot est en train de contient 
     */
    private double volumeEau;
	
    /**
     * Le nombre de litres d'eau dont le robot peut contenir 
     */
    private double volumeMax;
    
    /**
     * La vitesse par défault, mais peut etre lue dans la fichier de données
     */
    private double vitesseDeplacementDefault;
	 
    /**
     * La vitesse de remplissage , unitaire : litre/s
     */
    private int vitesseRemplissage;
    
    /**
     * La vitesse de déversement , unitaire : litre/s
     */
    private double vitesseDeversement;
	
    /**
     * L'indicateur boolean si le robot est en train de se deplacer
     */
    private boolean enTrainDeSeDeplacer = false;

    /**
     * L'indicateur boolean si le robot est en train de deversement
     */
    private boolean enTrainDeDeversement = false;

    /**
     * L'indicateur boolean si le robot est en train de remplir
     */
    private boolean enTrainDeRemplir = false;

    /**
     * Contructeur d'un nouveau Robot 
     * 
     * @param cas 		la cas sur laquelle il se trouve
     * @param couleur   	la couleur du robot
     * @param volumeEau 	la nombre de litres qu'il contient
     * @param lesvitesse
     * 
     */
    public Robot(Case cas, int volumeEau, int volumeMax, double vitesseDeplacementDefault,int vitesseRemplissage, double vitesseDeversement){
	super(cas);
	this.volumeEau = volumeEau;
	this.volumeMax = volumeMax;
	this.vitesseDeplacementDefault = vitesseDeplacementDefault;
	this.vitesseRemplissage = vitesseRemplissage;
	this.vitesseDeversement = vitesseDeversement;
    }

    /**
     * Retourne : la volume de l'eau dans le Robot
     */
    public double getVolumeEau(){
	return this.volumeEau;
    }

    /** 
     * Retourne : L'indicatuer en train de seplacer
     */
    public boolean getIndicateurDeplacement(){
	return this.enTrainDeSeDeplacer;
    }


    /** 
     * Définit : L'indicateur en train de seplacer
     */
    public void setIndicateurDeplacement(boolean bool){
	this.enTrainDeSeDeplacer = bool;
    }

    /** 
     * Retourne : L'indicateur en train de deversement
     */
    public boolean getIndicateurDeversement(){
	return this.enTrainDeDeversement;
    }


    /** 
     * Définit : L'indicateur en train de deversement
     */
    public void setIndicateurDeversement(boolean bool){
	this.enTrainDeDeversement = bool;
    }

    /** 
     * Retourne : L'indicatuer en train de remplir
     */
    public boolean getIndicateurRemplir(){
	return this.enTrainDeRemplir;
    }


    /** 
     * Définit : L'indicateur en train de remplir
     */
    public void setIndicateurRemplir(boolean bool){
	this.enTrainDeRemplir = bool;
    }

    /**
     * Définit la case du robot
     */
    public void setPosition(Case cas){
	this.setCas(cas);
    }

    /**
     * Modifier la volume de l'eau dans le robot
     */
    public void setVolumeEau(double volumeEau){
	this.volumeEau=volumeEau;
    }

    /**
     * Modifier la vitesse de déplacement du robot
     */
    public void setVitesseDeplacement(double vitesse){
	this.vitesseDeplacementDefault = vitesse;
    }

    /**
     * Retourne : la volume maxcimale dont le robot peut contenir
     */
    public double getVolumeMax(){
	return this.volumeMax;
    }

    /**
     * Retourne : la vitesse de déplacement par défault du robot
     */
    public double getVitesseDeplacementDefault(){
	return this.vitesseDeplacementDefault;
    }

    /**
     * Retourne : la vitesse de remplissage du robot
     */
    public int getVitesseRemplissage(){
	return this.vitesseRemplissage;
    }

    /**
     * Retourne : la volume de déversement du robot
     */
    public double getVitesseDeversement(){
	return this.vitesseDeversement;
    }


    /**
     * Le robot deverser sur un incendie
     *
     * @param la nombre de litres qu'il se faut deverser
     */
    public void deverserEau(double vol){
	volumeEau -= vol;
    }

    /**
     * Le robot remplir le reservoir par second
     *
     * @param le nombre de litre par second
     */
    public void remplirEau(double vol){
	volumeEau += vol;
	if(volumeEau > getVolumeMax()){
	    volumeEau = getVolumeMax();
	}
    }
	
    /**
     * Retourne le temps nécessaire pour deverser vol litres de l'eau, unitaire s
     *
     * @param la nombre de litres qu'il se faut deverser
     */
    public int tempsDeversement(int vol){
	return (int) (vol/vitesseDeversement);
    }

    /**
     * Le robot remplit son réservoir 
     */
    public void remplirReservoir(){
	volumeEau = volumeMax;
    }

    /**
     * Retourne le temps nécessaire pour remplir completement, unitaire s
     *
     */
    public int tempsRemplissage(){
	return (int) ((volumeMax-volumeEau)/vitesseRemplissage);
    }


    /**
     * Retourne True si le robot peut se déplacer sur la nature Terrain
     * sinon retourne False
     * @param natureTerrain
     * @see NatureTerrain 
     */
    public boolean peutDeplacerSur(Case cas){
        return peutDeplacerSur(cas.getNatureType());
    }
    
    public double getVitesse(Case cas){
        return getVitesse(cas.getNatureType());
    }
    public abstract boolean peutDeplacerSur(NatureTerrain natureTerrain);

    /**
     * Retourne la vitesse du robot sur les Terrains différents
     *
     * @param natureTerrain : la type de natureTerrain
     * @see NatureTerrain
     */
    public abstract double getVitesse(NatureTerrain natureTerrain); 


    /**
     * Retourne le temps nécessaire pour se deplacer de sa possition
     * à la case , n'est pas encore définir
     * @param cas : Case
     */
    public abstract int tempsDeplacement(Case cas);

    /**
     * Trouver le chemin possible
     * retourne NULL s'il ne trouve pas
     */
    public List<Case> calculPlusCourtChemin(DonneesSimulation data, Case destination, int numero){
	Graphe g = new Graphe(data.getRobots().get(numero),data.getCarte());
	Dijkstra dijkstra = new Dijkstra(g);
	dijkstra.traiterGraphe(data.getRobots().get(numero).getCase());
	return dijkstra.getChemin(destination);
    }


    /**
     * Ajoute des evenement de deplacement dans la liste d'evenement par rapport de vitesse de simulateur
     */
    public void programEventDeplacement(Simulateur sim, DonneesSimulation data, Case destination, int numero){
	Graphe g = new Graphe(data.getRobots().get(numero),data.getCarte());
	Dijkstra dijkstra = new Dijkstra(g);
	dijkstra.traiterGraphe(data.getRobots().get(numero).getCase());
	List<Case> chemin = dijkstra.getChemin(destination);
        List<Integer> t = dijkstra.getListTime(destination);
       
	long currentDate = 0;
        Evenement lastE = sim.getLastEvenement(this);
        if(lastE != null) {
            currentDate= lastE.getDate();
        }

	try{
	    Case[] listC = chemin.toArray(new Case[chemin.size()]);
	    Integer[] listT = t.toArray(new Integer[t.size()]);

	    for(int i=0; i < listC.length - 1; i++){
		sim.ajouteEvenement(new Deplacement(currentDate + (long)listT[i+1], this, Position.getDirection(listC[i].getPosition(),
														listC[i+1].getPosition()))); 
	    }
	    setPosition(destination);
        } catch( Exception e){
            System.out.println("Can't get to  from this actual robot position :" );
        }
    }
    
    public void programEventRemplissage(Simulateur sim, int numero){
	long currentDate = 0;
        Evenement lastE = sim.getLastEvenement(this);
        if(lastE != null) {
            currentDate= lastE.getDate();
        }

	int volCurrent = 0;
	int i = 0;
    
	while(volCurrent < getVolumeMax()){
	    sim.ajouteEvenement(new Remplissage(currentDate + i, this, getVitesseRemplissage()));
	    volCurrent += getVitesseRemplissage();
	    i++;
	}

    }

    public void programEventIntervention(Simulateur sim, DonneesSimulation data, int numero){
	long currentDate = 0;
        Evenement lastE = sim.getLastEvenement(this);
        if(lastE != null) {
            currentDate= lastE.getDate();
        }
	
	Incendie inc = data.getIncendieAt(getCase());
	if (inc != null) {
            double volCurrent = getVolumeEau();
            int intensite = inc.getNbLitresEauPourExtinction();
            int i = 0;

            while ((volCurrent > 0) && (intensite > 0)) {
                sim.ajouteEvenement(new Intervention(currentDate + i, this, getVitesseDeversement()));
                volCurrent -= getVitesseDeversement();
                intensite -= getVitesseDeversement();
                i++;
            }
        }
    }
}
