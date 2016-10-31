package robot.entities;

import java.awt.Color;
import robot.map.*;
import gui.GUISimulator;

/**
 * Un incendie est défini par sa position (sur une case d'un carte),
 * et par le nombre de litres d'eau nécessaires pour l'éteindre.
 *
 * @see robot.map.Carte
 * @see robot.map.Case
 */
public class Incendie extends Entity {


    /**
     * Le coleur de l'Incendie , rouge par default
     */
	public static final Color incendieCouleur = new Color(255,0,0);

    /**
     * Le nombre de litres d'eau nécessaires à l'extinction de l'incendie.
     */
    private final int nbLitresEauPourExtinction;

    /**
     * Crée un nouvelle incendie, avec pour position la case <code>pos</code>
     * au coleur de <code>col</code> et nécessitant <code>nbLitres</code> litres 
     * d'eau pour son extinction.
     *
     * @param pos      la position de l'incendie.
     * @param nbLitres le nombre de litres d'eau nécessaires à son extinction.
     * @see            robot.map.Case
     */
    public Incendie(Case pos, int nbLitres) {
		super(pos,incendieCouleur);
		nbLitresEauPourExtinction = nbLitres;
    }


    /**
     * Le nombre de litre d'eau nécessaire à l'éxtinction de l'incendie.
     * La valeur retournée reflète une condition à l'état initial.
     *
     * @return le nombre de litres d'eau necéssaires.
     */
    public int getNbLitresEauPourExtinction() {
		return nbLitresEauPourExtinction;
    }

	@Override
    public void draw(GUISimulator gui){
	gui.addGraphicalElement(new Rectangle(x+4,y,incendiCouleur, incendieCouleur,2));

	gui.addGraphicalElement(new Rectangle(x+3,y+1,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+5,y+1,incendiCouleur, incendieCouleur,2));

	gui.addGraphicalElement(new Rectangle(x+2,y+2,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+4,y+2,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+6,y+2,incendiCouleur, incendieCouleur,2));
	
	gui.addGraphicalElement(new Rectangle(x+1,y+4,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+3,y+4,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+5,y+4,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+7,y+4,incendiCouleur, incendieCouleur,2));

	gui.addGraphicalElement(new Rectangle(x,y+6,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+2,y+6,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+4,y+6,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+6,y+6,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+8,y+6,incendiCouleur, incendieCouleur,2));

	gui.addGraphicalElement(new Rectangle(x+1,y+8,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+3,y+8,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+5,y+8,incendiCouleur, incendieCouleur,2));
	gui.addGraphicalElement(new Rectangle(x+7,y+8,incendiCouleur, incendieCouleur,2));

		gui.addGraphicalElement(new Rectangle(x+2,y+9,incendiCouleur, incendieCouleur,2));
		gui.addGraphicalElement(new Rectangle(x+4,y+9,incendiCouleur, incendieCouleur,2));
		gui.addGraphicalElement(new Rectangle(x+6,y+9,incendiCouleur, incendieCouleur,2));
} 

}
