package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;

public class StartDeversement extends EvenementRobot {
    
    public StartDeversement(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeversement(true);
    }

    @Override
    public void undo(DonneesSimulation data) {
	getRobot().setIndicateurDeversement(false);
    }

}
