package robot.strategy;

import java.util.*;

import robot.*;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public class Evolue extends Strategie{

    private int[] estimateTime;

    public Evolue(Simulateur sim, DonneesSimulation data){
	super(sim, data);
	this.estimateTime = new int[data.getRobots().length];
    }

    public void setTableBoolRobot(int i){
	getRobots()[i] = true;
    }

    public void setEstimateTime(int i, int value){
	estimateTime[i] = value;
    }

    public int[] getEstimateTime(){
	return this.estimateTime;
    }

    public void proposeAll(){
	Robot[] bot = getDonneesSimulation().getRobots();
	for(int i=0; i < bot.length; i++){
	    if(bot[i].getStatus()){
		setTableBoolRobot(i);
	    }
	}
    }

    //return false si au moins un de Robot est libre
    public boolean isEveryoneBusy(){
	for (boolean value : getRobots()) {
	    if (!value)
		return false;
	}
	return true;
    }

    public int whichRobotIsCloser(int indiceIncendie){
	Case dest = getDonneesSimulation().getIncendies()[indiceIncendie].getPosition();
	Robot[] bot = getDonneesSimulation().getRobots();
	int time = Integer.MAX_VALUE;
	int indice = 0;
	for(int i=0; i<getRobots().length; i++){
	    if(!bot[i].getStatus()){
		if(time > bot[i].calculPlusCourtChemin(getDonneesSimulation(),dest,i)){
		    time = bot[i].calculPlusCourtChemin(getDonneesSimulation(),dest,i);
		    indice = i;
		}
	    }
	}
	if(time == Integer.MAX_VALUE){
	    return time;
	}else{
	    return indice;
	}
    }

    public int whichWaterSourceIsCloser(Case[] dest, Robot bot, int numero){
	int time = Integer.MAX_VALUE;
	int indice = 0;
	for(int i=0; i<dest.length; i++){
	    if(time > bot.calculPlusCourtChemin(getDonneesSimulation(),dest[i],numero)){
		time = bot.calculPlusCourtChemin(getDonneesSimulation(),dest[i],numero);
		indice = i;
	    }
	}
	return indice;
    }

    public List<Case> getPosTousEau(){
	List<Case> eau = new ArrayList<Case>();
	for(Case[] lc : getDonneesSimulation().getCarte().getMap()){
	    for(Case c : lc){
		if(c.getNatureType() == NatureTerrain.EAU){
		    eau.add(c);
		}
	    }
	}
	return eau;
    }

    public Case[] getListEauPos(){
	return getPosTousEau().toArray(new Case[getPosTousEau().size()]);
    }

    public List<Case> getPosTousBerge(){
	Carte map = getDonneesSimulation().getCarte();
	List<Case> berge = new ArrayList<Case>();
	for(Case c : getListEauPos()){
	    for(Direction dir : Direction.values()){
		Position pos = new Position(c.getPosition(),dir);
		if(map.isInMapBound(pos)){
		    if(map.getCaseAt(pos).getNatureType() != NatureTerrain.EAU){
			berge.add(map.getCaseAt(pos));
		    }
		}
	    }
	}
	return berge;
    }

    public Case[] getListBergePos(){
	return getPosTousBerge().toArray(new Case[getPosTousBerge().size()]);
    }

    public Case closestWaterSource(Robot bot, int indiceRobot){
	int i;;
	if(bot.getClass().getSimpleName().equals("Drone")){
	    i = whichWaterSourceIsCloser(getListEauPos(), bot, indiceRobot);
	    return getListEauPos()[i];
	}else{
	    i = whichWaterSourceIsCloser(getListBergePos(), bot, indiceRobot);
	    return getListBergePos()[i]; 	
	}
    }

    public void checkRobotFinish(){
	Robot[] bot = getDonneesSimulation().getRobots();
	for(int i=0; i<estimateTime.length; i++){
	    if(getTemp() >= estimateTime[i]){
		bot[i].setStatus(false);
	    }
	}
    }

    public void initProposal(){
	for(int i=0; i<getRobots().length; i++){
	    getRobots()[i] = false;
	}
    }

    @Override
    public void sendRobot(Simulateur sim, int indiceRobot, int indiceIncendie){
	DonneesSimulation data = getDonneesSimulation();
	Robot bot = data.getRobots()[indiceRobot];
	//System.out.println(indiceIncendie);
	Incendie fire = data.getIncendies()[indiceIncendie];

	int time = 0;

	sim.ajouteEvenement(new TempCourant(getTemp()), indiceRobot);

	int volEau = bot.getVolumeMax();
	int intensity = fire.getNbLitresEauPourExtinction();

	bot.setStatus(true);
	time += bot.calculPlusCourtChemin(data,fire.getPosition(), indiceRobot);
	bot.programEventDeplacement(sim, data, fire.getPosition(), indiceRobot);
	time += bot.tempsDeversement((int)bot.getVitesseDeversement());
	bot.programEventIntervention(sim, data, indiceRobot);
	if(!bot.getClass().getSimpleName().equals("Patte")){
	    intensity -= volEau;
	    while(intensity > 0){
		Case water = closestWaterSource(bot, indiceRobot);
		time += bot.calculPlusCourtChemin(data, water, indiceRobot);
		bot.programEventDeplacement(sim, data, water, indiceRobot);
		time += bot.tempsRemplissage();
		bot.programEventRemplissage(sim, indiceRobot);
		time += bot.calculPlusCourtChemin(data,fire.getPosition(), indiceRobot);
		bot.programEventDeplacement(sim, data, fire.getPosition(), indiceRobot);
		bot.programEventIntervention(sim, data, indiceRobot);
		intensity -= volEau;
	    }

	    if(intensity <= 0){
		Case water = closestWaterSource(bot, indiceRobot);
		time += bot.calculPlusCourtChemin(data, water, indiceRobot);
		bot.programEventDeplacement(sim, data, water, indiceRobot);
		time += bot.tempsRemplissage();
		bot.programEventRemplissage(sim, indiceRobot);
	    }
	}

	setEstimateTime(indiceRobot, getTemp() + time);
    }

}
