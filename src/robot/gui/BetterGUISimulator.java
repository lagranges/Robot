package robot.gui;

import java.awt.Color;
import gui.*;

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

}
