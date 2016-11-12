package robot.simulateur;

import robot.*;
import robot.io.*;
import robot.map.*;
import robot.entities.*;

public class Intervention extends Evenement{

    private int indice;

    public Intervention(long date, int indice){
	super(date);
	this.indice = indice;
    }

    private int getIndice(){
	return this.indice;
    }

    private int verifierIndiceIncendie(Case pos, Incendie[] fire){
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
	Case pos = data.getRobots()[getIndice()].getPosition();
	int i = verifierIndiceIncendie(pos, data.getIncendies());
	if(i < data.getIncendies().length){
	    if(data.getIncendies()[i].getNbLitresEauPourExtinction() >= data.getRobots()[indice].getVolumeMax()){
		data.getIncendies()[i].nbLitresEauArrive(data.getRobots()[indice].getVolumeMax());
		data.getRobots()[indice].deverserEau(data.getRobots()[indice].getVolumeMax());
	    }else{
		data.getRobots()[indice].deverserEau(data.getIncendies()[i].getNbLitresEauPourExtinction());
		data.getIncendies()[i].nbLitresEauArrive(data.getIncendies()[i].getNbLitresEauPourExtinction());
	    }
	}
    }
}
