package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;

public class EndDeversement extends EvenementRobot {
    
    public EndDeversement(long date, Robot robot){
	super(date, robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeversement(false);
    }

}
