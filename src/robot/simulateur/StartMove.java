package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;

public class StartMove extends EvenementRobot {
    
    public StartMove(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeplacement(true);
    }

    @Override
    public void undo(DonneesSimulation data) {
        getRobot().setIndicateurDeplacement(false);
    }

}
