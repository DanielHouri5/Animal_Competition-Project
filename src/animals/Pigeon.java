/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package animals;

import Graphics.CompetitionPanel;
import mobility.Point;
import olympics.Medal;

import java.awt.image.BufferedImage;

/**
 * Represents a Pigeon, which is a type of air animal.
 * Extends AirAnimal to inherit basic air animal attributes and behaviors.
 */
public class Pigeon extends AirAnimal
{
    private String family;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Pigeon()
    {
        super();
        this.family = "Unknown";
    }
    /**
     * Parameterized constructor to initialize a pigeon with specific attributes.
     * @param family Family of the pigeon.
     * @param name Name of the pigeon.
     * @param gender Gender of the pigeon (Male, Female, Hermaphrodite).
     * @param weight Weight of the pigeon.
     * @param speed Speed of the pigeon.
     * @param medals Array of medals earned by the pigeon.
     */
    public Pigeon(String family, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                 , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan){
        super(7, new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/pigeon.png"
                ,null, null, null);
        this.family = family;
    }
    /**
     * Copy constructor to create a deep copy of another Pigeon object.
     * @param other Another Pigeon object to copy.
     */
    public Pigeon(Pigeon other)
    {
        super(other);
        this.family = other.family;
    }
    /**
     * Specifies the individual sound made by the pigeon.
     * @return A String representing the sound made by the pigeon.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Arr-rar-rar-rar-raah";
    }
    /**
     * Checks if this pigeon is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Pigeon))
            return false;
        Pigeon other = (Pigeon) o;
        return super.equals(other) && this.family.equals(other.family);
    }
    /**
     * Generates a String representation of the pigeon.
     * @return A String representation including all attributes of the pigeon.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nFamily: " + this.family;
    }

}
