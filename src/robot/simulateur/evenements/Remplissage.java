package robot.simulateur.evenements;

import robot.io.*;
import robot.entities.Robot;

public class Remplissage extends EvenementRobot {
    
    private final double volEau;

    public Remplissage(long date, Robot robot, double volEau){
	super(date, robot);
	this.volEau = volEau;
    }

    private double volumeEauSave = 0;
    
    @Override
    public void execute(DonneesSimulation data){
	Robot robot = getRobot();
        volumeEauSave = robot.getVolumeEau();
        if(robot.peutRemplirEau(data.getCarte())) {
            robot.remplirEau(volEau);
        }
    }

    @Override
    public void undo(DonneesSimulation data) {
        getRobot().setVolumeEau(volumeEauSave);
    }
    
}
