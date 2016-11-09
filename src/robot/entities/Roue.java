package robot.entities;

import java.awt.Color;
import robot.map.*;
import java.lang.*;
import gui.*;
import robot.gui.BetterGUISimulator;

//Remarque: c'est pas tres clair d'afficher la vitesse en km/s, soit km/h ou m/s c'est plus parlant
/**
 * Un robot avec la vitesse de remplissage = 5000/(60*10) l/s 
 * 		 la vitesse de déversement = 100/5 l/s
 *		 la vitesse de déplacement = 80 km/s 
 * Ne peut se déplacer que sur du terrain libre ou habitat,
 * se remplir à coté d'une case contenant de l'eau.
 */

public class Roue extends Robot{  
	  
    public static final int volumeMaxRoue = 5000;
    public static final double vitesseRemplissageRoue = 5000/600;
    public static final double vitesseDeversementRoue = 100/5;
    public static final double vitesseDeplacementRoue = 80 ;
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

    @Override
    public String toString(){
	return "Robot a roues";
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
	    case EAU: return false; 
	    case FORET: return false;
	    case ROCHE: return false;
	    case TERRAIN_LIBRE: return true;
	    case HABITAT: return true;
	    default:return false;
	    }
    }
    
    @Override
    public int tempsDeplacement(Case c1){return 0;}	

    @Override
    public void draw(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = this.cas.getPosition().getColonne() * ratio;
	int y = this.cas.getPosition().getLigne() * ratio;
	int size = ratio/pixel;
	Color teal = new Color(0,128,128);

	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*1, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*1, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*1, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*1, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*3, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*4, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*6, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*8, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*8, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*8, teal.darker(), teal.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*8, teal.darker(), teal.darker(), size));

    }
}
