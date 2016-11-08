package robot.entities;

import java.awt.Color;
import java.lang.*;
import robot.map.*;
import gui.*;

//Remarque: c'est pas tres clair d'afficher la vitesse en km/s, soit km/h ou m/s c'est plus parlant
/**
 * Un robot avec la vitesse de remplissage = 2000/(60*5) l/s 
 * 		 la vitesse de déversement = 100/8 l/s
 *		 la vitesse de déplacement = (60*1000)/60 m/s
 * La vitesse est diminuée de 50% en foret, ne peut pas se rendre sur l'eau ou du rocher
 * se remplir à coté d'une case contenant de l'eau.
 */

public class Chenille extends Robot{  
	  
    public static final int volumeMaxChenille = 2000;
    public static final double vitesseRemplissageChenille = 2000/300;
    public static final double vitesseDeversementChenille = 100/8;
    public static final double vitesseDeplacementChenille = (60*1000)/60 ;
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
    public void draw(GUISimulator gui, int ratio, int pixel){
	int x = this.cas.getPosition().getColonne();
	int y = this.cas.getPosition().getLigne();
	gui.addGraphicalElement(new Rectangle(x+1,y,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+8,y,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x,y+1,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+1,y+1,couleurChenille, couleurChenille,2));
	gui.addGraphicalElement(new Rectangle(x+3,y+1,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+4,y+1,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+5,y+1,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+1,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+7,y+1,couleurChenille, couleurChenille,2));
	gui.addGraphicalElement(new Rectangle(x+9,y+1,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+3,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+3,y+3,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+3,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+3,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+4,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+4,y+4,couleurChenille, couleurChenille,2));
	gui.addGraphicalElement(new Rectangle(x+8,y+4,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+5,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+5,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+6,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+3,y+6,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+6,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+6,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+7,couleurChenille, couleurChenille,2));
	gui.addGraphicalElement(new Rectangle(x+7,y+7,couleurChenille, couleurChenille,2));

	gui.addGraphicalElement(new Rectangle(x,y+8,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+3,y+8,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+4,y+8,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+5,y+8,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+6,y+8,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+9,y+8,couleurChenille, couleurChenille,1));

	gui.addGraphicalElement(new Rectangle(x+1,y+9,couleurChenille, couleurChenille,1));
	gui.addGraphicalElement(new Rectangle(x+8,y+9,couleurChenille, couleurChenille,1));
    }
}
