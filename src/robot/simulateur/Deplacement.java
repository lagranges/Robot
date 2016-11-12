package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.graph.*;
import robot.map.*;

import java.util.*;

public class Deplacement extends Evenement{
 
    private Direction dir;

    private int indice;

    public Deplacement(long date,int indice, Direction dir){
	super(date);
	this.dir = dir;
	this.indice = indice;
    }

    private Direction getDirection(){
	return this.dir;
    }

    private int getIndice(){
	return this.indice;
    }

    @Override
    public void execute(DonneesSimulation data){
	Position pos = new Position(data.getRobots()[getIndice()].getPosition().getPosition(), getDirection());

	if(data.getCarte().isInMapBound(pos)){
		//Si le robot peut se deplacer sur le nouvel terrain
		if(data.getRobots()[getIndice()].peutDeplacerSur(data.getCarte().getCaseAt(pos.getLigne(),pos.getColonne()).getNatureType())){
		    data.getRobots()[getIndice()].setPosition(data.getCarte().getCaseAt(pos.getLigne(),pos.getColonne()));
		}
	}
	
    }

    
}
