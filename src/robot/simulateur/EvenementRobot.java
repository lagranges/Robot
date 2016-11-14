package robot.simulateur;

import robot.simulateur.Evenement;
import robot.entities.Robot;
import robot.io.*;

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