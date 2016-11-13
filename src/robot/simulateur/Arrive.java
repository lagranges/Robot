package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;

public class Arrive extends Evenement{
    
    private int indice;

    private int destLigne;

    private int destColonne;

    public Arrive(long date, int indice, int destLigne, int destColonne){
	super(date);
	this.indice = indice;
	this.destLigne = destLigne;
	this.destColonne = destColonne;
    }

    private int getIndice(){
	return this.indice;
    }

    private int getDestLigne(){
	return this.destLigne;
    }

    private int getDestColonne(){
	return this.destColonne;
    }

    @Override
    public void execute(DonneesSimulation data){
	while(!((data.getRobots()[getIndice()].getPosition().getPosition().getLigne() != getDestLigne()) && 
		(data.getRobots()[getIndice()].getPosition().getPosition().getColonne() != getDestColonne()))){}
	data.getRobots()[getIndice()].setIndicateurDeplacement(false);
    }

}
