package robot.entities;

import java.awt.Color;
import robot.map.*;
import gui.GUISimulator;

/**
 * Un robot avec la vitesse de remplissage = 10000/(60*30) l/s 
 * 		 la vitesse de déversement = 10000/30 l/s
 *		 la vitesse de déplacement = 100/60 km/s
 * se déplacer sur tous les case, à vitesse constante,
 * se remplir sur une case contenant de l'eau.
 */

public class Drone extends Robot{
	
	/**
	  *
	  *
	  *
	  */
	public static final double volumeMaxDrone = 10000;
	public static final double vitesseRemplissageDrone = 10000/1800;
	public static final double vitesseDeversementDrone = 10000/30;
	public static final double vitesseDeplacementDrone = 100/60 ;
	public static final Color couleurDrone = Color.green;	

	
	public Drone(Case cas){
		super(cas, couleurDrone, volumeMaxDrone, volumeMaxDrone, vitesseDeplacementDrone, vitesseRemplissageDrone, vitesseDeversementDrone);
	}
	
	@Override
	public double getVitesse(NatureTerrain natureTerrain){
	}	

	@Override
	public void draw(GUISimulator gui){
		
	}
}
