package robot.entities;

import java.awt.Color;
import robot.map.*;
import java.lang.*;
import gui.*;

//Remarque: c'est pas tres clair d'afficher la vitesse en km/s, soit km/h ou m/s c'est plus parlant
/**
 * Un robot avec  
 * 		 la vitesse de déversement = 10/1 l/s
 *		 la vitesse de déplacement = 30*1000/60 m/s
 * La vitesse réduite à 10 km/s sur du rocher, ne peut pas se rendre sur de l'eau
 * Ne se remplit jamais
 */

public class Patte extends Robot{  
	  
    public static final int volumeMaxPatte = Integer.MAX_VALUE;
    public static final double vitesseRemplissagePatte = Integer.MAX_VALUE;
    public static final double vitesseDeversementPatte = 10/1;
    public static final double vitesseDeplacementPatte = (30*1000)/60 ;
    public static final double vitesseDeplacementPatteRoche = 10/60 ;
    public static final Color couleurPatte = Color.green;	

	
    /**
     * Le contructeur d'une Patte avec les atribut déterminé au-dessus
     *
     * @param cas : la position du Robot a Patte 
     * @see Robot
     * @see Entity
     */
    public Patte(Case cas){
	super(cas, couleurPatte, volumeMaxPatte, volumeMaxPatte, vitesseDeplacementPatte, vitesseRemplissagePatte, vitesseDeversementPatte);
    }

    @Override
    public String toString(){
	return "Robot a pattes";
    }
	
    // return 0 à Terrain sur la quelle le robot ne peut pas se déplacer
    @Override
    public double getVitesse(NatureTerrain natureTerrain){
	if(this.peutDeplacerSur(natureTerrain))	
	    if(natureTerrain.compareTo(NatureTerrain.ROCHE)>0)	return vitesseDeplacementPatteRoche;
	    else return vitesseDeplacementPatte;
	else return 0;	
    }	
	
    @Override
    public boolean peutDeplacerSur(NatureTerrain natureTerrain){
	switch (natureTerrain)
	    {
	    case EAU: return false; 
	    case FORET: return true;
	    case ROCHE: return true;
	    case TERRAIN_LIBRE: return true;
	    case HABITAT: return true;
	    default : return false;
	    }
    }
	
    @Override
    public int tempsDeplacement(Case c1){return 0;}	

    @Override
    public void draw(GUISimulator gui, int ratio, int smallest){
	int x = this.cas.getPosition().getColonne();
	int y = this.cas.getPosition().getLigne();
	gui.addGraphicalElement(new Rectangle(x+1,y,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+8,y,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x,y+1,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+1,y+1,couleurPatte, couleurPatte,2));
	gui.addGraphicalElement(new Rectangle(x+3,y+1,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+4,y+1,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+5,y+1,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+1,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+7,y+1,couleurPatte, couleurPatte,2));
	gui.addGraphicalElement(new Rectangle(x+9,y+1,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+3,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+3,y+3,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+3,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+3,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+4,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+4,y+4,couleurPatte, couleurPatte,2));
	gui.addGraphicalElement(new Rectangle(x+8,y+4,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+5,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+5,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+6,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+3,y+6,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+6,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+6,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+7,couleurPatte, couleurPatte,2));
	gui.addGraphicalElement(new Rectangle(x+7,y+7,couleurPatte, couleurPatte,2));

	gui.addGraphicalElement(new Rectangle(x,y+8,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+3,y+8,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+4,y+8,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+5,y+8,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+8,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+9,y+8,couleurPatte, couleurPatte,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+9,couleurPatte, couleurPatte,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+9,couleurPatte, couleurPatte,1));
    }
}
