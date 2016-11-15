package robot;

import robot.io.*;
import robot.simulateur.*;
import robot.strategy.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.awt.Color;

import robot.gui.BetterGUISimulator;

public class TestStrategie {
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
	        
	    Strategie chef = new Elementaire(sim, data);
	    
            chef.executeStrategie();
            
	    /*
	    while(!chef.allDejaTraiteIncendie() && !chef.allDejaProposeRobot()){
		int indiceRobot = chef.chooseRandomRobot();
		int indiceIncendie = chef.chooseRandomIncendie();
		while(chef.isDejaTraiteIncendie(indiceIncendie)){
		    indiceIncendie = chef.chooseRandomIncendie();
		}
		while(chef.tryPropose(indiceRobot)){
		    indiceRobot = chef.chooseRandomRobot();
		}

		chef.sendRobot(sim,indiceRobot,indiceIncendie);
		chef.dejaTraiteIncendie(indiceIncendie);
		if(chef.allDejaTraiteIncendie() || chef.allDejaProposeRobot()) {
		    chef.wait(50);
		}

		}*/
	    
	    /*
	    chef = new Evolue(sim, data);

            
            chef.executeStrategie();
            
	    while(!chef.allDejaTraiteIncendie()){
		chef.checkRobotFinish();
		int indiceIncendie = chef.chooseRandomIncendie();
		while(chef.isDejaTraiteIncendie(indiceIncendie)){
		    indiceIncendie = chef.chooseRandomIncendie();
		}
		chef.proposeAll();
		if(!chef.isEveryoneBusy()){
		    int indiceRobot = chef.whichRobotIsCloser(indiceIncendie);
		    if(indiceRobot != Integer.MAX_VALUE){
			chef.sendRobot(sim, indiceRobot, indiceIncendie);
			chef.dejaTraiteIncendie(indiceIncendie);

			System.out.println("Robot " + indiceRobot + " go to " + indiceIncendie);
		    }
		}
		chef.initProposal();
		System.out.println("chef time : " + chef.getTemp());
		System.out.println("0 time : " + chef.getEstimateTime()[0]);
		System.out.println("1 time : " + chef.getEstimateTime()[1]);
		System.out.println("2 time : " + chef.getEstimateTime()[2]);
		System.out.println();
		chef.wait(5);
	    }*/
	    
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }
}
