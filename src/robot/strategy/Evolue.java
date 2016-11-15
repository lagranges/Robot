package robot.strategy;

import java.util.*;

import robot.*;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public class Evolue extends Strategie{

    private Map<Robot,Integer> estimateTime;
    
    private List<Case> berges;
    private List<Case> eaux;
    
    public Evolue(Simulateur sim, DonneesSimulation data){
	super(sim, data);
        berges = data.getCarte().getListeCasesBerge();
        eaux = getDonneesSimulation().getCarte().getListeCases(NatureTerrain.EAU);
        this.estimateTime = new HashMap<Robot, Integer>();
    }
    
    @Override
    public void executeStrategie() {
        for(Incendie i : getIncendiesNonAffecte()){
            Robot choix = null;
            int distanceMin = Integer.MAX_VALUE;
            for(Robot r : getRobotsDisponible()){
                int distance = r.calculPlusCourtChemin(getDonneesSimulation(), i.getCase());
                if(distance < distanceMin){
                    distanceMin = distance;
                    choix = r;
                }
            }
            if(choix != null){
                affecterIncendie(choix, i);
            }
        }
    }

    
    public Case closestWaterSource(List<Case> lc, Robot bot){
	int time = Integer.MAX_VALUE;
	Case choix = null;
	for(Case eau : lc) {
	    if(time > bot.calculPlusCourtChemin(getDonneesSimulation(),eau)){
		time = bot.calculPlusCourtChemin(getDonneesSimulation(),eau);
		choix = eau;
	    }
	}
	return choix;
    }

    public List<Case> getCasesTypeEau(){
	return getDonneesSimulation().getCarte().getListeCases(NatureTerrain.EAU);
    }

    @Override
    public void sendRobot(Simulateur sim, Robot bot, Incendie fire) {
        DonneesSimulation data = getDonneesSimulation();
        //System.out.println(indiceIncendie);

        int time = 0;

        sim.ajouteEvenement(new TempCourant(getTemps(), bot));

        int volEau = bot.getVolumeMax();
        int intensity = fire.getNbLitresEauRestantPourExtinction();

        bot.setStatus(true);
        time += bot.calculPlusCourtChemin(data, fire.getCase());
        bot.programEventDeplacement(sim, data, fire.getCase());
        time += bot.tempsDeversement((int) bot.getVitesseDeversement());
        bot.programEventIntervention(sim, data, fire.getCase());
        intensity -= volEau;
        while (intensity > 0) {
            Case water;
            if (bot.peutRemplirSurCaseEau()) {
                water = closestWaterSource(eaux, bot);
            } else {
                water = closestWaterSource(berges, bot);
            }
            time += bot.calculPlusCourtChemin(data, water);
            bot.programEventDeplacement(sim, data, water);
            time += bot.tempsRemplissage();
            bot.programEventRemplissage(sim);
            time += bot.calculPlusCourtChemin(data, fire.getCase());
            bot.programEventDeplacement(sim, data, fire.getCase());
            bot.programEventIntervention(sim, data,fire.getCase());
            intensity -= volEau;
        }
    }

    //setEstimateTime(getTemps() + time);
}
