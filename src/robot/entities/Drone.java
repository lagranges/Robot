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
	public static final Color couleurDrone = Color.blue;	

	
	public Drone(Case cas){
		super(cas, couleurDrone, volumeMaxDrone, volumeMaxDrone, vitesseDeplacementDrone, vitesseRemplissageDrone, vitesseDeversementDrone);
	}
	
	@Override
	public double getVitesse(NatureTerrain natureTerrain){
		return vitesseDeversementDrone;
	}	

	@Override
	public boolean peutDeplacerSur(NatureTerrain natureTerrain){
		return true;
	}
	
	@Override
	public int tempsDeplacement(Case c1){}	

	@Override
	public void draw(GUISimulator gui){
		
		int x = this.cas.getPosition().getColonne();
		int y = this.cas.getPosition().getLigne();
		gui.addGraphicalElement(new Rectangle(x+1,y,couleurDrone, couleurDrone,1));
		gui.addGraphicalElement(new Rectangle(x+8,y,couleurDrone, couleurDrone,1));

		gui.addGraphicalElement(new Rectangle(x,y+1,couleurDrone, couleurDrone,1));
		gui.addGraphicalElement(new Rectangle(x+1,y+1,couleurDrone, couleurDrone,2));
		gui.addGraphicalElement(new Rectangle(x+3,y+1,couleurDrone, couleurDrone,1));
		gui.addGraphicalElement(new Rectangle(x+4,y+1,couleurDrone, couleurDrone,1));
		gui.addGraphicalElement(new Rectangle(x+5,y+1,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+1,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+7,y+1,couleurDrone, couleurDrone,2));
		gui.addgraphicalelement(new Rectangle(x+9,y+1,couleurDrone, couleurDrone,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+3,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+3,y+3,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+3,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+3,couleurDrone, couleurDrone,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+4,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+4,y+4,couleurDrone, couleurDrone,2));
		gui.addgraphicalelement(new Rectangle(x+8,y+4,couleurDrone, couleurDrone,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+5,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+5,couleurDrone, couleurDrone,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+6,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+3,y+6,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+6,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+6,couleurDrone, couleurDrone,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+7,couleurDrone, couleurDrone,2));
		gui.addgraphicalelement(new Rectangle(x+7,y+7,couleurDrone, couleurDrone,2));

		gui.addgraphicalelement(new Rectangle(x,y+8,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+3,y+8,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+4,y+8,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+5,y+8,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+8,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+9,y+8,couleurDrone, couleurDrone,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+9,couleurDrone, couleurDrone,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+9,couleurDrone, couleurDrone,1));
	}
}
