package robot.entities;

import java.awt.Color;
import robot.map.*;
import gui.*;

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
    public void draw(GUISimulator gui, int ratio, int pixel){
	int x = this.cas.getPosition().getColonne() * ratio;
	int y = this.cas.getPosition().getLigne() * ratio;
	int size = ratio/pixel;

	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*1, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*2, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*2, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*9, y + size*3, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*3, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*3, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*4, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*4, Color.orange.darker(), Color.orange.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, Color.orange.darker(), Color.orange.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*5, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*5, Color.orange.darker(), Color.orange.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, Color.orange.darker(), Color.orange.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, Color.orange.darker(), Color.orange.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, Color.red.darker(), Color.red.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, Color.red.darker(), Color.red.darker(), size));

    } 
    
}
