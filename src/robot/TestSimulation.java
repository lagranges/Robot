import robot.io.*;
import robot.simulateur.*;
import robot.*;
import robot.strategy.*;

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
	    
	    Chief chef = new Evolue(sim, data);

	    while(!chef.allDejaTraiteIncendie() || !chef.allDejaProposeRobot()){
		chef.strategy();
	    }
	  
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
