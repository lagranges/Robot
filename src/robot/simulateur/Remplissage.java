package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class Remplissage extends Evenement{
    
    private int indice;

    private enum Type{
	Drone,
	Chenille,
	Patte,
	Roue,
    }

    public Remplissage(long date, int indice){
	super(date);
	this.indice = indice;
    }

    private int getIndice(){
	return this.indice;
    }

    private boolean verifierEauACote(Case pos, Carte map){
	for(Direction dir : Direction.values()){
	    Position p = new Position(pos.getPosition(), dir);
	    if(map.getCaseAt(p.getLigne(),p.getColonne()).getNatureType() == NatureTerrain.EAU){ 
		return true;
	    }
	}
	return false;
    }

    @Override
    public void execute(DonneesSimulation data){
	Case pos = data.getRobots()[getIndice()].getPosition();
	String type = data.getRobots()[getIndice()].getClass().getSimpleName();
	Type typeValue = Type.valueOf(type);
	switch(typeValue){
	case Drone:
	    if(data.getRobots()[getIndice()].getPosition().getNatureType() == NatureTerrain.EAU){
		data.getRobots()[getIndice()].remplirReservoir();
	    }
	    break;
	case Chenille:
	case Roue:
	    if(verifierEauACote(pos, data.getCarte())){
		data.getRobots()[getIndice()].remplirReservoir();
	    }
	    break;
	case Patte:
	    break;
	}
    }
}
