package robot.map;

import robot.*;

public class Case {

    private final Position pos;
    private final NatureTerrain type;

    public Case(int l, int c, NatureTerrain nt) {
	pos = new Position(l,c);
	type = nt;
    }
    
    public Position getPosition() {
	return pos;
    }
    
    /*
    public int getLigne() {
	return pos.getLigne();
    }

    public int getColonne() {
	return pos.getColonne();
    }
    */
    public NatureTerrain getNatureType() {
	return type;
    }

}
