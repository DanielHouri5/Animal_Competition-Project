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
 * Abstract class representing an air animal.
 * Extends Animal to inherit basic animal attributes and behaviors.
 */
public class AirAnimal extends Animal
{
    private double wingspan;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public AirAnimal()
    {
        super();
        this.setLocation(new Point(0, 100));
        this.wingspan = 0;
    }
    /**
     * Parameterized constructor to initialize an air animal with specific attributes.
     * @param wingspan Wingspan of the air animal.
     * @param location Initial location of the animal.
     * @param name Name of the animal.
     * @param gender Gender of the animal (Male, Female, Hermaphrodite).
     * @param weight Weight of the animal.
     * @param speed Speed of the animal.
     * @param medals Array of medals earned by the animal.
     */
    public AirAnimal(double wingspan, Point location, String name, Gender gender, double weight, int speed, Medal[] medals
                    , int size, int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan
                    ,String img1, String img2, String img3, String img4)    {
        super(location, name, gender, weight, speed, medals, size, energy, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        if (wingspan < 0)
            wingspan = 0;
        this.wingspan = wingspan;
    }
    /**
     * Copy constructor to create a deep copy of another AirAnimal object.
     * @param other Another AirAnimal object to copy.
     */
    public AirAnimal(AirAnimal other)
    {
        super(other);
        this.wingspan = other.wingspan;
    }
    /**
     * Abstract method to be implemented by subclasses.
     * Specifies the individual sound made by each type of air animal.
     * @return A String representing the sound made by the air animal.
     */
    protected String animalIndividualSound(){return "";};
    /**
     * Checks if this air animal is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof AirAnimal))
            return false;
        AirAnimal other = (AirAnimal) o;
        return super.equals(other) && this.wingspan == other.wingspan;
    }
    /**
     * Generates a String representation of the air animal.
     * @return A String representation including all attributes of the air animal.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nWingspan: " + this.wingspan;
    }
}
