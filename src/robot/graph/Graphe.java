package robot.graph;

import robot.*;
import robot.entities.*;
import robot.map.*;
import java.util.*;

public class Graphe{

	private List<Case> listCase;
	private List<Aretes> listAretes;
	private Carte carte;
    private Robot robot;

	public Graphe(Robot robot, Carte carte){
		this.carte = carte;
        this.robot = robot;
        setGraphe();
	}

	public void setGraphe(){
		listCase = new ArrayList<Case>();
		listAretes = new ArrayList<Aretes>();  
		for(int i = 0; i < carte.getNbLigne(); i++)
		for(int j = 0; j < carte.getNbColonne(); j++)
			listCase.add(carte.getCaseAt(i,j));

		for(Case cas : listCase){
			for(Direction dir : Direction.values()){
			if(carte.voisinExiste(cas,dir)){ 
                Case temps = carte.getCaseAt(cas.getPosition().deplace(dir));
                double vitesse =  (robot.getVitesse(cas)+robot.getVitesse(temps))/2;
		if(vitesse != 0){
			int time = (int) ((60.0*10)/(vitesse)); 
			listAretes.add(new Aretes(cas,temps,time));
		}
		else listAretes.add(new Aretes(cas,temps,Integer.MAX_VALUE));
            }                
		}
		}
		
		
		List<Case> willRemoveCase= new ArrayList<Case>();
		for(Case cas : listCase)
		{
			if(!robot.peutDeplacerSur(cas)){
				removeAretes(cas);
				willRemoveCase.add(cas);
			}
		}
		for(Case cas : willRemoveCase){
			listCase.remove(cas);
		}
	}

	@Override
	public String toString(){
		String s=new String("Graph with sommet : \n");
		for(Case cas : listCase){
            s+=(cas.toString()+"\n");
        }
		s+="And the aretes :\n";
		for(Aretes aretes : listAretes) s+=(aretes.toString()+"\n");
		return s;
	}
	
	private void removeAretes(Case cas){
		for(Aretes aretes : AretesDe(cas)) listAretes.remove(aretes);
		for(Aretes aretes : AretesA(cas)) listAretes.remove(aretes);
	}

	// List d'arestes dont la source consiste cas
	public List<Aretes> AretesDe(Case cas){
		List<Aretes> aretesDe = new ArrayList<Aretes>();
		for(Aretes aretes : listAretes){
			if(aretes.getSource()==cas) aretesDe.add(aretes);  
		}
		return aretesDe;
			 
	}

	public List<Aretes> AretesA(Case cas){
		List<Aretes> aretesA = new ArrayList<Aretes>();
		for(Aretes aretes : listAretes){
			if(aretes.getDestination()==cas) aretesA.add(aretes);  
		}
		return aretesA;
	}

	public List<Case> getListCase(){
		return listCase;
	}
	
	public List<Aretes> getListAretes(){
		return listAretes;
	}
	
}


