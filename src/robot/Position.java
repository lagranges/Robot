package robot;

/**
 * La class Position représente un couple ligne, colonne.
 * Les objets Position sont immutable.
 *
 */
public class Position {

    private final int ligne;
    private final int colonne;

    /**
     * Constructeur d'une nouvelle Position à partir d'une ligne et
     * d'une colonne.
     *
     * @param lig la valeur en lignes de la Position
     * @param col la valeur en colonnes de la Position
     */
    public Position(int lig, int col) {
	ligne = lig;
	colonne = col;
    }

    /**
     * Constructeur d'une nouvelle Position par copie.
     *
     * @param p la Position copié
     */
    public Position(Position p) {
	ligne = p.getLigne();
	colonne = p.getColonne();
    }

    /**
     * Constructeur d'une nouvelle Position à partir d'une position initial et
     * d'une direction. Applique la direction <code>dir</code> à la Position
     * <code>p</code>, crée une nouvelle Position avec le résultat du
     * déplacement.
     *
     * @param p   la Position initiale
     * @param dir la Direction à appliquer
     * @see       Direction
     */
    public Position(Position p, Direction dir) {
	ligne   = p.getLigne()   + dir.getDeltaLigne();
	colonne = p.getColonne() + dir.getDeltaColonne();
    }

    /**
     * Retourne la ligne correspondante à cette Position.
     *
     * @return la valeur de la ligne
     */
    public int getLigne() {
	return ligne;
    }

    /**
     * Retourne la colonne correspondante à cette Position.
     *
     * @return la valeur de la colonne
     */
    public int getColonne() {
	return colonne;
    }

    /**
     * Crée une nouvelle Position à partir de la position courante et de la
     * Direction <code>dir</code>. La direction est appliquer à la position
     * courante. Crée un nouvelle objet Position.
     *
     * @param  dir la Direction à appliquer
     * @return     la nouvelle position
     * @see        Direction
     */
    public Position deplace(Direction dir) {
	return new Position(this,dir);
    }

    /**
     * Retourne la direction associer a la difference entre deux position
     * 
     * @param before la position initial
     * @param after la position suite a un déplacement
     * @return la direction associer au déplacement, null si aucune Direction ne correspond
     */
    public static Direction getDirection(Position before, Position after){
	int dLigne = after.getLigne() - before.getLigne();
	int dColonne =  after.getColonne() - before.getColonne();
	for(Direction d : Direction.values()) {
	    if (d.getDeltaLigne() == dLigne && d.getDeltaColonne() == dColonne) {
		return d;
	    }
	}
	return null;
    }

    /**
     * {@inheritDoc}
     * Retourne la valeur de hashage d'un Position.
     * Si deux positions sont égales leurs hashcode l'est également.
     * L'inverse n'est pas vérifier.
     * 
     * @return  le hashcode de cette position
     */
    @Override
    public int hashCode() {
	int hash = 2;
	hash = hash * 21 + ligne;
	hash = hash * 21 + colonne;
	return hash;
    }

    /**
     * Deux positions sont egales si leur colonne et leur ligne sont égales
     * 
     * @param obj objet a tester
     * @return true si obj egale a this, sinon false
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	// instanceof return false if obj is null
	if (obj instanceof Position) {
	    Position other = (Position) obj;
	    return getLigne() == other.getLigne()
		&& getColonne() == other.getColonne();
	}
	return false;
    }

    /**
     * Retourne la représentation en String de cette position.
     * Sous la forme "(ligne,colonne)".
     * 
     * @return "(ligne,colonne)"
     */
    @Override
    public String toString() {
	return "("+getLigne()+","+getColonne()+")";
    }
}
