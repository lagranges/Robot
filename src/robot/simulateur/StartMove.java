package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class StartMove extends EvenementRobot {
    
    public StartMove(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeplacement(true);
    }

}
