import robot.io.LecteurDonnees;
import robot.io.DonneesSimulation;
import robot.io.Simulation;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.awt.Color;

import gui.GUISimulator;

public class TestSimulation{
    public static void main(String[] args){
	if (args.length < 1){
	    System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
	}

	try {
	    DonneesSimulation data = LecteurDonnees.creeDonnees(args[0]);
	    int guiColonne = 500;
	    int guiLigne = 500;
	    // crée la fenêtre graphique dans laquelle dessiner
	    GUISimulator gui = new GUISimulator(guiColonne,guiLigne, Color.BLACK);
	    Simulation simu = new Simulation(gui, data, guiColonne/data.getCarteNbColonne(), guiColonne/50);
					    
	    data.afficheDonneesCarte();
	    data.afficheDonneesIncendies();
	    data.afficheDonneesRobots();
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}