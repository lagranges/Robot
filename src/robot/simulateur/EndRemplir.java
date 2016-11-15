package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;

public class EndRemplir extends EvenementRobot {
    
    public EndRemplir(long date, Robot robot){
	super(date,robot);
    }

    @Override
    public void execute(DonneesSimulation data){
	getRobot().setIndicateurRemplir(false);
    }

    @Override
    public void undo(DonneesSimulation data) {
        getRobot().setIndicateurRemplir(true);
    }

    
    
}
