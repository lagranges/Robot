package robot.strategy;

import java.util.Random;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public class Elementaire extends Chief{

    public Elementaire(Simulateur sim, DonneesSimulation data){
	super(sim, data);
    }

    @Override
    public void sendRobot(Simulateur sim, int i, Case dest){
	DonneesSimulation donnees = getDonneesSimulation();
	donnees.getRobots()[i].setStatus(true);
	donnees.getRobots()[i].programEventDeplacement(sim, donnees, dest, i);
	donnees.getRobots()[i].programEventIntervention(sim, donnees, i);
    }

    @Override
    public void strategy(){
	int i;
	int incendie = chooseIncendie();
	if(incendie < getIncendies().length){
	    do{
		i = chooseRobot();
		if(i >= getRobots().length){break;}
	    }while(propose(i));

	    if(i< getRobots().length){
		sendRobot(getSim(), i, getDonneesSimulation().getIncendies()[incendie].getPosition());
		dejaTraite(i);
	    }
	}
    }
}
