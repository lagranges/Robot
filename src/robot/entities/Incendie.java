package robot.entities;

import java.awt.Color;
import robot.map.*;
import robot.gui.BetterGUISimulator;
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
     * Le nombre de litres d'eau nécessaires à l'extinction de l'incendie.
     */
    private final int nbLitresEauPourExtinction;
    
    private int nbLitreRestantAvantExtinction;

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
	super(pos);
	nbLitresEauPourExtinction = nbLitres;
        nbLitreRestantAvantExtinction = nbLitresEauPourExtinction;
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
    
    public int getNbLitresEauRestantPourExtinction() {
	return nbLitreRestantAvantExtinction;
    }

    public void nbLitresEauArrive(double vol){
	this.nbLitreRestantAvantExtinction -= vol;
    }
    
    public boolean estEteint() {
        return nbLitreRestantAvantExtinction <= 0;
    }
    
    @Override
    public void draw(BetterGUISimulator gui){
	if(getNbLitresEauRestantPourExtinction() > 0){
	    int ratio = gui.getTailleCase();
	    int pixel = gui.getTaillePixel();
	    int x = getCase().getPosition().getColonne() * ratio;
	    int y = getCase().getPosition().getLigne() * ratio;
	    int size = ratio/pixel;
            Color red = Color.red;
            Color darkerRed = red.darker();
            Color orange = Color.orange.darker();
            
	    gui.addGraphicalElement(new Rectangle(x + size*6, y + size*1, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*6, y + size*2, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*4, y + size*2, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*9, y + size*3, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*6, y + size*3, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*7, y + size*3, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*8, y + size*4, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*7, y + size*4, orange, orange, size));
	    gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, orange, orange, size));
	    gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*8, y + size*5, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*7, y + size*5, orange, orange, size));
	    gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, orange, orange, size));
	    gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, orange, orange, size));
	    gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, darkerRed, darkerRed, size));
	    gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, darkerRed, darkerRed, size));

	    gui.addGraphicalElement(new Text(x + size*4, y - size, Color.white, Integer.toString(getNbLitresEauRestantPourExtinction())));
	} 
    }
}
