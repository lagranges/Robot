package robot.map;

import robot.*;

/**
 * La classe Case represente un élément atomique d'une carte.
 * Une Case est composer d'une Position et d'un type NatureTerrain.
 *
 */
public class Case {

    private final Position pos;
    private final NatureTerrain type;

    /**
     * Crée une nouvelle Case avec une position et une nature du terrain.
     *
     * @param p      la Position de la case sur une carte
     * @param nature le type de la case {@link NatureTerrain}
     * @see          robot.Position
     * @see          NatureTerrain
     */
    public Case(Position p, NatureTerrain nature) {
	pos = p;
	type = nature;
    }

    /**
     * Crée une nouvelle Case avec une position et une nature du terrain.
     *
     * @param l      la ligne à laquelle la case se situe
     * @param c      la colonne à laquelle la case se site
     * @param nature le type de la case {@link NatureTerrain}
     * @see          robot.Position
     * @see          NatureTerrain
     * @see          Class#Constructor(Position, NatureTerrain)
     */
    public Case(int l, int c, NatureTerrain nature) {
	this(new Position(l,c),nature);
    }

    /**
     * Accesseur de la position de la case.
     *
     * @return la position de la case
     * @see    robot.Position
     */
    public Position getPosition() {
	return pos;
    }

    /**
     * Accesseur de la nature du terrain de la case.
     *
     * @return le type de la case
     * @see    robot.map.NatureTerrain
     */
    public NatureTerrain getNatureType() {
	return type;
    }

}
