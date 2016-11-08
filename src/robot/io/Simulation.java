package robot.io;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

public class Simulation implements Simulable {
    /** L'interface graphique associée */
    private GUISimulator gui;

    /** Les données de simulation */
    private DonneesSimulation data;

    public Simulation(GUISimulator gui, DonneesSimulation data, int ratio, int smallest){
	this.gui = gui;
	gui.setSimulable(this); //association a la gui
	this.data = data;
	draw(ratio, smallest);
    }

    private void draw(int ratio, int pixel){
	gui.reset();
	drawCarte(ratio, pixel);
	drawIncendie(ratio, pixel);
	drawRobots(ratio,pixel);
    }

    @Override
    public void next(){
    }

    @Override
    public void restart(){
    }

    private void drawRobots(int ratio, int pixel){
	for(int i=0; i < data.getNbRobots(); i++){
	    data.drawRobot(i,gui,ratio,pixel);
	}
    }

    private void drawCarte(int ratio, int pixel){
	for(int i=0; i < data.getCarteNbLigne(); i++){
	    for(int j=0; j < data.getCarteNbColonne(); j++){
		data.drawCarte(i,j,gui,ratio,pixel);
	    }
	}
    }

    private void drawIncendie(int ratio, int pixel){
	for(int i=0; i < data.getNbIncendie(); i++){
	    data.drawIncendie(i,gui,ratio,pixel);
	}
    }

}
