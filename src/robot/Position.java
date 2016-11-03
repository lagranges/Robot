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

    @Override
    public int hashCode() {
	int hash = 2;
	hash = hash * 21 + ligne;
	hash = hash * 21 + colonne;
	return hash;
    }

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

}
