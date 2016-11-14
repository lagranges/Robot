package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class StartDeversement extends EvenementRobot {
    
    public StartDeversement(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeversement(true);
    }

}
