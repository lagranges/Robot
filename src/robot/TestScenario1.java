package robot;

import robot.simulateur.evenements.Intervention;
import robot.simulateur.evenements.Remplissage;
import robot.simulateur.evenements.Deplacement;
import robot.io.*;
import robot.simulateur.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.awt.Color;
import robot.entities.Robot;

import robot.gui.BetterGUISimulator;

public class TestScenario1 {
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
            Robot r1 = data.getRobots().get(1);
	    sim.ajouteEvenement(new Deplacement(0, r1,Direction.NORD));
	    sim.ajouteEvenement(new Intervention(1, r1, r1.getVolumeMax()));
	    sim.ajouteEvenement(new Deplacement(2, r1,Direction.OUEST));
	    sim.ajouteEvenement(new Deplacement(3, r1,Direction.OUEST));
	    sim.ajouteEvenement(new Remplissage(4, r1, r1.getVolumeMax()));
	    sim.ajouteEvenement(new Deplacement(5, r1, Direction.EST));
	    sim.ajouteEvenement(new Deplacement(6, r1, Direction.EST));
	    sim.ajouteEvenement(new Intervention(7, r1, r1.getVolumeMax()));
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
