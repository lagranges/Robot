package robot.map;

import robot.*;
import gui.*;
import java.awt.Color;

/**
 * La classe Case représente un élément atomique d'une carte.
 * Une Case est composée d'une Position et d'un type NatureTerrain.
 *
 */
public class Case implements Drawable {

    /**
     * La position de la case sur la carte
     */
    private final Position pos;

    /**
     * La nature du terrain représenter par la case
     */
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
     * @see          #Case(Position, NatureTerrain)
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
    
    @Override
    public void draw(GUISimulator gui){
	switch(type) {
	case EAU:
	    drawEau(pos, gui);
	    break;
	case ROCHE:
	    drawRoche(pos, gui);
	    break;
	case TERRAIN_LIBRE:
	    drawTerrainLibre(pos, gui);
	    break;
	case HABITAT :
	    drawHabitat(pos, gui);
	    break;
	}
    }

    private static void drawEau(Position p, GUISimulator gui) {
	Color bleuFoncee = new Color(21,40,189);
	Color bleuClair = new Color(30,144,255);

	int x = p.getColonne();
	int y = p.getLigne();
	gui.addGraphicalElement(new Rectangle(x,y,bleuFoncee,bleuFoncee,10));
	gui.addGraphicalElement(new Rectangle(x-2,y-4,bleuFoncee,bleuFoncee,2,4));
	gui.addGraphicalElement(new Rectangle(x-6,y-3,bleuFoncee,bleuFoncee,2,4));
    }

    private static void drawRoche(Position p, GUISimulator gui) {
	Color gris = new Color(150,150,150);
	int x = p.getColonne();
	int y = p.getLigne();
	gui.addGraphicalElement(new Rectangle(x,y,gris,gris,10));
    }

    private static void drawTerrainLibre(Position p, GUISimulator gui) {
	Color vert = new Color(58,157,35);
	int x = p.getColonne();
	int y = p.getLigne();
	gui.addGraphicalElement(new Rectangle(x,y,vert,vert,10));
    }

    private static void drawHabitat(Position p, GUISimulator gui) {
	Color brique = new Color(178,34,34);
	int x = p.getColonne();
	int y = p.getLigne();
	gui.addGraphicalElement(new Rectangle(x,y,brique,brique,10));
    }

}
