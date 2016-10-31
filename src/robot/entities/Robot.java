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

abstract class Robot extends Entity{	
      
	  
    /**
     * Le nombre de litres d'eau que le robot est en train de contient 
     */
	private int volumeEau;
	
    /**
     * Le nombre de litres d'eau que le robot peut contenir 
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
     * La vitesse de dérversement , unitaire : litre/s
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
     * Retourne le temps nécessaire pour se deplacer entre deux cases
     * n'est pas encore définir
     */
	abstract int tempsDeplacement(Case c1,Case c2);
    /**
     * Le nombre de litre d
     * La valeur retournée reflète une condition à l'état initial.
     *
     * @return le nombre de litres d'eau necéssaires.
     */
	abstract double getVitesse(NatureTerrain natureTerrain); 
}
