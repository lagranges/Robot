package robot.entities;

import java.awt.Color;
import java.lang.*;
import robot.map.*;
import robot.gui.BetterGUISimulator;
import gui.*;


//Remarque: c'est pas tres clair d'afficher la vitesse en km/s, soit km/h ou m/s c'est plus parlant
/**
 * Un robot avec la vitesse de remplissage = 2000/(60*5) l/s 
 * 		 la vitesse de déversement = 100/8 l/s
 *		 la vitesse de déplacement = 60 km/s
 * La vitesse est diminuée de 50% en foret, ne peut pas se rendre sur l'eau ou du rocher
 * se remplir à coté d'une case contenant de l'eau.
 */

public class Chenille extends Robot{  
	  
    public static final int volumeMaxChenille = 2000;
    public static final double vitesseRemplissageChenille = 2000/300;
    public static final double vitesseDeversementChenille = 100/8;
    public static final double vitesseDeplacementChenille = 60;
    public static final Color couleurChenille = Color.gray;	

	
    /**
     * Le contructeur d'une Chenille avec les atribut déterminé au-dessus
     *
     * @param cas : la position du Robot a Chenille 
     * @see Robot
     * @see Entity
     */
    public Chenille(Case cas){
	super(cas, couleurChenille, volumeMaxChenille, volumeMaxChenille, vitesseDeplacementChenille, vitesseRemplissageChenille, vitesseDeversementChenille);
    }
    
    @Override
    public String toString(){
	return "Robot a chenilles";
    }
	
    // return 0 à Terrain sur la quelle le robot ne peut pas se déplacer
    @Override
    public double getVitesse(NatureTerrain natureTerrain){
	if(this.peutDeplacerSur(natureTerrain))	
	    if(natureTerrain.compareTo(NatureTerrain.FORET)>0)	return vitesseDeplacementChenille/2;
	    else return vitesseDeplacementChenille;
	else return 0;	
    }	
	
    @Override
    public boolean peutDeplacerSur(NatureTerrain natureTerrain){
	switch (natureTerrain)
	    {
	    case EAU: return false; 
	    case FORET: return true;
	    case ROCHE: return false;
	    case TERRAIN_LIBRE: return true;
	    case HABITAT: return true;
	    default : return false;
	    }
    }
	
    @Override
    public int tempsDeplacement(Case c1){return 0;}	

    @Override
    public void draw(BetterGUISimulator gui) {
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = this.cas.getPosition().getColonne() * ratio;
	int y = this.cas.getPosition().getLigne() * ratio;
	int size = ratio/pixel;
	Color peru = new Color(205,133,63);

	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*2, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*3, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*3, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*4, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, peru.darker(), peru.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*6, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*6, peru, peru, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, peru.darker(), peru.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*7,  Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*7, Color.black, Color.black, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*7, Color.black, Color.black, size));

	gui.addGraphicalElement(new Text(x + size*5, y + size*9, Color.darkGray, Integer.toString(getVolumeEau())));
    }
}
