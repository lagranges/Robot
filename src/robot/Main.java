/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import robot.gui.BetterGUISimulator;
import robot.io.DonneesSimulation;
import robot.io.LecteurDonnees;
import robot.simulateur.Simulateur;

public class Main {

    public static void main(String args[]) {
        try {
            if (args.length < 1) {
                System.out.println("Syntaxe: java Main <nomDeFichier>");
                System.exit(1);
            }
            
            DonneesSimulation data = LecteurDonnees.creeDonnees(args[0]);
            // taille de la fenetre initial
            int guiColonne = 500;
            int guiLigne = 500;
            // crée la fenêtre graphique dans laquelle dessiner
            BetterGUISimulator gui = new BetterGUISimulator(guiColonne, guiLigne, Color.BLACK, guiColonne / data.getCarte().getNbColonne(), guiColonne / 50);
            
            Simulateur sim = new Simulateur(gui, data);
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataFormatException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
