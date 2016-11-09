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

        public Dijkstra(Graphe graph) {
                this.listCase = new ArrayList<Case>(graph.getListCase());
                this.listAretes = new ArrayList<Aretes>(graph.getListAretes());
        }

        public void traiterGraphe(Case source) {
                setCaseTraite = new HashSet<Case>();
                setCaseEnTraitant = new HashSet<Case>();
                time = new HashMap<Case, Integer>();
                predecesseur = new HashMap<Case, Case>();
                time.put(source, 0);
                setCaseEnTraitant.add(source);
                while (setCaseEnTraitant.size() > 0) {
                        Case cas = getMinimum(setCaseEnTraitant);
                        setCaseTraite.add(cas);
                        setCaseEnTraitant.remove(cas);
                        updateCaseEnTraitant(cas);
                }
        }

        private void updateCaseEnTraitant(Case cas) {
                List<Case> adjacentNodes = getCousin(cas);
                for (Case target : adjacentNodes) {
                        if (getShortestDistance(target) > getShortestDistance(cas)
                                        + getDistance(cas, target)) {
                                time.put(target, getShortestDistance(cas)
                                                + getDistance(cas, target));
                                predecesseur.put(target, cas);
                                setCaseEnTraitant.add(target);
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

        private List<Case> getCousin(Case cas) {
                List<Case> cousin = new ArrayList<Case>();
                for (Aretes aretes : listAretes) {
                        if (aretes.getSource().equals(cas)
                                        && !isTraite(aretes.getDestination())) {
                                cousin.add(aretes.getDestination());
                        }
                }
                return cousin;
        }

        private Case getMinimum(Set<Case> cases) {
                Case minimum = null;
                for (Case cas : cases) {
                        if (minimum == null) {
                                minimum = cas;
                        } else {
                                if (getShortestDistance(cas) < getShortestDistance(minimum)) {
                                        minimum = cas;
                                }
                        }
                }
                return minimum;
        }

        private boolean isTraite(Case cas) {
                return setCaseTraite.contains(cas);
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
        public List<Case> getPath(Case target) {
                ArrayList<Case> path = new ArrayList<Case>();
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
