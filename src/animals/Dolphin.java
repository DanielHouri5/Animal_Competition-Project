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
 * Represents a Dolphin, which is a type of water animal.
 * Extends WaterAnimal to inherit basic water animal attributes and behaviors.
 */
public class Dolphin extends WaterAnimal
{
    public enum WaterType{Sea, Sweet}
    private WaterType waterType;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Dolphin()
    {
        super();
        this.waterType = WaterType.Sea;
    }
    /**
     * Parameterized constructor to initialize a dolphin with specific attributes.
     * @param waterType Type of water the dolphin resides in (Sea or Sweet).
     * @param name Name of the dolphin.
     * @param gender Gender of the dolphin (Male, Female, Hermaphrodite).
     * @param weight Weight of the dolphin.
     * @param speed Speed of the dolphin.
     * @param medals Array of medals earned by the dolphin.
     */
    public Dolphin(WaterType waterType, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                  , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan)
    {
        super(50, new Point(0, 0), name, gender, weight, speed, medals, 65,energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/dolphin3.png", null, null, null);
        this.waterType = waterType;
    }
    /**
     * Copy constructor to create a deep copy of another Dolphin object.
     * @param other Another Dolphin object to copy.
     */
    public Dolphin(Dolphin other)
    {
        super(other);
        this.waterType = other.waterType;
    }
    /**
     * Specifies the individual sound made by the dolphin.
     * @return A String representing the sound made by the dolphin.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Click-click";
    }
    /**
     * Checks if this dolphin is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Dolphin))
            return false;
        Dolphin other = (Dolphin) o;
        return super.equals(other) && this.waterType == other.waterType;
    }
    /**
     * Generates a String representation of the dolphin.
     * @return A String representation including all attributes of the dolphin.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nWaterType: " + this.waterType;
    }
}
