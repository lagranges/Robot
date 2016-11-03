package robot.io;

/**
 * @see robot.map.Carte
 * @see robot.map.Case
 */
import robot.map.*;

/**
 * @see robot.entities.Entity
 * @see robot.entities.Incendie
 */
import robot.entities.*;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class DonneesSimulation{
    private Carte donneesCarte;
    private Incendie donneesIncendie[];
    //   private Robot donneesRobot;

    public DonneesSimulation(Carte mapData, Incendie fireData[]){
	this.donneesCarte = mapData;
	this.donneesIncendie = fireData;
    }

    public Case getCase(int lig, int col){
	return this.donneesCarte.getCaseAt(lig,col);
    }
    
    public void setIncendie(int lig, int col, int nbLitres){
    }
}
