package robot.map;


import robot.*;

public class Carte {

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


    private boolean isInMapBound(Position pos) {
	return isInMapBound(pos.getLigne(),pos.getColonne());
    }
    
    private boolean isInMapBound(int lig, int col) {
	return !(lig < 0 || col < 0 || lig >= nbLignes || col >= nbColonnes);
    }

    public Case getCaseAt(Position pos) {
	return getCaseAt(pos.getLigne(),pos.getColonne());
    }

    public Case getCaseAt(int lig, int col) {
	if(!isInMapBound(lig,col) ){
	    throw new IllegalArgumentException("Illegal request of Case outside of map's bounds");
	}
	return map[lig][col];
    }

    public boolean voisinExiste(Case source, Direction dir) {
	return isInMapBound(Position.move(source.getPosition(),dir));
    }


}
