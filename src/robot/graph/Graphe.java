package robot.graph;

import robot.*;
import robot.entities.*;
import robot.map.*;
import java.util.*;

public class Graphe {

    private final List<Case> listCase;
    private final List<Aretes> listAretes;
    private final Carte carte;
    private final Robot robot;

    /**
     * Construit un graphe à partir d'une carte et d'un robot. Le robot determine
     * les movements possible.
     *
     */
    public Graphe(Robot robot, Carte carte) {
        this.carte = carte;
        this.robot = robot;
        listCase = new ArrayList<Case>();
        listAretes = new ArrayList<Aretes>();
        for (int i = 0; i < carte.getNbLigne(); i++) {
            for (int j = 0; j < carte.getNbColonne(); j++) {
                listCase.add(carte.getCaseAt(i, j));
            }
        }

        for (Case cas : listCase) {
            for (Direction dir : Direction.values()) {
                if (carte.voisinExiste(cas, dir)) {
                    Case temps = carte.getCaseAt(cas.getPosition().deplace(dir));
                    double vitesse = (robot.getVitesse(cas) + robot.getVitesse(temps)) / 2;
                    if (vitesse != 0) {
                        int time = (int) ((60.0 * 10) / (vitesse));
                        listAretes.add(new Aretes(cas, temps, time));
                    } else {
                        listAretes.add(new Aretes(cas, temps, Integer.MAX_VALUE));
                    }
                }
            }
        }

        List<Case> willRemoveCase = new ArrayList<Case>();
        for (Case cas : listCase) {
            if (!robot.peutDeplacerSur(cas)) {
                removeAretes(cas);
                willRemoveCase.add(cas);
            }
        }
        for (Case cas : willRemoveCase) {
            listCase.remove(cas);
        }
    }

    /**
     * Retourne les sommet et les aretes du graphe
     *
     * @return String de l'information de la graphe
     */
    @Override
    public String toString() {
        String s = new String("Graph with sommet : \n");
        for (Case cas : listCase) {
            s += (cas.toString() + "\n");
        }
        s += "And the aretes :\n";
        for (Aretes aretes : listAretes) {
            s += (aretes.toString() + "\n");
        }
        return s;
    }

    /**
     * Supprimer des Arete d'une case dans le graphe
     *
     * @param Case la case qui est le sommet des aretes
     */
    private void removeAretes(Case cas) {
        for (Aretes aretes : AretesDe(cas)) {
            listAretes.remove(aretes);
        }
        for (Aretes aretes : AretesA(cas)) {
            listAretes.remove(aretes);
        }
    }

    /**
     * Retourne la liste des aretes qui sortent d'une case du graphe
     *
     * @param cas la case source
     * @return La liste des Aretes
     *
     */
    public List<Aretes> AretesDe(Case cas) {
        List<Aretes> aretesDe = new ArrayList<Aretes>();
        for (Aretes aretes : listAretes) {
            if (aretes.getSource() == cas) {
                aretesDe.add(aretes);
            }
        }
        return aretesDe;

    }

    /**
     * Retourne la liste des aretes qui se dirige vers une case du graphe
     *
     * @param cas la case qui est la destination des aretes
     * @return La liste des Aretes
     *
     */
    public List<Aretes> AretesA(Case cas) {
        List<Aretes> aretesA = new ArrayList<Aretes>();
        for (Aretes aretes : listAretes) {
            if (aretes.getDestination() == cas) {
                aretesA.add(aretes);
            }
        }
        return aretesA;
    }

    /**
     * Retourne la liste des Cases du graphe
     *
     * @return La liste des Cases
     */
    public List<Case> getListCase() {
        return listCase;
    }

    /**
     * Retourne la liste des Aretes du graphe
     *
     * @return La liste des Aretes
     */
    public List<Aretes> getListAretes() {
        return listAretes;
    }

}
