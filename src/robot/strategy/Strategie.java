package robot.strategy;

import java.util.Random;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public abstract class Strategie{
    private DonneesSimulation data;

    private Simulateur sim;

    private boolean[] incendie;

    private boolean[] robot;

    private int temp = 0;
    
    public Strategie(Simulateur sim, DonneesSimulation data){
	this.data = data.copy();
	this.sim = sim;
	this.incendie = new boolean[data.getIncendies().length];
	this.robot = new boolean[data.getRobots().length];
    }

    public DonneesSimulation getDonneesSimulation(){
	return this.data;
    }

    public Simulateur getSim(){
	return this.sim;
    }

    public boolean[] getIncendies(){
	return this.incendie;
    }

    public boolean[] getRobots(){
	return this.robot;
    }

    public int chooseRandomIncendie(){
	Random rand = new Random();
	return rand.nextInt(getIncendies().length);
    }

    public boolean allDejaTraiteIncendie(){
	for (boolean value : getIncendies()) {
	    if (!value)
		return false;
	}
	return true;
    }

    public boolean isDejaTraiteIncendie(int i){
	return getIncendies()[i];
    }

    public void dejaTraiteIncendie(int i){
	getIncendies()[i] = true;
    }

    public boolean allDejaProposeRobot(){
	for (boolean value : getRobots()) {
	    if (!value)
		return false;
	}
	return true;
    }

    public void dejaPropose(int i){
	getRobots()[i] = true;
    }

    public int getTemp(){
	return this.temp;
    }

    public void incrementTemp(){
	this.temp++;
    }

    //Wait t en s pour envoyer la commande suivant
    public void wait(int t){
	incrementTemp();
	while(temp%t != 0){
	    incrementTemp();
	}
    }

    public abstract void sendRobot(Simulateur sim, int indiceRobot, int indiceIncendie);
}
