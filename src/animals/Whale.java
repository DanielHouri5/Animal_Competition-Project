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
 * Represents a Whale, which is a type of water animal.
 * Extends WaterAnimal to inherit basic water animal attributes and behaviors.
 */
public class Whale extends WaterAnimal
{
    private String foodType;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Whale()
    {
        super();
        this.foodType = "Unknown";
    }
    /**
     * Parameterized constructor to initialize a whale with specific attributes.
     * @param foodType Type of food the whale consumes.
     * @param name Name of the whale.
     * @param gender Gender of the whale (Male, Female, Hermaphrodite).
     * @param weight Weight of the whale.
     * @param speed Speed of the whale.
     * @param medals Array of medals earned by the whale.
     */
    public Whale(String foodType, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan){
        super(100, new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/whale.png", null, null, null);
        this.foodType = foodType;
    }
    /**
     * Copy constructor to create a deep copy of another Whale object.
     * @param other Another Whale object to copy.
     */
    public Whale(Whale other)
    {
        super(other);
        this.foodType = other.foodType;
    }
    /**
     * Specifies the individual sound made by the whale.
     * @return A String representing the sound made by the whale.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Splash";
    }
    /**
     * Checks if this whale is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Whale))
            return false;
        Whale other = (Whale) o;
        return super.equals(other) && this.foodType.equals(other.foodType);
    }
    /**
     * Generates a String representation of the whale.
     * @return A String representation including all attributes of the whale.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nFoodType: " + this.foodType;
    }
}
