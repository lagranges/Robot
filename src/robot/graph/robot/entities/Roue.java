
package robot.entities;

import java.awt.Color;
import robot.map.*;
import java.lang.*;
import gui.GUISimulator;

/**
 * Un robot avec la vitesse de remplissage = 5000/(60*10) l/s 
 * 		 la vitesse de déversement = 100/5 l/s
 *		 la vitesse de déplacement = 80/60 km/s
 * Ne peut se déplacer que sur du terrain libre ou habitat,
 * se remplir à coté d'une case contenant de l'eau.
 */

public class Roue extends Robot{  
	  
	public static final double volumeMaxRoue = 5000;
	public static final double vitesseRemplissageRoue = 5000/600;
	public static final double vitesseDeversementRoue = 100/5;
	public static final double vitesseDeplacementRoue = 80/60 ;
	public static final Color couleurRoue = Color.yellow;	

	
	/**
	  * Le contructeur d'une Roue avec les atribut déterminé au-dessus
	  *
	  * @param cas : la position du Robot a roue 
	  * @see Robot
	  * @see Entity
	  */
	public Roue(Case cas){
		super(cas, couleurRoue, volumeMaxRoue, volumeMaxRoue, vitesseDeplacementRoue, vitesseRemplissageRoue, vitesseDeversementRoue);
	}
	
	// return 0 à Terrain sur la quelle le robot ne peut pas se déplacer
	@Override
	public double getVitesse(NatureTerrain natureTerrain){
		if(this.peutDeplacerSur(natureTerrain))	return vitesseDeplacementRoue;
		else return 0;	
	}	
	
	@Override
	public boolean peutDeplacerSur(NatureTerrain natureTerrain){
		switch (natureTerrain)
		{
			case NatureTerrain.EAU: return false; 
			case NatureTerrain.FORET: return false;
			case NatureTerrain.ROCHE: return false;
			case NatureTerrain.TERRAIN_LIBRE: return true;
			case NatureTerrain.HABITAT: return true;
		}
	}
	
	@Override
	public int tempsDeplacement(Case c1){}	

	@Override
	public void draw(GUISimulator gui){
		
		int x = this.cas.getPosition().getColonne();
		int y = this.cas.getPosition().getLigne();
		gui.addGraphicalElement(new Rectangle(x+1,y,couleurRoue, couleurRoue,1));
		gui.addGraphicalElement(new Rectangle(x+8,y,couleurRoue, couleurRoue,1));

		gui.addGraphicalElement(new Rectangle(x,y+1,couleurRoue, couleurRoue,1));
		gui.addGraphicalElement(new Rectangle(x+1,y+1,couleurRoue, couleurRoue,2));
		gui.addGraphicalElement(new Rectangle(x+3,y+1,couleurRoue, couleurRoue,1));
		gui.addGraphicalElement(new Rectangle(x+4,y+1,couleurRoue, couleurRoue,1));
		gui.addGraphicalElement(new Rectangle(x+5,y+1,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+1,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+7,y+1,couleurRoue, couleurRoue,2));
		gui.addgraphicalelement(new Rectangle(x+9,y+1,couleurRoue, couleurRoue,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+3,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+3,y+3,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+3,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+3,couleurRoue, couleurRoue,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+4,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+4,y+4,couleurRoue, couleurRoue,2));
		gui.addgraphicalelement(new Rectangle(x+8,y+4,couleurRoue, couleurRoue,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+5,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+5,couleurRoue, couleurRoue,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+6,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+3,y+6,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+6,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+6,couleurRoue, couleurRoue,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+7,couleurRoue, couleurRoue,2));
		gui.addgraphicalelement(new Rectangle(x+7,y+7,couleurRoue, couleurRoue,2));

		gui.addgraphicalelement(new Rectangle(x,y+8,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+3,y+8,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+4,y+8,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+5,y+8,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+6,y+8,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+9,y+8,couleurRoue, couleurRoue,1));

		gui.addgraphicalelement(new Rectangle(x+1,y+9,couleurRoue, couleurRoue,1));
		gui.addgraphicalelement(new Rectangle(x+8,y+9,couleurRoue, couleurRoue,1));
	}
}
