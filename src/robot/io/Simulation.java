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

    private void draw(int ratio, int smallest){
	gui.reset();
	//methods to be placed here
	data.drawCarte(gui, ratio, smallest);
	data.drawIncendie(gui, ratio, smallest);
    }

    @Override
    public void next(){
    }

    @Override
    public void restart(){
    }

    private void drawRobots(){
	for(int i=0; i < data.getNbRobots(); i++){
	    data.drawRobot(i,gui);
	}
    }

}
