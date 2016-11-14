package robot.gui;

import java.awt.Color;
import java.util.List;
import gui.*;
import robot.*;

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

    public void drawAll(List<? extends Drawable> l) {
	// for( Drawable d:l) == Iterator<Drawable> it = l.getIterator();
	for( Drawable d : l) {
	    d.draw(this);
	}
    }

    public void drawAll(Drawable[] l) {
	// for( Drawable d:l) == for(int i = 0;i<l.length;i++)
	for( Drawable d : l) {
	    d.draw(this);
	}
    }

    public void drawAll(Drawable d) {
	d.draw(this);
    }
}
