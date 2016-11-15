package robot.graph;

import robot.map.*;

public class Aretes{

	private Case source;
	private Case destination;
	private int time;
    /**
     * Construit un Arete à partir d'une case de source, d'une case de destination et du temps de déplacement entre les deux cases
     * @param Case s Source de l'arete
     * @param Case d Destination de l'arete
     * @param int t Le temps de déplacement
     *
     */
	public Aretes(Case s, Case d, int t){
		source = s;
		destination = d;
		time = t;
	}
    /**
     * Retourne la Case de la source de l'arete
     * @return la Case de la source
     */
	public Case getSource(){
		return source;
	}
    /**
     * Retourne la Case de la destination de l'arete
     * @return la Case de la destinaiton
     */
	public Case getDestination(){
		return destination;
	}
    /**
     * Retourne la durée du déplacement entre la source et la destination
     * @return la valuer de la durée
     */
	public int getTime(){
		return time;
	}
    /**
     * Retourne la source, la destination de la durée de déplacement
     * @return String de l'information de l'Arete
     */
	@Override
	public String toString(){
		return "["+source.toString()+"->"+destination.toString()+"]"+":"+time;
	}
}
