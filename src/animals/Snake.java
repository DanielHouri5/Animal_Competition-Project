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
 * The Snake class represents a snake, which is a type of terrestrial animal
 * and also implements reptilian characteristics as defined by the IReptile interface.
 */
public class Snake extends TerrestrialAnimals implements IReptile
{
    /**
     * Enum defining the poisonous levels of the snake.
     */
    public enum Poisonous{low, medium, high}
    private Poisonous poisonLevel;
    private double length;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Snake()
    {
        super();
        this.poisonLevel = Poisonous.medium;
        this.length = 1;
    }
    /**
     * Parameterized constructor to initialize a snake with specific attributes.
     * @param poisonLevel Poisonous level of the snake.
     * @param length Length of the snake.
     * @param name Name of the snake.
     * @param gender Gender of the snake (Male, Female, Hermaphrodite).
     * @param weight Weight of the snake.
     * @param speed Speed of the snake.
     * @param medals Array of medals earned by the snake.
     */
    public Snake(Poisonous poisonLevel, double length, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan){
        super(0,  new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/snake1.png"
                , "src/graphics2/snake2.png"
                , "src/graphics2/snake3.png"
                , "src/graphics2/snake4.png");
        this.poisonLevel = poisonLevel;
        if (length < 0)
            length = 1;
        this.length = length;
    }
    /**
     * Copy constructor to create a deep copy of another Snake object.
     * @param other Another Snake object to copy.
     */
    public Snake(Snake other)
    {
        super(other);
        this.poisonLevel = other.poisonLevel;
        this.length = other.length;    }
    /**
     * Specifies the individual sound made by the snake.
     * @return A String representing the sound made by the snake.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "ssssssss";
    }
    /**
     * Increases the speed of the snake by the specified amount.
     *
     * @param speed the amount to increase the speed by; must be non-negative
     * @return {@code true} if the speed was successfully increased;
     *         {@code false} if the resulting speed would exceed the maximum allowed speed
     */
    public boolean speedUp(int speed)
    {
        if (this.getSpeed() + speed > MAX_SPEED)
            return false;
        this.setSpeed(this.getSpeed() + speed);
        return true;
    }
    /**
     * Checks if this snake is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Snake))
            return false;
        Snake other = (Snake) o;
        return super.equals(other) && this.poisonLevel == other.poisonLevel && this.length == other.length;
    }
    /**
     * Generates a String representation of the snake.
     * @return A String representation including all attributes of the snake.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nPoisonLevel: " + this.poisonLevel + "\nLength: " + this.length;
    }
}
