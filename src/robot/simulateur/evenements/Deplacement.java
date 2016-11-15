package robot.simulateur.evenements;

import robot.*;
import robot.io.*;
import robot.entities.Robot;

public class Deplacement extends EvenementRobot {
 
    /**
     * La direction du déplacement
     */
    private final Direction dir;

    /**
     * Crée un nouvelle évenement de type déplacement.
     */
    public Deplacement(long date, Robot robot, Direction dir){
	super(date, robot);
	this.dir = dir;
    }

    private Position positionSave = null;
    
    @Override
    public void execute(DonneesSimulation data){
        System.out.println("["+getDate()+"]Deplacement : " + getRobot() + " dir " + dir);
        positionSave = getRobot().getCase().getPosition();
	Position pos = new Position(getRobot().getCase().getPosition(), dir);
	if(data.getCarte().isInMapBound(pos)) {
	    //Si le robot peut se deplacer sur le nouvel terrain
	    if(getRobot().peutDeplacerSur(data.getCarte().getCaseAt(pos))){
		getRobot().setPosition(data.getCarte().getCaseAt(pos));
	    }
	}
    }

    @Override
    public void undo(DonneesSimulation data) {
        if(positionSave != null) {
            getRobot().setPosition(data.getCarte().getCaseAt(positionSave));
        }
    }
    
}
