package robot.entities;

import java.awt.Color;
import robot.map.*;
import gui.*;
import robot.gui.BetterGUISimulator;

//Remarque: c'est pas tres clair d'afficher la vitesse en km/s, soit km/h ou m/s c'est plus parlant
/**
 * Un robot avec la vitesse de remplissage = 10000/(60*30) l/s 
 * 		 la vitesse de déversement = 10000/30 l/s
 *		 la vitesse de déplacement = 100 km/h
 * se déplacer sur tous les case, à vitesse constante,
 * se remplir sur une case contenant de l'eau.
 */

public class Drone extends Robot{
	
    /**
     *
     *
     *
     */
    public static final int volumeMaxDrone = 10000;
    public static final double vitesseRemplissageDrone = 10000/1800;
    public static final double vitesseDeversementDrone = 10000/30;
    public static final double vitesseDeplacementDrone = 100 ;
    public static final Color couleurDrone = Color.blue;	

	
    public Drone(Case cas){
	super(cas, couleurDrone, volumeMaxDrone, volumeMaxDrone, vitesseDeplacementDrone, vitesseRemplissageDrone, vitesseDeversementDrone);
    }
    
    @Override
    public String toString(){
	return "Drone";
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
    public int tempsDeplacement(Case c1){return 0;}	

    @Override
    public void draw(BetterGUISimulator gui){	    
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = this.cas.getPosition().getColonne() * ratio;
	int y = this.cas.getPosition().getLigne() * ratio;
	int size = ratio/pixel;
	Color magenta = new Color(139,0,139);

	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*1, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*1, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*1, y + size*1, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*1, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*2, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*2, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*3, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*3, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*6, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*6, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*7, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*7, magenta, magenta, size));
	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*8, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*8, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*8, magenta.darker(), magenta.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*1, y + size*8, magenta.darker(), magenta.darker(), size));

    }
}
