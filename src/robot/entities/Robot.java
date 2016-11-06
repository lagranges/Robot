package robot.entities;

import java.awt.Color;
import robot.map.*;
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
	private int volumeEau;
	
    /**
     * Le nombre de litres d'eau dont le robot peut contenir 
     */
	private int volumeMax;
    
    /**
     * La vitesse par défault, mais peut etre lue dans la fichier de données
     */
	private double vitesseDeplacementDefault;
	 
    /**
     * La vitesse de remplissage , unitaire : litre/s
     */
	private double vitesseRemplissage;
    
    /**
     * La vitesse de déversement , unitaire : litre/s
     */
	private double vitesseDeversement;
	
	/**
     * Contructeur d'un nouveau Robot 
     * 
     * @param cas 		la cas sur laquelle il se trouve
     * @param couleur   	la couleur du robot
     * @param volumeEau 	la nombre de litres qu'il contient
     * @param lesvitesse
     * 
     */
	public Robot(Case cas, Color couleur, int volumeEau, int volumeMax, double vitesseDeplacementDefault,double vitesseRemplissage, double vitesseDeversement){
		super(cas,couleur);
		this.volumeEau = volumeEau;
		this.volumeMax = volumeMax;
		this.vitesseDeplacementDefault = vitesseDeplacementDefault;
		this.vitesseRemplissage = vitesseRemplissage;
		this.vitesseDeversement = vitesseDeversement;
	}
	

    /**
     * Retourne : la volume de l'eau dans le Robot
     */
	public int getVolumeEau(){
		return this.volumeEau;
	}

    /**
     * Définit la case du robot
     */
	public void setPosition(Case cas){
		this.cas = cas;
	}
    /**
     * Modifier la volume de l'eau dans le robot
     */
	public void setVolumeEau(int volumeEau){
		this.volumeEau=volumeEau;
	}

    /**
     * Modifier la vitesse de déplacement du robot
     */
	public void setVitesseDeplacement(int vitesse){
	    this.vitesseDeplacementDefault = (vitesse*1000)/60;
	}

    /**
     * Retourne : la volume maxcimale dont le robot peut contenir
     */
	public int getVolumeMax(){
		return this.volumeMax;
	}

    /**
     * Retourne : la vitesse de déplacement par défault du robot
     */
	public double getVitesseDeplacementDefault(){
		return this.vitesseDeplacementDefault;
	}

    /**
     * Retourne : la vitesse de remlissage du robot
     */
	public double getVitesseRemplissage(){
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
	public void deverserEau(int vol){
		volumeEau -= vol;
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
	abstract boolean peutDeplacerSur(NatureTerrain natureTerrain);

	/**
	  * Retourne la vitesse du robot sur les Terrains différents
	  *
	  * @param natureTerrain : la type de natureTerrain
	  * @see NatureTerrain
	  */
	abstract double getVitesse(NatureTerrain natureTerrain); 


    /**
     * Retourne le temps nécessaire pour se deplacer de sa possition
     * à la case , n'est pas encore définir
     * @param cas : Case
     */
	abstract int tempsDeplacement(Case cas);

}
