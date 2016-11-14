package robot.map;

import java.util.ArrayList;
import java.util.List;
import robot.*;
import robot.gui.Drawable;
import robot.gui.BetterGUISimulator;

public class Carte implements Drawable {

    private final int tailleCases;
    private final int nbLignes;
    private final int nbColonnes;

    private final Case[][] map;

    public Carte(int tailleCases , int nbLignes, int nbColonnes) {
	this.tailleCases = tailleCases;
	this.nbLignes = nbLignes;
	this.nbColonnes = nbColonnes;

	map = new Case[nbLignes][];
	for(int i = 0; i < nbLignes; i++) {
	    map[i] = new Case[nbColonnes];
	}
    }

    /**
     * Insert une case dans la carte. La position de la case doit être dans les
     * dimension de la carte.
     * 
     * @param c la case a inserer dans la carte
     */
    public void setCase(Case c){
        Position p = c.getPosition();
	if(isInMapBound(p)){
	    this.map[p.getLigne()][p.getColonne()] = c;
	} else {
	    throw new IllegalArgumentException("Can't set a Case outside of map's bounds");
	}
    }
    
    /**
     * Insert toutes les case dans la carte
     * @param m les cases a inserer
     */
    public void setCarte(Case[][] m) {
        for(Case[] lc : m){
            for(Case c : lc) {
                setCase(c);                
            }
        }
    }

    /**
     * Retourne la liste des cases dans la carte
     * @return la liste des cases
     */
    public List<Case> getCases() {
       List<Case> l = new ArrayList<Case>();
       for(Case[] lc : map){
           for(Case c : lc) {
               l.add(c);               
           }
       }
       return l;
    }
    
    public int getNbLigne() {
	return nbLignes;
    }

    public int getNbColonne() {
	return nbColonnes;
    }

    public int getTailleCases() {
	return tailleCases;
    }

    public boolean isInMapBound(Position pos) {
	return isInMapBound(pos.getLigne(),pos.getColonne());
    }

    private boolean isInMapBound(int lig, int col) {
	return !(lig < 0 || col < 0 || lig >= nbLignes || col >= nbColonnes);
    }

    public Case getCaseAt(Position pos) {
	return getCaseAt(pos.getLigne(),pos.getColonne());
    }

    public Case getCaseAt(int lig, int col) {
	if(!isInMapBound(lig,col)) {
	    throw new IllegalArgumentException("Illegal request of Case outside of map's bounds");
	}
	return map[lig][col];
    }

    public boolean voisinExiste(Case source, Direction dir) {
	return isInMapBound(source.getPosition().deplace(dir));
    }

    public Case getVoisin(Case source, Direction dir) {
	if(!voisinExiste(source,dir)) {
	    throw new IllegalArgumentException("Illegal request of Case outside of map's bounds");
	}
	return getCaseAt(source.getPosition().deplace(dir));
    }
    
    public void draw(BetterGUISimulator gui){
        gui.drawAll(getCases());
    }
}
