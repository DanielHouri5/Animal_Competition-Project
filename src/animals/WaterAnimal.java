/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package animals;
import Graphics.CompetitionPanel;
import mobility.Point;
import olympics.Medal;

import java.awt.*;

/**
 * Abstract class representing water animals.
 * Extends Animal to inherit basic animal attributes and behaviors.
 */
public class WaterAnimal extends Animal implements IWaterAnimal
{
    private static final double MAX_DIVE = -800;
    private double diveDept;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public WaterAnimal()
    {
        super();
        this.setLocation(new Point(50, 0));
        this.diveDept = 0;
    }
    /**
     * Parameterized constructor to initialize a water animal with specific attributes.
     * @param diveDept Depth to which the animal can dive.
     * @param location Initial location of the animal.
     * @param name Name of the animal.
     * @param gender Gender of the animal (Male, Female, Hermaphrodite).
     * @param weight Weight of the animal.
     * @param speed Speed of the animal.
     * @param medals Array of medals earned by the animal.
     */
    public WaterAnimal(double diveDept, Point location, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                      , int size, int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan
                      , String img1, String img2, String img3, String img4){
        super(location, name, gender, weight, speed, medals, size, energy, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        if (diveDept > 0)
            diveDept = 0;
        this.diveDept = diveDept;
    }
    /**
     * Copy constructor to create a deep copy of another WaterAnimal object.
     * @param other Another WaterAnimal object to copy.
     */
    public WaterAnimal(WaterAnimal other)
    {
        super(other);
        this.diveDept = other.diveDept;
    }
    /**
     * Allows the water animal to dive to a specified depth.
     * @param dive Depth to dive (negative value).
     * @return True if the dive was successful, false otherwise (if maximum depth is exceeded).
     */
    public boolean Dive(double dive)
    {
        if (diveDept - dive < MAX_DIVE)
            return false;
        this.diveDept -= dive;
        return true;
    }
    /**
     * Abstract method to be implemented by subclasses.
     * Specifies the individual sound made by each type of water animal.
     * @return A String representing the sound made by the water animal.
     */
    protected String animalIndividualSound(){return "";};

    /**
     * Checks if this water animal is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof WaterAnimal))
            return false;
        WaterAnimal other = (WaterAnimal) o;
        return super.equals(other) && this.diveDept == other.diveDept;
    }
    /**
     * Generates a String representation of the water animal.
     * @return A String representation including all attributes of the water animal.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nDiveDept: " + this.diveDept;
    }
}
