package robot.entities;

import robot.map.*;
import gui.GUISimulator;
/**
 * Un  entité représente un élément présent sur une case, dans une carte.
 *
 * @see robot.map.case
 * @see robot.map.carte
 */
public abstract Entity {

    /**
     * La taille d'une case
     */
	public static final TAILLE = 10;
	
     private Case cas;
     private Color col;

    
    
    /**
     * Contructeur d'une nouvelle entité 
     * 
     * @param cas la cas sur laquelle elle se trouve
     * @param col la couleur de l'entité
     */
	public Entity(Case cas, Color col){
		this.cas = cas;
		this.col = col;
	}



    /**
     * Retourne la case de l'entité.
     * La position d'une entité est la case sur laquelle elle se trouve.
     *
     * @return la position de l'entité
     * @see    robot.map.Case
     */
	public Case getPostion(){
		return this.cas;
	}
	
	
    /**
     * Définit la case de l'entité
     */
	public void setPosition(Case cas){
		this.cas = cas;
	}


    /**
     * Draw un image represté l'entité sur une case
     */
	abstract void draw(GUISimulator gui);
}
