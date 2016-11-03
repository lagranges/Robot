package robot.entities;

import robot.*;
import robot.map.*;
import gui.GUISimulator;
import java.awt.Color;
/**
 * Un  entité représente un élément présent sur une case, dans une carte.
 *
 * @see robot.map.case
 * @see robot.map.carte
 */
public abstract class Entity implements Drawable{

    /**
     * La taille d'une case
     */
	public static final int  TAILLE = 10;
	
    protected Case cas;
    protected Color col;

    
    
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


}
