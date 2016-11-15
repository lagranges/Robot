package robot.simulateur.evenements;

import robot.io.*;
import robot.entities.*;

public class Intervention extends EvenementRobot {

    private final int volEau;

    public Intervention(long date, Robot robot, int volEau){
	super(date,robot);
	this.volEau = volEau;
    }

    private int eauDeverserSave = 0;
    
    @Override
    public void execute(DonneesSimulation data){
	Robot robot = getRobot();
	Incendie inc = data.getIncendieAt(robot.getCase());
	if(inc != null) {
	    int litreNeccessaires = inc.getNbLitresEauRestantPourExtinction();
	    int litreAVerse = Math.min(litreNeccessaires,volEau);
	    int litreVersable = Math.min(litreAVerse,robot.getVolumeEau());
	    robot.deverserEau(litreVersable);
	    inc.nbLitresEauArrive(litreVersable);
            eauDeverserSave = litreVersable;
            System.out.println("["+getDate()+"]Intervention : " + robot + " inc " + inc + " litre verse : " + litreVersable);
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
