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

    /** L'indice d'evenement a executer */
    private int evenementIndice = 0;

    /** La liste d'evenement */
    private List<Evenement> evenement = new ArrayList<Evenement>();

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
	    if(getDateSimulation() == (long)getListeEvenement()[evenementIndice].getDate()){
	    getListeEvenement()[evenementIndice].execute(getDonneesSimulation());
	    evenementIndice++;
	    }
	    incrementeDate();
	    draw();
	}


    }

    @Override
    public void restart(){
	this.manipulationData = saveData.copy();
	this.dateSimulation = 0;
	this.evenementIndice = 0;
	draw();
    }

    public Evenement[] getListeEvenement(){
	return evenement.toArray(new Evenement[evenement.size()]);
    }

    public int getListeSize(){
	return evenement.size();
    }

    public void ajouteEvenement(Evenement e){
	evenement.add(e);
    }

    private void incrementeDate(){
	this.dateSimulation++;
    }

    private long getDateSimulation(){
	return this.dateSimulation;
    }

    private boolean simulationTerminee(){
	return (this.evenementIndice >= getListeEvenement().length);
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
	Robot[] bot = manipulationData.getRobots();
	gui.addGraphicalElement(new Text(position + 250, 10, Color.white, "Info Robots"));
	for(int i=0; i< bot.length; i++){
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+30, Color.white, "Robot " + Integer.toString(i) + ": " + bot[i].getClass().getSimpleName()));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+50, Color.white, "Pos : (" + Integer.toString(bot[i].getPosition().getPosition().getLigne()) + ","  + Integer.toString(bot[i].getPosition().getPosition().getColonne()) + ")" ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+70, Color.white, "Vitesse : " + Double.toString(bot[i].getVitesseDeplacementDefault()) + "km/h" ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+90, Color.white, "Deversement : " + Double.toString(bot[i].getVitesseDeversement()) + "l/s" ));
	    if(bot[i].getClass().getSimpleName().equals("Patte")){
		gui.addGraphicalElement(new Text(position + 250, (i*140)+110, Color.white, "Pas besoin de remplir" ));
		gui.addGraphicalElement(new Text(position + 250, (i*140)+130, Color.white, "Reservoir : Infini" ));
	    }else{
		gui.addGraphicalElement(new Text(position + 250, (i*140)+110, Color.white, "Remplissage : " + Double.toString(bot[i].getVitesseRemplissage()) + "l/s" ));
		gui.addGraphicalElement(new Text(position + 250, (i*140)+130, Color.white, "Reservoir : " + Integer.toString(bot[i].getVolumeEau()) + "/" + Integer.toString(bot[i].getVolumeMax()) +" L" ));
	    }
	}	
    }

    private void drawInfoIncendies(){
	int position = gui.getPanelWidth();
	Incendie[] fire = manipulationData.getIncendies();
	gui.addGraphicalElement(new Text(position + 430, 10, Color.white, "Info Incendies"));
	for(int i=0; i< fire.length; i++){
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+30, Color.white, "Incendie " + Integer.toString(i)));
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+50, Color.white, "Pos : (" + Integer.toString(fire[i].getPosition().getPosition().getLigne()) + ","  + Integer.toString(fire[i].getPosition().getPosition().getColonne()) + ")" ));
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+70, Color.white, "Intensité : " + Integer.toString(fire[i].getNbLitresEauPourExtinction()) + " L" ));
	}	
    }
}
