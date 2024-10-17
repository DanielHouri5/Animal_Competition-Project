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
 * Represents a Cat, which is a type of terrestrial animal.
 * Extends TerrestrialAnimals to inherit basic terrestrial animal attributes and behaviors.
 */
public class Cat extends TerrestrialAnimals
{
    private boolean castrated;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Cat()
    {
        super();
        this.castrated = false;
    }
    /**
     * Parameterized constructor to initialize a cat with specific attributes.
     * @param castrated Whether the cat is castrated or not.
     * @param name Name of the cat.
     * @param gender Gender of the cat (Male, Female, Hermaphrodite).
     * @param weight Weight of the cat.
     * @param speed Speed of the cat.
     * @param medals Array of medals earned by the cat.
     */
    public Cat(boolean castrated, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
              , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan){
        super(4,  new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/cat1.png"
                , "src/graphics2/cat2.png"
                , "src/graphics2/cat3.png"
                , "src/graphics2/cat4.png");
        this.castrated = castrated;
    }
    /**
     * Copy constructor to create a deep copy of another Cat object.
     * @param other Another Cat object to copy.
     */
    public Cat(Cat other)
    {
        super(other);
        this.castrated = other.castrated;
    }
    /**
     * Specifies the individual sound made by the cat.
     * @return A String representing the sound made by the cat.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Meow";
    }
    /**
     * Checks if this cat is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Cat))
            return false;
        Cat other = (Cat) o;
        return super.equals(other) && this.castrated == other.castrated;
    }
    /**
     * Generates a String representation of the cat.
     * @return A String representation including all attributes of the cat.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nCastrated: " + this.castrated;
    }
}
