package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;

public class Arrive extends EvenementRobot {
    
    public Arrive(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeplacement(false);
    }

    @Override
    public void undo(DonneesSimulation data) {
	getRobot().setIndicateurDeplacement(true);
    }

}
