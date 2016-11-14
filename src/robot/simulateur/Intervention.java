package robot.simulateur;

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

    private static Incendie getIncendieAt(Case caze, List<Incendie> incendies) {
	for(Incendie inc : incendies) {
	    if(inc.getPosition().equals(caze.getPosition())){
		return inc;
	    }
	}
	return null;
    }

    @Override
    public void execute(DonneesSimulation data){
	Robot robot = getRobot();
	Incendie inc = Intervention.getIncendieAt(robot.getPosition(),data.getIncendies());
	if(inc != null) {
	    double litreNeccessaires = inc.getNbLitresEauPourExtinction();
	    double litreAVerse = Math.min(litreNeccessaires,volEau);
	    double litreVersable = Math.min(litreAVerse,robot.getVolumeEau());
	    robot.deverserEau(litreVersable);
	    inc.nbLitresEauArrive(litreVersable);
	}
    }
}
