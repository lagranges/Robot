package robot.entities;

import java.awt.Color;
import robot.map.*;
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
	  
    public static final int VOLUME_MAX = 5000;
    public static final int VITESSE_REMPLISSAGE = 5000/600;
    public static final double VITESSE_DEVERSEMENT = 100/5;
    public static final double VITESSE_DEPLACEMENT = 80 ;
	
    /**
     * Le constructeur d'un robot de type roue avec les attributs déterminé au-dessus
     *
     * @param cas : la position du Robot a roue 
     * @see Robot
     * @see Entity
     */
    public Roue(Case cas){
	super(cas, VOLUME_MAX, VOLUME_MAX, VITESSE_DEPLACEMENT, VITESSE_REMPLISSAGE, VITESSE_DEVERSEMENT);
    }

    @Override
    public String toString(){
	return "Robot a roues";
    }
    
    // return 0 à Terrain sur la quelle le robot ne peut pas se déplacer
    @Override
    public double getVitesse(NatureTerrain natureTerrain){
	if(this.peutDeplacerSur(natureTerrain))	return VITESSE_DEPLACEMENT;
	else return 0;	
    }	
    
    @Override
    public boolean peutRemplirEau(Carte carte) {
        return carte.caseAProximite(getCase(),NatureTerrain.EAU);
    }
    
    @Override
    public boolean peutRemplirSurCaseEau() {
        return false;
    }
    
    @Override
    public boolean peutDeplacerSur(NatureTerrain natureTerrain) {
        switch (natureTerrain) {
            case TERRAIN_LIBRE:
            case HABITAT:
                return true;
            case EAU:
            case FORET:
            case ROCHE:
            default:
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
	Color teal = new Color(0,128,128);
        Color tealDarker = teal.darker();

	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*1, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*1, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*1, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*1, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*3, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*4, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, teal, teal, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*6, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*8, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*8, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*8, tealDarker, tealDarker, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*8, tealDarker, tealDarker, size));

	gui.addGraphicalElement(new Text(x + size*5, y + size*9, Color.darkGray, Double.toString(getVolumeEau())));
    }
}
