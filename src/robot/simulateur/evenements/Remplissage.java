package robot.simulateur.evenements;

import robot.io.*;
import robot.entities.Robot;

public class Remplissage extends EvenementRobot {
    
    private final int volEau;

    public Remplissage(long date, Robot robot, int volEau){
	super(date, robot);
	this.volEau = volEau;
    }

    private int volumeEauSave = 0;
    
    @Override
    public void execute(DonneesSimulation data){
	Robot robot = getRobot();
        volumeEauSave = robot.getVolumeEau();
        if(robot.peutRemplirEau(data.getCarte())) {
            System.out.println("["+getDate()+"]Remplissage : " + robot + " litre verse : " + volEau);
            robot.remplirEau(volEau);
        }
    }

    @Override
    public void undo(DonneesSimulation data) {
        getRobot().setVolumeEau(volumeEauSave);
    }
    
}
