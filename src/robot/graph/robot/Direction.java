package robot;

/**
 * Le type enum Direction, représente les différentes directions que peuvents
 * suivres les déplacement. La direction est utilisé pour calculer une nouvelle
 * position à partir d'une position existante.
 * <p>
 * Les différentes directions sont :
 * <ul>
 * <li> NORD </li>
 * <li> SUD </li>
 * <li> EST </li>
 * <li> OUEST </li>
 * </ul>
 *
 */
public enum Direction {

    /**
     * Direction NORD.
     * Corresponds à monter d'une ligne.
     */
    NORD  (-1, 0),

    /**
     * Direction SUD.
     * Corresponds à descendre d'une ligne.
     */
    SUD   (1 , 0),

    /**
     * Direction EST.
     * Corresponds à avancer d'une colonne.
     */
    EST   (0 , 1),

    /**
     * Direction OUEST.
     * Corresponds à reculer d'une colonne.
     */
    OUEST (0 ,-1);

    /*
       Ce type enum  contient les informations associer à un déplacement dans
       la direction correspondante, le déplacement en lignes et en colonnes.
     */

    /**
     * Différence de Ligne lors d'un déplacement dans la direction courante.
     */
    private final int dLigne;

    /**
     * Différence de Colonne lors d'un déplacement dans la direction courante.
     */
    private final int dColonne;

    /*
       Constructeur Privée du type enum, seul la "classe / enum" Direction peut
       l'utiliser.
       Les instances (NORD,SUD,EST,OUEST) de la "classe / enum" Direction sont
       static. Il est impossible de créer d'autre instances (non static).
    */

    /**
     * Crée une nouvelle Direction. Une direction est définit par un déplacement
     * selon les lignes et les colonnes.
     *
     * @param deltaLigne le nombre de ligne à ajouter lors d'un déplacement
     * @param deltaColone le nombre de colonnes à ajouter lors d'un déplacement
     */
    private Direction(int deltaLigne, int deltaColonne) {
	dLigne = deltaLigne;
	dColonne = deltaColonne;
    }

    /**
     * Accesseur de la différence de Ligne à appliquer lors d'un déplacement
     * dans cette direction.
     *
     * @return le déplacement suivant les lignes
     */
    public int getDeltaLigne() {
	return dLigne;
    }

    /**
     * Accesseur de la différence de Colonne à appliquer lors d'un déplacement
     * dans cette direction.
     *
     * @return le déplacement suivant les colonnes
     */
    public int getDeltaColonne() {
	return dColonne;
    }
}
