package robot.graph;

import robot.*;
import robot.map.*;
import java.util.*;

public class Graphe{

	private List<Case> listCase;
	private List<Aretes> listAretes;
	private Carte carte;
	public Graphe(Carte carte){
		this.carte = carte;
	}

	public void setGraphe(){
		listCase = new ArrayList<Case>();
		listAretes = new ArrayList<Aretes>();  
		for(int i = 0; i < carte.getNbLigne(); i++)
		for(int j = 0; j < carte.getNbColonne(); j++)
			listCase.add(carte.getCaseAt(i,j));

		for(Case cas : listCase){
			for(Direction dir : Direction.values()){
			if(carte.voisinExiste(cas,dir)) 
				listAretes.add(new Aretes(cas,carte.getCaseAt(cas.getPosition().deplace(dir)),1));	
		}
		}
		
		
		List<Case> willRemoveCase= new ArrayList<Case>();
		for(Case cas : listCase)
		{
			if(cas.getNatureType().equals(NatureTerrain.FORET)){
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
		String s=new String("Graph with sommet :");
		for(Case cas : listCase) s+=cas.toString();
		s+="and aretes";
		for(Aretes aretes : listAretes) s+=aretes.toString();
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


