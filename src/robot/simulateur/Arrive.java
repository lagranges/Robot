package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class Arrive extends EvenementRobot {
    
    public Arrive(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurDeplacement(false);
    }

}
