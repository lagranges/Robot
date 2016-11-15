package robot.strategy;

import robot.io.*;
import robot.entities.*;
import robot.simulateur.*;

public class Elementaire extends Strategie{

    public Elementaire(Simulateur sim, DonneesSimulation data){
	super(sim, data);
    }

    @Override
    public void executeStrategie() {
        for(Robot r : getRobots()){
            affecterIncendie(r, chooseRandomIncendie());
        }
    }
    
    @Override
    public void sendRobot(Simulateur sim, Robot robot, Incendie inc) {
        DonneesSimulation data = getDonneesSimulation();
	sim.ajouteEvenement(new TempCourant(getTemps(),robot));
	robot.setStatus(true);
	robot.programEventDeplacement(sim, data, inc.getCase());
	robot.programEventIntervention(sim, data, inc.getCase());
        
        robot.programEventDeplacement(sim, data, data.getCarte().getListeCasesBerge().get(0));
        robot.programEventRemplissage(sim);
        
    }
}
