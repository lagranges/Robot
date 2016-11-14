package robot.entities;

import java.awt.Color;
import robot.map.*;
import java.lang.*;
import robot.gui.BetterGUISimulator;
import gui.*;

//Remarque: c'est pas tres clair d'afficher la vitesse en km/s, soit km/h ou m/s c'est plus parlant
/**
 * Un robot avec  
 * 		 la vitesse de déversement = 10/1 l/s
 *		 la vitesse de déplacement = 30 km/h
 * La vitesse réduite à 10 km/s sur du rocher, ne peut pas se rendre sur de l'eau
 * Ne se remplit jamais
 */

public class Patte extends Robot{  
	  
    public static final int volumeMaxPatte = Integer.MAX_VALUE;
    public static final int vitesseRemplissagePatte = Integer.MAX_VALUE;
    public static final double vitesseDeversementPatte = 10;
    public static final double vitesseDeplacementPatte = 30;
    public static final double vitesseDeplacementPatteRoche = 10;
    public static final Color couleurPatte = Color.green;	

	
    /**
     * Le contructeur d'une Patte avec les atribut déterminé au-dessus
     *
     * @param cas : la position du Robot a Patte 
     * @see Robot
     * @see Entity
     */
    public Patte(Case cas){
	super(cas, volumeMaxPatte, volumeMaxPatte, vitesseDeplacementPatte, vitesseRemplissagePatte, vitesseDeversementPatte);
    }

    @Override
    public String toString(){
	return "Robot a pattes";
    }
	
    // return 0 à Terrain sur la quelle le robot ne peut pas se déplacer
    @Override
    public double getVitesse(NatureTerrain natureTerrain){
	if(this.peutDeplacerSur(natureTerrain))	
	    if(natureTerrain.equals(NatureTerrain.ROCHE))	return vitesseDeplacementPatteRoche;
	    else return vitesseDeplacementPatte;
	else return 0;	
    }	
	
    @Override
    public boolean peutDeplacerSur(NatureTerrain natureTerrain){
	switch (natureTerrain) {
	    case FORET:
	    case ROCHE:
	    case TERRAIN_LIBRE: 
	    case HABITAT: 
                return true;
            case EAU:
	    default : 
                return false;
	}
    }
	
    @Override
    public int tempsDeplacement(Case c1){return 0;}	

    @Override
    public void draw(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = getCase().getPosition().getColonne() * ratio;
	int y = getCase().getPosition().getLigne() * ratio;
	int size = ratio/pixel;
	Color redIndian = new Color(205,92,92);

	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*1, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*1, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*1, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*1, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*3, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*4, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*4, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*4, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, redIndian, redIndian, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*6, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*6, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*8, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*8, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*8, redIndian.darker(), redIndian.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*8, redIndian.darker(), redIndian.darker(), size));

	gui.addGraphicalElement(new Text(x + size*5, y + size*9, Color.darkGray, "Infini"));
    }
}
