package robot.simulateur;

import robot.entities.Robot;
import robot.io.*;
import robot.simulateur.evenements.EvenementRobot;

public class TempCourant extends EvenementRobot {

    public TempCourant(long date, Robot b){
	super(date,b);
    }

    @Override
    public void execute(DonneesSimulation data){}

    @Override
    public void undo(DonneesSimulation data) {
    }
}
