package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class EndDeversement extends EvenementRobot {
    
    public EndDeversement(long date, Robot robot){
	super(date, robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeversement(false);
    }

}
