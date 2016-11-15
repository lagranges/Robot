package robot.strategy;

import java.util.Random;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public class Elementaire extends Strategie{

    public Elementaire(Simulateur sim, DonneesSimulation data){
	super(sim, data);
    }

    public int chooseRandomRobot(){
	Random rand = new Random();
	return rand.nextInt(getRobots().length);
    } 

    /**
     * retourne false si libre
     */
    public boolean tryPropose(int i){
	dejaPropose(i);
	return getDonneesSimulation().getRobots()[i].getStatus();
    }

    @Override
    public void sendRobot(Simulateur sim, int indiceRobot, int indiceIncendie){
	DonneesSimulation data = getDonneesSimulation();
	Robot bot = data.getRobots()[indiceRobot];
	Incendie fire = data.getIncendies()[indiceIncendie];
	sim.ajouteEvenement(new TempCourant(getTemp()), indiceRobot);
	bot.setStatus(true);
	bot.programEventDeplacement(sim, data, fire.getPosition(), indiceRobot);
	bot.programEventIntervention(sim, data, indiceRobot);
    }
}
