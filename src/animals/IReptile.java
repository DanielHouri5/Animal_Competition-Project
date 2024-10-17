/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package animals;
/**
 * Interface representing a reptile.
 * Defines constants and methods related to reptiles.
 */
public interface IReptile
{
    /**
     * Maximum speed constant for reptiles.
     */
    public static final int MAX_SPEED = 5;
    /**
     * Method to speed up the reptile.
     * @param speed The speed to increase.
     * @return True if the reptile successfully speeds up, false otherwise.
     */
    public boolean speedUp(int speed);
}
