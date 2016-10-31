package robot.entities;

import robot.map.*;

/**
 * Une entité représente un élément présent sur une case, dans une carte.
 *
 * @see robot.map.Case
 * @see robot.map.Carte
 */
public interface Entity {


    /**
     * Retourne la position de l'entité.
     * La position d'une entité est la case sur laquelle elle se trouve.
     *
     * @return la position de l'entité
     * @see    robot.map.Case
     */
    public Case getPosition();

}
