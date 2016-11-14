package robot.graph;

import robot.map.*;
import robot.*;
import java.util.*;
public class TestGraph{

    public static void main(String agrs[]) {
        Case[][] map = new Case[4][4];
        for (int j = 0; j < 4; j++) {
            map[0][j] = new Case(0, j, NatureTerrain.FORET);
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Case(i, j, NatureTerrain.ROCHE);
            }
        }
        Carte carte = new Carte(1, 4, 4);
        carte.setCarte(map);
        Graphe graph = new Graphe(carte);
        System.out.println(graph);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.traiterGraphe(carte.getCaseAt(new Position(1, 2)));
        List<Case> chemin = dijkstra.getPath(carte.getCaseAt(new Position(3, 3)));
        for (Case cas : chemin) {
            System.out.println(cas);
        }
    }

}
