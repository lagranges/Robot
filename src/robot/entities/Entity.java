package robot.entities;

import robot.map.*;
import robot.gui.Drawable;

/**
 * Un  entité représente un élément présent sur une case, dans une carte.
 *
 * @see robot.map.case
 * @see robot.map.carte
 */
public abstract class Entity implements Drawable {

    private Case cas;

    /**
     * Constructeur d'une nouvelle entité 
     * 
     * @param cas la cas sur laquelle elle se trouve
     * @param col la couleur de l'entité
     */
    public Entity(Case cas){
	this.cas = cas;
    }
    
    /**
     * Retourne la case de l'entité.
     * La position d'une entité est la case sur laquelle elle se trouve.
     *
     * @return la position de l'entité
     * @see    robot.map.Case
     */
    public Case getCase(){
	return this.cas;
    }

    protected void setCase(Case cas) {
        this.cas = cas;
    }  
}
