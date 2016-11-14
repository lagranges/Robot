package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;
import robot.entities.Robot;

public class Remplissage extends EvenementRobot {
    
    private final int volEau;

    private enum Type{
	Drone,
	Chenille,
	Patte,
	Roue,
    }

    public Remplissage(long date, Robot robot, int volEau){
	super(date, robot);
	this.volEau = volEau;
    }
    
    private int getVolEau(){
	return this.volEau;
    }

    private boolean verifierEauACote(Case pos, Carte map){
	for(Direction dir : Direction.values()){
	    Position p = new Position(pos.getPosition(), dir);
	    if(map.getCaseAt(p).getNatureType() == NatureTerrain.EAU){ 
		return true;
	    }
	}
	return false;
    }

    @Override
    public void execute(DonneesSimulation data){
	Robot robot = getRobot();
	Case pos = robot.getCase();
	String type = robot.getClass().getSimpleName();
	Type typeValue = Type.valueOf(type);
	switch(typeValue){
	case Drone:
	    if(robot.getCase().getNatureType() == NatureTerrain.EAU){
		robot.remplirEau(getVolEau());
	    }
	    break;
	case Chenille:
	case Roue:
	    if(verifierEauACote(pos, data.getCarte())){
		robot.remplirEau(getVolEau());
	    }
	    break;
	case Patte:
	    break;
	}
    }
}
