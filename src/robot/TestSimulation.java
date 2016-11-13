import robot.io.*;
import robot.simulateur.*;
import robot.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.awt.Color;

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

	    data.goToCourtChemin(sim,1,5,5);
	    // sim.ajouteEvenement(new Arrive(sim.getListeEvenement()[sim.getListeSize() - 1].getDate() + 3, 1, 5,5));
	    data.interventionParSecond(sim,0);	    
	    data.goToCourtChemin(sim,1,5,3);
	    //  sim.ajouteEvenement(new Arrive(sim.getListeEvenement()[sim.getListeSize() - 1].getDate() + 3, 1, 5,3));
	    data.remplirParSecond(sim,0);
	    data.goToCourtChemin(sim,1,5,5);
	    // sim.ajouteEvenement(new Arrive(sim.getListeEvenement()[sim.getListeSize() - 1].getDate() + 1, 1, 5,5));
	    data.interventionParSecond(sim,0);

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
	  

	    data.testPlusCourtChemin();
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
