package robot.gui;

import java.awt.Color;
import java.util.List;
import gui.*;

/**
 * La class BetterGUISimulator est une extension de la class GUISimulator.
 * Cette class rajoute un accès a des paramètres utile a la représentation 
 * visuelle de certain éléments. De plus cette class ajout des méthodes utilitaire
 * pour l'affichage.
 */
public class BetterGUISimulator extends GUISimulator { 

    private final int tailleCaseGraphique;
    private final int taillePixelGraphique;

    public BetterGUISimulator(int largeurFenetre, int hauteurFenetre,Color couleurFond, int tailleCase, int taillePixel) {
	super(largeurFenetre,hauteurFenetre,couleurFond);
	tailleCaseGraphique = tailleCase;
	taillePixelGraphique = taillePixel;
    }

    public int getTailleCase() {
	return tailleCaseGraphique;
    }
    
    public int getTaillePixel() {
	return taillePixelGraphique;
    }

    /**
     * Affiche tout les éléments d'une liste
     * 
     * @param l la liste des éléments à dessiner
     */
    public void drawAll(List<? extends Drawable> l) {
	for( Drawable d : l) {
	    d.draw(this);
	}
    }

    /**
     * Affiche tout les éléments d'un tableau liste
     * @param l le tableau d'éléments à dessiner
     */
    public void drawAll(Drawable[] l) {
	for( Drawable d : l) {
	    d.draw(this);
	}
    }

    /**
     * Dessine un élément de type Drawable;
     * @param d l'élément à dessiner
     */
    public void draw(Drawable d) {
	d.draw(this);
    }
}
