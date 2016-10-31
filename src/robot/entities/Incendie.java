package robot.entities;

import robot.map.*;

/**
 * Un incendie est défini par sa position (sur une case d'un carte),
 * et par le nombre de litres d'eau nécessaires pour l'éteindre.
 *
 * @see robot.map.Carte
 * @see robot.map.Case
 */
public class Incendie implements Entity {

    /**
     * La position de l'incendie sur la carte.
     */
    private final Case position;

    /**
     * Le nombre de litres d'eau nécessaires à l'extinction de l'incendie.
     */
    private final int nbLitresEauPourExtinction;

    /**
     * Crée un nouvelle incendie, avec pour position la case <code>pos</code>
     * et nécessitant <code>nbLitres</code> litres d'eau pour son extinction.
     *
     * @param pos      la position de l'incendie.
     * @param nbLitres le nombre de litres d'eau nécessaires à son extinction.
     * @see            robot.map.Case
     */
    public Incendie(Case pos, int nbLitres) {
	position = pos;
	nbLitresEauPourExtinction = nbLitres;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Implémentation de la méthode  {@link robot.entities.Entity#getPosition()}
     *
     * @return La position de l'incendie.
     */
    @Override
    public Case getPosition() {
	return position;
    }

    /**
     * Le nombre de litre d'eau nécessaire à l'éxtinction de l'incendie.
     * La valeur retournée reflète une condition à l'état initial.
     *
     * @return le nombre de litres d'eau necéssaires.
     */
    public int getNbLitresEauPourExtinction() {
	return nbLitresEauPourExtinction;
    }

}
