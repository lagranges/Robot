package robot.simulateur;

import robot.simulateur.evenements.EvenementRobot;
import robot.simulateur.evenements.Evenement;
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

    /** La date de simulation */
    private long dateSimulation = 0;
    private long dateFin = 0;
    
    /**
     * A chaque robot sa liste d'evenement
     */
    private Map<Robot,List<Evenement>> evenements = new HashMap<Robot,List<Evenement>>();


    public Simulateur(BetterGUISimulator gui, DonneesSimulation data) {
	this.gui = gui;
	gui.setSimulable(this); //association a la gui
	this.manipulationData = data;
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
            for (Robot r : evenements.keySet()) {
                for (Evenement e : evenements.get(r)) {
                    if(e.getDate() == getDateSimulation()) {
                        e.execute(getDonneesSimulation());
                    }
                }
            }
            incrementeDate();
        }
        draw();
    }

    @Override
    public void restart(){
	while(dateSimulation > 0) {
            dateSimulation--;
            for (Robot r : evenements.keySet()) {
                for (Evenement e : evenements.get(r)) {
                    if(e.getDate() == getDateSimulation()) {
                        e.undo(getDonneesSimulation());
                    }
                }
            }
        }
	draw();
    }
   
    public void ajouteEvenement(EvenementRobot e){
        System.out.println("ajout evement : " + e.getClass() + " robot " + e.getRobot());
	List<Evenement> le = evenements.get(e.getRobot());
	if(le == null) {
	    le = new ArrayList<Evenement>();
	    evenements.put(e.getRobot(), le);
	}
	le.add(e);
        dateFin = Math.max(e.getDate(),dateFin);
    }
    
    public Evenement getLastEvenement(Robot robot) {
        List<Evenement> el = evenements.get(robot);
        if(el == null) {
            return null;
        }
        return el.get(el.size()-1);
    }

    private void incrementeDate(){
	this.dateSimulation++;
    }

    private long getDateSimulation(){
	return this.dateSimulation;
    }

    private boolean simulationTerminee(){
        return dateSimulation > dateFin;
    }

    private void drawRobots(){
	gui.drawAll(manipulationData.getRobots());
    }

    private void drawCarte(){
	gui.draw(manipulationData.getCarte());
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
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+30, Color.white, "Robot " + i + ": " + bot));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+50, Color.white, "Pos : " + bot.getCase().getPosition() ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+70, Color.white, "Vitesse : " + bot.getVitesseDeplacementDefault() + "km/h" ));
	    gui.addGraphicalElement(new Text(position + 250, (i*140)+90, Color.white, "Deversement : " + bot.getVitesseDeversement() + "l/s" ));
            gui.addGraphicalElement(new Text(position + 250, (i * 140) + 110, Color.white, "Remplissage : " + bot.getVitesseRemplissage() + "l/s"));
            gui.addGraphicalElement(new Text(position + 250, (i * 140) + 130, Color.white, "Reservoir : " + bot.getVolumeEau() + "/" + bot.getVolumeMax() + " L"));
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
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+50, Color.white, "Pos : " + feu.getCase().getPosition() ));
	    gui.addGraphicalElement(new Text(position + 430, (i*70)+70, Color.white, "Intensité : " + feu.getNbLitresEauRestantPourExtinction() + " L" ));
	}	
    }
}
