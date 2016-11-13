package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;
import robot.entities.*;

public class Intervention extends Evenement{

    private int indice;

    private double volEau;

    public Intervention(long date, int indice, double volEau){
	super(date);
	this.indice = indice;
	this.volEau = volEau;
    }

    private int getIndice(){
	return this.indice;
    }

    private double getVolEau(){
	return this.volEau;
    }

    private int witchIncendie(Case pos, Incendie[] fire){
	for(int i=0; i < fire.length; i++){
	    if(fire[i].getPosition().getPosition().getLigne() == pos.getPosition().getLigne()){
		if(fire[i].getPosition().getPosition().getColonne() == pos.getPosition().getColonne()){
		    return i;
		}
	    }
	}
	return fire.length;
    }

    @Override
    public void execute(DonneesSimulation data){
	Robot bot = data.getRobots()[getIndice()];
	Incendie[] fire = data.getIncendies();
	int i = witchIncendie(bot.getPosition(), fire);
	//Si le robot est bien arrive sur l'un de l'incendies
	if(i < fire.length){
	    if(fire[i].getNbLitresEauPourExtinction() >= getVolEau()){
		if(bot.getVolumeEau() > getVolEau()){
		    fire[i].nbLitresEauArrive(getVolEau());
		    bot.deverserEau(getVolEau());
		}else{
		    fire[i].nbLitresEauArrive(bot.getVolumeEau());
		    bot.deverserEau(bot.getVolumeEau());
		}
	    }else{
		bot.deverserEau(fire[i].getNbLitresEauPourExtinction());
		fire[i].nbLitresEauArrive(fire[i].getNbLitresEauPourExtinction());
	    }
	}
    }
}
