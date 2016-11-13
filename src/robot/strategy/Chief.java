package robot.strategy;

import java.util.Random;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public abstract class Chief{
    private DonneesSimulation data;

    private Simulateur sim;

    private boolean[] incendie;

    private boolean[] robot;
    
    public Chief(Simulateur sim, DonneesSimulation data){
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

    public boolean allDejaTraiteIncendie(){
	for (boolean value : getIncendies()) {
	    if (!value)
		return false;
	}
	return true;
    }

    public int chooseIncendie(){
	Random rand = new Random();
	int i;
	if(!allDejaTraiteIncendie()){
	    do{
		i = rand.nextInt(getDonneesSimulation().getIncendies().length);
	    }while(getIncendies()[i]);
	    return i;
	}else{
	    return getIncendies().length;
	}
    }

    public void dejaTraite(int i){
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

    /**
     * retourne false si libre
     */
    public boolean propose(int i){
	dejaPropose(i);
	return getDonneesSimulation().getRobots()[i].getStatus();
    }

    public int chooseRobot(){
	Random rand = new Random();
	int i;
	if(!allDejaProposeRobot()){
	    do{
		i = rand.nextInt(getRobots().length);
	    }while(getRobots()[i]);
	    return i;
	}else{
	    return getRobots().length;
	}	
    } 

    public void initProposal(){
	for(int i=0; i<getRobots().length; i++){
	    getRobots()[i] = false;
	}
    }

    public abstract void strategy();

    public abstract void sendRobot(Simulateur sim, int i, Case dest);
}
