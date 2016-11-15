package robot.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
           l.addAll(Arrays.asList(lc));
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

    public Case[][] getMap(){
	return map;
    }
    
    @Override
    public void draw(BetterGUISimulator gui){
        gui.drawAll(getCases());
    }

    /**
     * Test si il y a une case de type NatureTerrain.EAU a proximite de la case 
     * <code>pos</code>.
     * 
     * @param pos la position a tester
     * @param nt le type de terrain
     * @return true si au moins une case eau est a proximite, false sinon
     */
    public boolean caseAProximite(Case pos, NatureTerrain nt) {
	for(Case c : getListCaseAProximite(pos)){
            if (c.getNatureType() == nt) {
                return true;
            }
	}
	return false;
    }
    public List<Case> getListCaseAProximite(Case pos) {
        List<Case> lc = new ArrayList<Case>();
        Direction[] d = {Direction.EST,Direction.NORD,Direction.OUEST,Direction.SUD};
	for(Direction dir : d){
            if(voisinExiste(pos,dir)){
                lc.add(getVoisin(pos, dir));
            }
	}
	return lc;
    }
    
    public List<Case> getListeCases(NatureTerrain nt){
	List<Case> lc = new ArrayList<Case>();
        for(Case c : getCases()){
            if(c.getNatureType() == nt){
                lc.add(c);
            }
        }
	return lc;
    }
    
    public List<Case> getListeCasesBerge(){
        Set<Case> lc = new HashSet<Case>();
        for(Case eau : getListeCases(NatureTerrain.EAU)){
            for(Case c : getListCaseAProximite(eau)){
                if(c.getNatureType() != NatureTerrain.EAU){
                    lc.add(c);
                }
            }
        }
        return new ArrayList<Case>(lc);
    }
}
