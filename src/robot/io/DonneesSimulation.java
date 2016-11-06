package robot.io;

import robot.map.*;
import robot.entities.*;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class DonneesSimulation{
    private Carte donneesCarte;
    private Incendie donneesIncendie[];
    private Robot donneesRobot[];

    public DonneesSimulation(Carte mapData, Incendie fireData[], Robot bot[]){
	this.donneesCarte = mapData;
	this.donneesIncendie = fireData;
	this.donneesRobot = bot;
    }

    public void getDonneesCarte(){
	System.out.println("Carte " + this.donneesCarte.getNbLigne() + "x" + this.donneesCarte.getNbColonne() + "; Taille des Cases = " + this.donneesCarte.getTailleCases());
	for(int i = 0; i < donneesCarte.getNbLigne(); i++){
	    for(int j = 0; j < donneesCarte.getNbColonne(); j++){
		Case box = donneesCarte.getCaseAt(i,j);
		System.out.println("Case(" + i + "," + j + "): Nature = " + box.getNatureType()); 
	    }
	}
    }

    public void getDonneesIncendies(){
	System.out.println("Nb d'incendies :" + donneesIncendie.length);
	for(int i = 0; i < donneesIncendie.length; i++){
	    System.out.println("Incendie " + i + ": position = (" + 
			       donneesIncendie[i].getPosition().getPosition().getLigne() + "," + 
			       donneesIncendie[i].getPosition().getPosition().getColonne() + "); Intensite :" 
			       + donneesIncendie[i].getNbLitresEauPourExtinction() );
	}
    }

    public void getDonneesRobots(){
	System.out.println("Nb de Robots :" + donneesRobot.length);
	for(int i = 0; i < donneesRobot.length; i++){
	    System.out.println("Robot " + i + ": position = (" + 
			       donneesRobot[i].getPosition().getPosition().getLigne() + "," + 
			       donneesRobot[i].getPosition().getPosition().getColonne() + "); Type :" 
			       + donneesRobot[i].toString() + "; Vitesse :" + donneesRobot[i].getVitesseDeplacementDefault() + " m/s");
	}
    }
}
