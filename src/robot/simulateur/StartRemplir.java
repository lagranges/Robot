package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;

public class StartRemplir extends EvenementRobot {
    
    public StartRemplir(long date, Robot robot){
	super(date, robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurRemplir(true);
    }

}
