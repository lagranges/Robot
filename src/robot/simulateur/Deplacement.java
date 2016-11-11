package robot.simulateur;

import robot.*;

public class Deplacement extends Evenement{
 
    private Direction dir;

    private int indice;

    public Deplacement(long date, Direction dir, int indice){
	super(date);
	this.dir = dir;
	this.indice = indice;
    }

    private Direction getDirection(){
	return this.dir;
    }

    private int getIndice(){
	return this.indice;
    }

    @Override
    public void execute(DonneesSimulation data){
	
    }
}
