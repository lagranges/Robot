package robot;

import robot.io.*;
import robot.simulateur.*;
import robot.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.awt.Color;
import robot.entities.Robot;

import robot.gui.BetterGUISimulator;

public class TestSimulation{
    public static void main(String[] args){
	if (args.length < 1){
	    System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
	}

	try {
	    DonneesSimulation data = LecteurDonnees.creeDonnees(args[0]);
	    // taille de la fenetre initial
	    int guiColonne = 500;
	    int guiLigne = 500;
	    // crée la fenêtre graphique dans laquelle dessiner
	    BetterGUISimulator gui = new BetterGUISimulator(guiColonne,guiLigne, Color.BLACK, guiColonne/data.getCarte().getNbColonne(), guiColonne/50);

	    Simulateur sim = new Simulateur(gui, data);
	    
	    //Robot0
            Robot r0 = data.getRobots().get(0);
	    r0.programEventDeplacement(sim, data, data.getCarte().getCaseAt(6,1), 0);
	    r0.programEventIntervention(sim, data, 0);
	    r0.programEventDeplacement(sim, data, data.getCarte().getCaseAt(6,2), 0);
	    r0.programEventRemplissage(sim, 0);
	    r0.programEventDeplacement(sim, data, data.getCarte().getCaseAt(6,1), 0);
	    r0.programEventIntervention(sim, data, 0);

	    //Robot1
            Robot r1 = data.getRobots().get(1);
	    r1.programEventDeplacement(sim, data, data.getCarte().getCaseAt(5,5), 1);
	    r1.programEventIntervention(sim, data, 1);
	    r1.programEventDeplacement(sim, data, data.getCarte().getCaseAt(5,3), 1);
	    r1.programEventRemplissage(sim,1);
	    r1.programEventDeplacement(sim, data, data.getCarte().getCaseAt(5,5), 1);
	    r1.programEventIntervention(sim, data, 1);

	    //Robot2
            Robot r2 = data.getRobots().get(2);
	    r2.programEventDeplacement(sim, data, data.getCarte().getCaseAt(7,7), 2);
	    r2.programEventIntervention(sim, data, 2);

	    //Robot1
	    r1.programEventDeplacement(sim, data, data.getCarte().getCaseAt(7,1), 1);
	    r1.programEventIntervention(sim, data, 1);
	    r1.programEventDeplacement(sim, data, data.getCarte().getCaseAt(4,1), 1);
	    r1.programEventRemplissage(sim,1);
	    r1.programEventDeplacement(sim, data, data.getCarte().getCaseAt(7,1), 1);
	    r1.programEventIntervention(sim, data, 1);
	    
	    //Robot2
	    r2.programEventDeplacement(sim, data, data.getCarte().getCaseAt(6,0), 2);
	    r2.programEventIntervention(sim, data, 2);
	    r2.programEventDeplacement(sim, data, data.getCarte().getCaseAt(7,0), 2);
	    r2.programEventIntervention(sim, data, 2);

	    sim.restart();

	    /*
	    sim.ajouteEvenement(new Deplacement(0, 1,Direction.NORD));
	    sim.ajouteEvenement(new Intervention(1, 1));
	    sim.ajouteEvenement(new Deplacement(2, 1,Direction.OUEST));
	    sim.ajouteEvenement(new Deplacement(3, 1,Direction.OUEST));
	    sim.ajouteEvenement(new Remplissage(4, 1));
	    sim.ajouteEvenement(new Deplacement(5, 1, Direction.EST));
	    sim.ajouteEvenement(new Deplacement(6, 1, Direction.EST));
	    sim.ajouteEvenement(new Intervention(7, 1));
	    */
	  
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
