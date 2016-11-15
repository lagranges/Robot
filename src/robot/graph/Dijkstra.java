package robot.graph;

import robot.map.*;
import java.util.*;


public class Dijkstra {

        private final List<Case> listCase;
        private final List<Aretes> listAretes;

        private Set<Case> setCaseTraite;
        private Set<Case> setCaseEnTraitant;
        private Map<Case, Case> predecesseur;
        private Map<Case, Integer> time;
        /**
         * Construit un Dijkstra correspondant a un graphe
         * @param Graphe graph
         */
        public Dijkstra(Graphe graph) {
                this.listCase = new ArrayList<Case>(graph.getListCase());
                this.listAretes = new ArrayList<Aretes>(graph.getListAretes());
        }
        /**
         * Calcule le plus court chemin correspondant à une case source 
         * @param Case source
         *
         */
        public void traiterGraphe(Case source) {
                setCaseTraite = new HashSet<Case>();
                setCaseEnTraitant = new HashSet<Case>();
                time = new HashMap<Case, Integer>();
                predecesseur = new HashMap<Case, Case>();
                time.put(source, 0);
                setCaseEnTraitant.add(source);
                while (setCaseEnTraitant.size() > 0) {
                        Case cas = getMin(setCaseEnTraitant);
                        setCaseTraite.add(cas);
                        setCaseEnTraitant.remove(cas);
                        updateCaseEnTraitant(cas);
                }
        }

        private void updateCaseEnTraitant(Case cas) {
                List<Case> adjacentNodes = getCousin(cas);
                for (Case destination : adjacentNodes) {
                        if (getTime(destination) > getTime(cas) + getTimeEntre(cas, destination)) {
                                time.put(destination, getTime(cas) + getTimeEntre(cas, destination));
                                predecesseur.put(destination, cas);
                                setCaseEnTraitant.add(destination);
                        }
                }

        }

        private int getTimeEntre(Case cas, Case destination) {
                for (Aretes aretes : listAretes) {
                        if (aretes.getSource().equals(cas) && aretes.getDestination().equals(destination)) 
                                return aretes.getTime();
                }
                throw new RuntimeException(" No chemin ");
        }

        private List<Case> getCousin(Case cas) {
                List<Case> cousin = new ArrayList<Case>();
                for (Aretes aretes : listAretes) {
                        if (aretes.getSource().equals(cas) && !isTraite(aretes.getDestination())) 
                                cousin.add(aretes.getDestination());
                }
                return cousin;
        }

        private Case getMin(Set<Case> cases) {
                Case min = null;
                for (Case cas : cases) {
                        if (min == null) min = cas;
                        else if (getTime(cas) < getTime(min)) min = cas;
                }
                return min;
        }

        private boolean isTraite(Case cas) {
                return setCaseTraite.contains(cas);
        }


        /**
         * Retourne le temps nécessaire pour se rendre d'une case à une autre case. 
         *
         * @param destination la case d'arrivée
         * @return le temps de déplacement, Integer.MAX_VALUE si aucun chemin
         */
        public int getTime(Case destination) {

                Integer t = time.get(destination);
                if (t == null) return Integer.MAX_VALUE;
                else return t;             
        }


        /**
         * Retourne la liste des temps de trajet dans le plus court chemin
         * NULL si il n'y a aucun chemin
         * @param destination la case d'arrivée
         * @return la liste des cases de chemin , null si aucun chemin.
         */
        public List<Integer> getListTime(Case destination) {
                ArrayList<Integer> listTime = new ArrayList<Integer>();
                Case step = destination;

                if (predecesseur.get(step) == null) {
                        return null;
                }
                listTime.add(this.getTime(step));
                while (predecesseur.get(step) != null) {
                        step = predecesseur.get(step);
                        listTime.add(this.getTime(step));
                }

                Collections.reverse(listTime);
                return listTime;
        }
        /**
         * Retourne la list de Cases du plus court chemin
         * , retourne NULL si il n'y a aucun chemin
         * @param destination la case d'arrivée
         * @return la liste des durées de déplacement entre 2 cases de la chemin
         */
        public List<Case> getChemin(Case destination) {
                ArrayList<Case> chemin = new ArrayList<Case>();
                Case step = destination;

                if (predecesseur.get(step) == null) {
                        return null;
                }
                chemin.add(step);
                while (predecesseur.get(step) != null) {
                        step = predecesseur.get(step);
                        chemin.add(step);
                }

                Collections.reverse(chemin);
                return chemin;
        }

	
}
