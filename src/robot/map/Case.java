package robot.map;

import robot.*;
import java.awt.Color;
import robot.gui.BetterGUISimulator;
import robot.gui.Drawable;
import gui.*;

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

    private void drawEau(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = getPosition().getColonne()*ratio;
	int y = getPosition().getLigne()*ratio;
	int size = ratio/pixel;
	Color eau = Color.blue;
	for(int i = x; i < x + ratio; i += size){
	    for(int j = y; j < y + ratio; j += size){
		gui.addGraphicalElement(new Rectangle(i, j, eau, eau, size));
	    }
	}

	if(ratio%pixel != 0){
	    for(int i = size*pixel; i < ratio; i++){
		for(int j = size*pixel; j < ratio; j++){
		    gui.addGraphicalElement(new Rectangle(i, j, eau, eau, 1));
		}
	    }
	}
	
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*4, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*4, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*5, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*6, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*6, eau.darker(), eau.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*1, y + size*6, eau.darker(), eau.darker(), size));
    }

    private void drawForet(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = getPosition().getColonne()*ratio;
	int y = getPosition().getLigne()*ratio;
	int size = ratio/pixel;
	Color foret = new Color(34,139,34);
	Color brown = new Color(184,134,11);
	for(int i = x; i < x + ratio; i += size){
	    for(int j = y; j < y + ratio; j += size){
		gui.addGraphicalElement(new Rectangle(i, j, foret, foret, size));
	    }
	}

	if(ratio%pixel != 0){
	    for(int i = size*pixel; i < ratio; i++){
		for(int j = size*pixel; j < ratio; j++){
		    gui.addGraphicalElement(new Rectangle(i, j, foret, foret, 1));
		}
	    }
	}

	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*2, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*3, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*3, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*4, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, foret.darker(), foret.darker(), size));
        gui.addGraphicalElement(new Rectangle(x + size*7, y + size*4, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, foret.darker(), foret.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, brown, brown, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, brown, brown, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*6, brown, brown, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*7, brown, brown, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*7, brown, brown, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*7, brown, brown, size));
    }

    private void drawRoche(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = getPosition().getColonne()*ratio;
	int y = getPosition().getLigne()*ratio;
	int size = ratio/pixel;
	Color roche = new Color(189,183,107);
	for(int i = x; i < x + ratio; i += size){
	    for(int j = y; j < y + ratio; j += size){
		gui.addGraphicalElement(new Rectangle(i, j, roche, roche, size));
	    }
	}

	if(ratio%pixel != 0){
	    for(int i = size*pixel; i < ratio; i++){
		for(int j = size*pixel; j < ratio; j++){
		    gui.addGraphicalElement(new Rectangle(i, j, roche, roche, 1));
		}
	    }
	}

	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*1, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*2, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*2, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*2, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*3, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*3, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*3, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*3, roche.darker(), roche.darker(), size));

	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*6, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*7, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*7, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*7, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*1, y + size*8, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*8, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*8, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*8, roche.darker(), roche.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*8, roche.darker(), roche.darker(), size));
    }

    private void drawTerrainLibre(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = getPosition().getColonne()*ratio;
	int y = getPosition().getLigne()*ratio;
	int size = ratio/pixel;
	Color terrain = Color.green;
	for(int i = x; i < x + ratio; i += size){
	    for(int j = y; j < y + ratio; j += size){
		gui.addGraphicalElement(new Rectangle(i, j, terrain.brighter(), terrain.brighter(), size));
	    }
	}

	if(ratio%pixel != 0){
	    for(int i = size*pixel; i < ratio; i++){
		for(int j = size*pixel; j < ratio; j++){
		    gui.addGraphicalElement(new Rectangle(i, j, terrain.brighter(), terrain.brighter(), 1));
		}
	    }
	}
	
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*2, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*3, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*3, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, terrain.darker(), terrain.darker(), size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, terrain.darker(), terrain.darker(), size));
    }
  
    private void drawHabitat(BetterGUISimulator gui){
	int ratio = gui.getTailleCase();
	int pixel = gui.getTaillePixel();
	int x = getPosition().getColonne()*ratio;
	int y = getPosition().getLigne()*ratio;
	int size = ratio/pixel;
	Color habitat = new Color(169,169,169);
	for(int i = x; i < x + ratio; i += size){
	    for(int j = y; j < y + ratio; j += size){
		gui.addGraphicalElement(new Rectangle(i, j, habitat, habitat, size));
	    }
	}

	if(ratio%pixel != 0){
	    for(int i = size*pixel; i < ratio; i++){
		for(int j = size*pixel; j < ratio; j++){
		    gui.addGraphicalElement(new Rectangle(i, j, habitat, habitat, 1));
		}
	    }
	}

	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*1, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*1, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*1, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*2, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*2, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*2, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*2, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*2, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*2, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*8, y + size*3, Color.red, Color.red, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*4, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*4, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*4, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*4, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*4, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*5, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*5, Color.blue, Color.blue, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*5, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*5, Color.blue, Color.blue, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*5, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*6, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*6, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*6, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*6, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*6, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*7, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*7, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*7, Color.orange, Color.orange, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*7, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*7, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*3, y + size*8, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*4, y + size*8, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*5, y + size*8, Color.orange, Color.orange, size));
	gui.addGraphicalElement(new Rectangle(x + size*6, y + size*8, Color.white, Color.white, size));
	gui.addGraphicalElement(new Rectangle(x + size*7, y + size*8, Color.white, Color.white, size));

    }
    
    @Override
    public void draw(BetterGUISimulator gui){
	switch(getNatureType()){
	case EAU: 
	    drawEau(gui);
	    break;
	case FORET: 
	    drawForet(gui);
	    break;
	case ROCHE: 
	    drawRoche(gui);
	    break;
	case TERRAIN_LIBRE: 
	    drawTerrainLibre(gui);
	    break;
	case HABITAT: 
	    drawHabitat(gui);
	    break;
	}
    
    }

    @Override
    public String toString(){
        return "("+pos.getLigne()+","+pos.getColonne()+")" + ": "+this.getNatureType().toString();
    }
}
