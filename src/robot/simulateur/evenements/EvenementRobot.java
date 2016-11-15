package robot.simulateur.evenements;

import robot.entities.Robot;

public abstract class EvenementRobot extends Evenement {
   
    private final Robot robot;
   
    public EvenementRobot(long date,Robot robot){
	super(date);
	this.robot = robot;
    }

    public Robot getRobot(){
	return this.robot;
    }

}
