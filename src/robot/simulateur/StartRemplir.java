package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class StartRemplir extends EvenementRobot {
    
    public StartRemplir(long date, Robot robot){
	super(date, robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurRemplir(true);
    }

}
