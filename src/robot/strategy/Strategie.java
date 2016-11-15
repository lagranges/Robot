package robot.strategy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import robot.io.*;
import robot.map.*;
import robot.entities.*;
import robot.simulateur.*;

public abstract class Strategie{
    private DonneesSimulation data;

    private Simulateur sim;

    private Map<Robot,Incendie> association;
    
    private Set<Incendie> incendiesAffecte;
    private List<Incendie> incendiesEnCours;
    
    private List<Robot> robots;

    private int temps = 0;
    
    public Strategie(Simulateur sim, DonneesSimulation data){
	this.data = data;
	this.sim = sim;
	this.incendiesAffecte = new HashSet<Incendie>(data.getIncendies());
        this.incendiesEnCours = new ArrayList<Incendie>(data.getIncendies());
        this.robots = data.getRobots();
	this.association = new HashMap<Robot,Incendie>();
    }

    protected DonneesSimulation getDonneesSimulation(){
	return this.data;
    }

    protected Simulateur getSim(){
	return this.sim;
    }
    
    public Incendie chooseRandomIncendie(){
	Random rand = new Random();
        int i = rand.nextInt(getIncendiesEnCours().size());
	return getIncendiesEnCours().get(i);
    }

    public boolean allDejaTraiteIncendie() {
        for(Incendie i : getIncendiesEnCours()){
            if(! incendiesAffecte.contains(i)){
                return false;
            }
        }
        return true;
    }

    public boolean isIncendieDejaAffecte(Incendie i){
        return getIncendiesAffecte().contains(i);
    }

    public void affecterIncendie(Robot r, Incendie i){
	association.put(r, i);
        getIncendiesAffecte().add(i);
        sendRobot(sim, r, i);
    }

    public void libererIncendie(Robot r){
        Incendie i = association.get(r);
        if(i != null){
            if(i.estEteint()) {   
                incendiesEnCours.remove(i);
            }
            association.put(r, null);
            incendiesAffecte.remove(i);
            r.setStatus(false);
        }
    }
    
    public int getTemps(){
	return this.temps;
    }

    public void incrementTemps(){
	this.temps++;
    }

    //Wait t en s pour envoyer la commande suivant
    public void wait(int t){
	incrementTemps();
	while(temps%t != 0){
	    incrementTemps();
	}
    }

    public abstract void executeStrategie();

    
    public abstract void sendRobot(Simulateur sim, Robot robot, Incendie inc);

    /**
     * @return the incendiesAffecte
     */
    public Set<Incendie> getIncendiesAffecte() {
        return incendiesAffecte;
    }

    /**
     * @return the incendiesAffecte
     */
    public Collection<Incendie> getIncendiesNonAffecte() {
        List<Incendie> li = new ArrayList<Incendie>();
        for(Incendie i : incendiesEnCours){
            if(! incendiesAffecte.contains(i)){
                li.add(i);
            }
        }
        return li;
    }

    /**
     * @return the incendiesEnCours
     */
    public List<Incendie> getIncendiesEnCours() {
        return incendiesEnCours;
    }

    /**
     * @return the robots
     */
    public List<Robot> getRobots() {
        return robots;
    }

    public List<Robot> getRobotsDisponible() {
        List<Robot> lr = new ArrayList<Robot>();
        for(Robot r : robots){
            Incendie i = association.get(r);
            if(i == null){
                lr.add(r);
            } else {
                if(i.estEteint()){
                    libererIncendie(r);
                }
            }
        }
        return lr;
    }
}
