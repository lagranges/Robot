package robot;

public class Position {

    private final int ligne;
    private final int colonne;

    public Position(int lig, int col) {
	ligne = lig;
	colonne = col;
    }

    public int getLigne() {
	return ligne;
    }

    public int getColonne() {
	return colonne;
    }

    public static Position move(Position pos, Direction dir) {
	switch (dir) {
	case NORD:
	    return new Position(pos.getLigne()-1,pos.getColonne());
	case SUD:
	    return new Position(pos.getLigne()+1,pos.getColonne());
	case EST:
	    return new Position(pos.getLigne(),pos.getColonne()+1);
	case OUEST:
	    return new Position(pos.getLigne(),pos.getColonne()-1);
	}
        return pos;
    }

}
