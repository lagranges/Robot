package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class Arrive extends Evenement{
    
    private int indice;

    public Arrive(long date, int indice){
	super(date);
	this.indice = indice;
    }

    private int getIndice(){
	return this.indice;
    }

    @Override
    public void execute(DonneesSimulation data){
	data.getRobots()[getIndice()].setIndicateurDeplacement(false);
    }

}