package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class EndRemplir extends EvenementRobot {
    
    public EndRemplir(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurRemplir(false);
    }

}
