package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.entities.Robot;

public class Deplacement extends EvenementRobot {
 
    /**
     * La direction du déplacement
     */
    private final Direction dir;

    /**
     * le robot affecté par le déplacement
     */
    private final Robot robot;

    /**
     * Crée un nouvelle évenement de type déplacement.
     */
    public Deplacement(long date, Robot robot, Direction dir){
	super(date, robot);
	this.dir = dir;
	this.robot = robot;
    }

    @Override
    public void execute(DonneesSimulation data){
	Position pos = new Position(robot.getCase().getPosition(), dir);
	if(data.getCarte().isInMapBound(pos)) {
	    //Si le robot peut se deplacer sur le nouvel terrain
	    if(robot.peutDeplacerSur(data.getCarte().getCaseAt(pos))){
		robot.setPosition(data.getCarte().getCaseAt(pos));
	    }
	}
    }
}
