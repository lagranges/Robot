package robot.simulateur.evenements;

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

    @Override
    public void undo(DonneesSimulation data) {
	getRobot().setIndicateurRemplir(false);
    }
    
}
