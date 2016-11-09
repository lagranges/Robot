package robot.graph;

import robot.map.*;
import java.util.*;


public class Dijkstra {

        private final List<Case> listCase;
        private final List<Aretes> listAretes;
        private Set<Case> setCaseTraite;
        private Set<Case> setCaseCousin;
        private Map<Case, Case> predecesseur;
        private Map<Case, Integer> time;

        public Dijkstra(Graphe graph) {
                this.listCase = new ArrayList<Case>(graph.getListCase());
                this.listAretes = new ArrayList<Aretes>(graph.getListAretes());
        }

        public void execute(Case source) {
                setCaseTraite = new HashSet<Case>();
                setCaseCousin = new HashSet<Case>();
                time = new HashMap<Case, Integer>();
                predecesseur = new HashMap<Case, Case>();
                time.put(source, 0);
                setCaseCousin.add(source);
                while (setCaseCousin.size() > 0) {
                        Case cas = getMinimum(setCaseCousin);
                        setCaseTraite.add(cas);
                        setCaseCousin.remove(cas);
                        findMinimalDistances(cas);
                }
        }

        private void findMinimalDistances(Case cas) {
                List<Case> adjacentNodes = getNeighbors(cas);
                for (Case target : adjacentNodes) {
                        if (getShortestDistance(target) > getShortestDistance(cas)
                                        + getDistance(cas, target)) {
                                time.put(target, getShortestDistance(cas)
                                                + getDistance(cas, target));
                                predecesseur.put(target, cas);
                                setCaseCousin.add(target);
                        }
                }

        }

        private int getDistance(Case cas, Case target) {
                for (Aretes aretes : listAretes) {
                        if (aretes.getSource().equals(cas)
                                        && aretes.getDestination().equals(target)) {
                                return aretes.getTime();
                        }
                }
                throw new RuntimeException("Should not happen");
        }

        private List<Case> getNeighbors(Case cas) {
                List<Case> neighbors = new ArrayList<Case>();
                for (Aretes aretes : listAretes) {
                        if (aretes.getSource().equals(cas)
                                        && !isSettled(aretes.getDestination())) {
                                neighbors.add(aretes.getDestination());
                        }
                }
                return neighbors;
        }

        private Case getMinimum(Set<Case> vertexes) {
                Case minimum = null;
                for (Case vertex : vertexes) {
                        if (minimum == null) {
                                minimum = vertex;
                        } else {
                                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                                        minimum = vertex;
                                }
                        }
                }
                return minimum;
        }

        private boolean isSettled(Case vertex) {
                return setCaseTraite.contains(vertex);
        }

        private int getShortestDistance(Case destination) {
                Integer d = time.get(destination);
                if (d == null) {
                        return Integer.MAX_VALUE;
                } else {
                        return d;
                }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public LinkedList<Case> getPath(Case target) {
                LinkedList<Case> path = new LinkedList<Case>();
                Case step = target;
                // check if a path exists
                if (predecesseur.get(step) == null) {
                        return null;
                }
                path.add(step);
                while (predecesseur.get(step) != null) {
                        step = predecesseur.get(step);
                        path.add(step);
                }
                // Put it into the correct order
                Collections.reverse(path);
                return path;
        }

}
