package robot.io;

import robot.gui.BetterGUISimulator;
import gui.Simulable;

public class Simulation implements Simulable {
    /** L'interface graphique associée */
    private BetterGUISimulator gui;

    /** Les données de simulation */
    private DonneesSimulation data;

    public Simulation(BetterGUISimulator gui, DonneesSimulation data) {
	this.gui = gui;
	gui.setSimulable(this); //association a la gui
	this.data = data;
	draw();
    }

    private void draw(){
	gui.reset();
	drawCarte();
	drawIncendies();
	drawRobots();
    }

    @Override
    public void next(){
    }

    @Override
    public void restart(){
    }

    private void drawRobots(){
	gui.drawAll(data.getRobots());
    }

    private void drawCarte(){
	gui.drawAll(data.getCarte());
    }

    private void drawIncendies(){
	gui.drawAll(data.getIncendies());
    }

}
