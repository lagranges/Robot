package robot.simulateur;

import robot.io.*;

public abstract class Evenement{
   
    private final long date;
   
    public Evenement(long date){
	this.date = date;
    }

    public long getDate(){
	return this.date;
    }

    public abstract void execute(DonneesSimulation data);
}
