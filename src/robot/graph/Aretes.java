package robot.graph;

import robot.map.*;

public class Aretes{

	private Case source;
	private Case destination;
	private int time;
	public Aretes(Case s, Case d, int t){
		source = s;
		destination = d;
		time = t;
	}

	public Case getSource(){
		return source;
	}

	public Case getDestination(){
		return destination;
	}

	public int getTime(){
		return time;
	}
	@Override
	public String toString(){
		return "["+source.toString()+"->"+destination.toString()+"]"+":"+time;
	}
}
