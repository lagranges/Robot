package robot.map;

import robot.*;
import gui.GUISimulator;

public class Carte implements Drawables {

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

    /*
     * set nature case(Hariz)
     */
    public void setCase(Position p, Case c){
	if(isInMapBound(p)){
	    this.map[p.getLigne()][p.getColonne()] = c;
	} else {
	    throw new IllegalArgumentException("Can't set a Case outside of map's bounds");
	}
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
    
    public void draw(GUISimulator gui){}
}
