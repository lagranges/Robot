package robot.simulateur;

import robot.gui.BetterGUISimulator;
import robot.io.*;
import robot.entities.*;
import robot.map.*;

import java.util.*;
import java.awt.Color;

import gui.*;

public class Simulateur implements Simulable {
    /** L'interface graphique associée */
    private BetterGUISimulator gui;

    /** Les données de simulation a manipuler */
    private DonneesSimulation manipulationData;

    /** Les données de simulation a sauvegarder */
    private DonneesSimulation saveData;

    /** La date de simulation */
    private long dateSimulation = 0;

    /**
     * A chaque date sa liste d'evenement
     */
    private Map<Long,List<Evenement>> evenements = new HashMap<Long,List<Evenement>>();

    public Simulateur(BetterGUISimulator gui, DonneesSimulation data) {
	this.gui = gui;
	gui.setSimulable(this); //association a la gui
	this.manipulationData = data;

      
	saveData = data.copy();
	draw();
    }

    private DonneesSimulation getDonneesSimulation(){
	return this.manipulationData;
    }

    private void draw(){
	gui.reset();
	drawCarte();
	drawIncendies();
	drawRobots();
	drawTempsOperation();
	drawInfoMap();
	drawInfoRobots();
	drawInfoIncendies();
    }

    @Override
    public void next(){
	if(!simulationTerminee()){
	    for(Evenement e : evenements.get(getDateSimulation())){
		e.execute(getDonneesSimulation());
	    }
	    incrementeDate();
	    draw();
	}
    }

    @Override
    public void restart(){
	this.manipulationData = saveData.copy();
	this.dateSimulation = 0;
	draw();
    }

    public void ajouteEvenement(Evenement e){
	List<Evenement> le = evenements.get(e.getDate());
	if(le == null) {
	    le = new ArrayList<Evenement>();
	    evenements.put(e.getDate(), le);
	}
	le.add(e);
    }

    private void incrementeDate(){
	this.dateSimulation++;
    }

    private long getDateSimulation(){
	return this.dateSimulation;
    }

    private boolean simulationTerminee(){
	long date = getDateSimulation();
	for(Long d : evenements.keySet()){
	    if(d >= date) {
		return false;
	    }
	}
	return true;
    }

    private void drawRobots(){
	gui.drawAll(manipulationData.getRobots());
    }

    private void drawCarte(){
	gui.drawAll(manipulationData.getCarte());
    }

    private void drawIncendies(){
	gui.drawAll(manipulationData.getIncendies());
    }

    private void drawTempsOperation(){
	int position = gui.getPanelWidth();
	gui.addGraphicalElement(new Text(position + 80, 10, Color.white, "Temps d'Operation")); 
	gui.addGraphicalElement(new Text(position + 120, 30, Color.white, Long.toString(getDateSimulation()) + "s")); 
    }
    
    private void drawInfoMap(){
	int position = gui.getPanelWidth();
	Carte map = manipulationData.getCarte();
	gui.addGraphicalElement(new Text(position + 80, 80, Color.white, "Info Carte")); 
	gui.addGraphicalElement(new Text(position + 80, 100, Color.white, Integer.toString(map.getNbLigne()) + "x" + Integer.toString(map.getNbColonne()))); 
	gui.addGraphicalElement(new Text(position + 80, 120, Color.white, "Taille :" +  Integer.toString(map.getTailleCases()) + "m")); 
    }

    private void drawInfoRobots(){
	int position = gui.getPanelWidth();
	List<Robot> bots = manipulationData.getRobots();
	gui.addGraphicalElement(new Text(position + 250, 10, Color.white, "Info Robots"));
	Iterator<Robot> iter = bots.iterator();	
	for(int i=0; iter.hasNext(); i++) {
	    Robot bot = iter.next();
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+30, Color.white, "Robot " + i + ": " + bot.getClass().getSimpleName() ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+50, Color.white, "Pos : " + bot.getPosition().getPosition() ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+70, Color.white, "Vitesse : " + bot.getVitesseDeplacementDefault() + "km/h" ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+90, Color.white, "Deversement : " + bot.getVitesseDeversement() + "l/s" ));
	    if(bot.getClass().getSimpleName().equals("Patte")){
		gui.addGraphicalElement(new Text(position + 250, (i*140)+110, Color.white, "Pas besoin de remplir" ));
		gui.addGraphicalElement(new Text(position + 250, (i*140)+130, Color.white, "Reservoir : Infini" ));
	    }else{
		gui.addGraphicalElement(new Text(position + 250, (i*140)+110, Color.white, "Remplissage : " + bot.getVitesseRemplissage() + "l/s" ));
		gui.addGraphicalElement(new Text(position + 250, (i*140)+130, Color.white, "Reservoir : " + bot.getVolumeEau() + "/" + bot.getVolumeMax() +" L" ));
	    }
	}	
    }

    private void drawInfoIncendies(){
	int position = gui.getPanelWidth();
	List<Incendie> fires = manipulationData.getIncendies();
	gui.addGraphicalElement(new Text(position + 430, 10, Color.white, "Info Incendies"));
	Iterator<Incendie> iter = fires.iterator();	
	for(int i=0; iter.hasNext(); i++) {
	    Incendie feu = iter.next();
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+30, Color.white, "Incendie " + i ));
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+50, Color.white, "Pos : " + feu.getPosition().getPosition() ));
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+70, Color.white, "Intensité : " + feu.getNbLitresEauPourExtinction() + " L" ));
	}	
    }
}
