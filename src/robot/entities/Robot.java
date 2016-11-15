package robot.entities;

import robot.simulateur.evenements.Intervention;
import robot.simulateur.evenements.Remplissage;
import robot.simulateur.evenements.Deplacement;
import robot.simulateur.evenements.Evenement;
import java.awt.Color;
import java.util.*;

import robot.map.*;
import robot.graph.*;
import robot.simulateur.*;
import robot.io.*;
import robot.*;

import gui.GUISimulator;

/**
 * Un robot présente sur une case, dans une carte.
 *
 * @see robot.map.case
 * @see robot.map.carte
 */
public abstract class Robot extends Entity {

    /**
     * Le nombre de litres d'eau dont le robot est en train de contient
     */
    private int volumeEau;

    /**
     * Le nombre de litres d'eau dont le robot peut contenir
     */
    private int volumeMax;

    /**
     * La vitesse par défault, mais peut etre lue dans la fichier de données
     */
    private double vitesseDeplacementDefault;

    /**
     * La vitesse de remplissage , unitaire : litre/s
     */
    private double vitesseRemplissage;

    /**
     * La vitesse de déversement , unitaire : litre/s
     */
    private double vitesseDeversement;

    /**
     * L'indicateur boolean si le robot est occupe
     */
    private boolean occupe = false;

    /**
     * Contructeur d'un nouveau Robot
     *
     * @param cas la cas sur laquelle il se trouve
     * @param volumeEau la nombre de litres qu'il contient
     * @param lesvitesse
     *
     */
    public Robot(Case cas, int volumeEau, int volumeMax, double vitesseDeplacementDefault, double vitesseRemplissage, double vitesseDeversement) {
        super(cas);
        this.volumeEau = volumeEau;
        this.volumeMax = volumeMax;
        this.vitesseDeplacementDefault = vitesseDeplacementDefault;
        this.vitesseRemplissage = vitesseRemplissage;
        this.vitesseDeversement = vitesseDeversement;
    }

    public boolean getStatus() {
        return this.occupe;
    }

    public void setStatus(boolean bool) {
        this.occupe = bool;
    }

    /**
     * Retourne : la volume de l'eau dans le Robot
     */
    public int getVolumeEau() {
        return this.volumeEau;
    }

    /**
     * Définit la case du robot
     */
    public void setPosition(Case cas) {
        this.setCase(cas);
    }

    /**
     * Modifier la volume de l'eau dans le robot
     */
    public void setVolumeEau(int volumeEau) {
        this.volumeEau = volumeEau;
    }

    /**
     * Modifier la vitesse de déplacement du robot
     */
    public void setVitesseDeplacement(double vitesse) {
        this.vitesseDeplacementDefault = vitesse;
    }

    /**
     * Retourne : la volume maxcimale dont le robot peut contenir
     */
    public int getVolumeMax() {
        return this.volumeMax;
    }

    /**
     * Retourne : la vitesse de déplacement par défault du robot
     */
    public double getVitesseDeplacementDefault() {
        return this.vitesseDeplacementDefault;
    }

    /**
     * Retourne : la vitesse de remplissage du robot
     */
    public double getVitesseRemplissage() {
        return this.vitesseRemplissage;
    }

    /**
     * Retourne : la volume de déversement du robot
     */
    public double getVitesseDeversement() {
        return this.vitesseDeversement;
    }

    /**
     * Le robot deverser sur un incendie
     *
     * @param la nombre de litres qu'il se faut deverser
     */
    public void deverserEau(double vol) {
        volumeEau -= vol;
    }

    /**
     * Le robot remplir le reservoir par second
     *
     * @param le nombre de litre par second
     */
    public void remplirEau(int vol) {
        volumeEau += vol;
        if (volumeEau > getVolumeMax()) {
            volumeEau = getVolumeMax();
        }
    }

    /**
     * Retourne le temps nécessaire pour deverser vol litres de l'eau, unitaire
     * s
     *
     * @param la nombre de litres qu'il se faut deverser
     */
    public int tempsDeversement(int vol) {
        return (int) (vol / vitesseDeversement);
    }

    /**
     * Le robot remplit son réservoir
     */
    public void remplirReservoir() {
        volumeEau = volumeMax;
    }

    /**
     * Retourne le temps nécessaire pour remplir completement, unitaire s
     *
     */
    public int tempsRemplissage() {
        return (int) ((volumeMax - volumeEau) / vitesseRemplissage);
    }

    /**
     * Retourne True si le robot peut se déplacer sur la nature Terrain sinon
     * retourne False
     *
     * @param natureTerrain
     * @see NatureTerrain
     */
    public boolean peutDeplacerSur(Case cas) {
        return peutDeplacerSur(cas.getNatureType());
    }

    public double getVitesse(Case cas) {
        return getVitesse(cas.getNatureType());
    }

    public abstract boolean peutDeplacerSur(NatureTerrain natureTerrain);

    /**
     * Retourne la vitesse du robot sur les Terrains différents
     *
     * @param natureTerrain : la type de natureTerrain
     * @see NatureTerrain
     */
    public abstract double getVitesse(NatureTerrain natureTerrain);

    /**
     * Retourne le temps nécessaire pour se deplacer de sa position à la case ,
     * n'est pas encore définir
     *
     * @param cas : Case
     */
    public abstract int tempsDeplacement(Case cas);

    /**
     *
     * Retourne si le robot peut remplir son réservoir à la position où il se
     * trouve. Cette condition peux varier en fonction du type de robot.
     *
     * @param carte la carte sur lequelle le robot se trouve
     * @return v
     */
    public abstract boolean peutRemplirEau(Carte carte);

    /**
     * Retourne si le robot peut remplir son réservoir sur une case de type EAU.
     * 
     * @return true si le remplissage sur un case eau est possible, sinon false
     */
    public abstract boolean peutRemplirSurCaseEau();

    /**
     * Trouver le chemin possible retourne NULL s'il ne trouve pas
     */
    public int calculPlusCourtChemin(DonneesSimulation data, Case destination) {
        Graphe g = new Graphe(this, data.getCarte());
        Dijkstra dijkstra = new Dijkstra(g);
        dijkstra.traiterGraphe(getCase());
        return dijkstra.getTime(destination);
    }

    /**
     * Ajoute des evenement de deplacement dans la liste d'evenement par rapport
     * de vitesse de simulateur
     */
    public void programEventDeplacement(Simulateur sim, DonneesSimulation data, Case destination) {
        Graphe g = new Graphe(this, data.getCarte());
        Dijkstra dijkstra = new Dijkstra(g);
        dijkstra.traiterGraphe(getCase());
        List<Case> chemin = dijkstra.getChemin(destination);
        List<Integer> t = dijkstra.getListTime(destination);

        long currentDate = 0;
        Evenement lastE = sim.getLastEvenement(this);
        if (lastE != null) {
            currentDate = lastE.getDate();
        }

        try {

            Case[] listC = chemin.toArray(new Case[chemin.size()]);
            Integer[] listT = t.toArray(new Integer[t.size()]);
            for (int i = 0; i < listC.length - 1; i++) {
                sim.ajouteEvenement(new Deplacement(currentDate + (long) listT[i + 1], this, Position.getDirection(listC[i].getPosition(),
                        listC[i + 1].getPosition())));
            }
        } catch (Exception e) {
            System.out.println("Can't get to  from this actual robot position :" + getCase().toString());
        }
    }

    public void programEventRemplissage(Simulateur sim) {
        long currentDate = 0;
        Evenement lastE = sim.getLastEvenement(this);
        if (lastE != null) {
            currentDate = lastE.getDate();
        }
        
        int volCurrent = 0;
        int i = 0;

        while (volCurrent < getVolumeMax()) {
            sim.ajouteEvenement(new Remplissage(currentDate + i +1, this, (int) getVitesseRemplissage()));
            volCurrent += getVitesseRemplissage();
            i++;
        }

    }

    public void programEventIntervention(Simulateur sim, DonneesSimulation data, Case pos) {
        long currentDate = 0;
        Evenement lastE = sim.getLastEvenement(this);
        if (lastE != null) {
            currentDate = lastE.getDate();
        }
        System.out.println("progIntervention : robot " + this);
        Incendie inc = data.getIncendieAt(pos);
        if (inc != null) {
            int intensite = inc.getNbLitresEauRestantPourExtinction();
            int nombreInterventions = (int) Math.min(Math.floor(getVolumeEau() / getVitesseDeversement()),Math.floor(intensite / getVitesseDeversement()));
            System.out.println("progIntervention : robot: " + this +" nbIter" + nombreInterventions);
            for(int i=0; i< nombreInterventions; i++){
                sim.ajouteEvenement(new Intervention(currentDate + i + 1, this, (int) getVitesseDeversement()));
                //deverserEau(getVitesseDeversement()); 
                //inc.nbLitresEauArrive(getVitesseDeversement());
            }
            
            /*
            while ((getVolumeEau() > 0) && (intensite > 0)) {
                sim.ajouteEvenement(new Intervention(currentDate + i, this, (int) getVitesseDeversement()));
                deverserEau(getVitesseDeversement());
                volCurrent -= getVitesseDeversement();
                intensite -= getVitesseDeversement();
                i++;
            }*/
        }

    }
}
