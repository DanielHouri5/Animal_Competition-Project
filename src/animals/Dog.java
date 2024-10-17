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
 * Represents a Dog, which is a type of terrestrial animal.
 * Extends TerrestrialAnimals to inherit basic terrestrial animal attributes and behaviors.
 */
public class Dog extends TerrestrialAnimals
{
    private String breed;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Dog()
    {
        super();
        breed = "Unknown";
    }
    /**
     * Parameterized constructor to initialize a dog with specific attributes.
     * @param breed Breed of the dog.
     * @param name Name of the dog.
     * @param gender Gender of the dog (Male, Female, Hermaphrodite).
     * @param weight Weight of the dog.
     * @param speed Speed of the dog.
     * @param medals Array of medals earned by the dog.
     */
    public Dog(String breed, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
              , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan){
        super(4, new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/dog1.png"
                , "src/graphics2/dog2.png"
                , "src/graphics2/dog3.png"
                , "src/graphics2/dog4.png");
        this.breed = breed;
    }
    /**
     * Copy constructor to create a deep copy of another Dog object.
     * @param other Another Dog object to copy.
     */
    public Dog(Dog other)
    {
        super(other);
        this.breed = other.breed;
    }
    /**
     * Specifies the individual sound made by the dog.
     * @return A String representing the sound made by the dog.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Woof Woof";
    }
    /**
     * Checks if this dog is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Dog))
            return false;
        Dog other = (Dog) o;
        return super.equals(other) && this.breed.equals(other.breed);
    }
    /**
     * Generates a String representation of the dog.
     * @return A String representation including all attributes of the dog.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nBreed: " + this.breed;
    }
}
