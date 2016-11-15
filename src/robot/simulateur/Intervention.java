package robot.simulateur;

import robot.io.*;
import robot.entities.*;

public class Intervention extends EvenementRobot {

    private final double volEau;

    public Intervention(long date, Robot robot, double volEau){
	super(date,robot);
	this.volEau = volEau;
    }

    private double eauDeverserSave = 0;
    
    @Override
    public void execute(DonneesSimulation data){
	Robot robot = getRobot();
	Incendie inc = data.getIncendieAt(robot.getCase());
	if(inc != null) {
	    double litreNeccessaires = inc.getNbLitresEauPourExtinction();
	    double litreAVerse = Math.min(litreNeccessaires,volEau);
	    double litreVersable = Math.min(litreAVerse,robot.getVolumeEau());
	    robot.deverserEau(litreVersable);
	    inc.nbLitresEauArrive(litreVersable);
            eauDeverserSave = litreVersable;
	}
    }

    @Override
    public void undo(DonneesSimulation data) {
        Robot robot = getRobot();
	Incendie inc = data.getIncendieAt(robot.getCase());
	if(inc != null) {
            robot.remplirEau(eauDeverserSave);
            inc.nbLitresEauArrive(-eauDeverserSave);
	}
    }
    
}
