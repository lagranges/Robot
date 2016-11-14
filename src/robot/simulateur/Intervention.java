package robot.simulateur;

import java.util.List;
import robot.*;
import robot.io.*;
import robot.map.*;
import robot.entities.*;

public class Intervention extends EvenementRobot {

    private final double volEau;

    public Intervention(long date, Robot robot, double volEau){
	super(date,robot);
	this.volEau = volEau;
    }

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
	}
    }
}
