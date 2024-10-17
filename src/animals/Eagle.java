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
 * Represents an Eagle, which is a type of air animal.
 * Extends AirAnimal to inherit basic air animal attributes and behaviors.
 */
public class Eagle extends AirAnimal
{
    private double altitudeOfFlight;
    private static final double MAX_ALTITUDE = 1000;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Eagle()
    {
        super();
        this.altitudeOfFlight = 1;
    }
    /**
     * Parameterized constructor to initialize an eagle with specific attributes.
     * @param altitudeOfFlight Altitude at which the eagle can fly.
     * @param name Name of the eagle.
     * @param gender Gender of the eagle (Male, Female, Hermaphrodite).
     * @param weight Weight of the eagle.
     * @param speed Speed of the eagle.
     * @param medals Array of medals earned by the eagle.
     */
    public Eagle(double altitudeOfFlight, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan){
        super(10, new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/eagle1.png"
                ,null, null, null);
        if (altitudeOfFlight < 0)
            altitudeOfFlight = 1;
        this.altitudeOfFlight = altitudeOfFlight;
    }
    /**
     * Copy constructor to create a deep copy of another Eagle object.
     * @param other Another Eagle object to copy.
     */
    public Eagle(Eagle other)
    {
        super(other);
        this.altitudeOfFlight = other.altitudeOfFlight;
    }
    /**
     * Specifies the individual sound made by the eagle.
     * @return A String representing the sound made by the eagle.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Clack-wack-chack";
    }
    /**
     * Checks if this eagle is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Eagle))
            return false;
        Eagle other = (Eagle) o;
        return super.equals(other) && this.altitudeOfFlight == other.altitudeOfFlight;
    }
    /**
     * Generates a String representation of the eagle.
     * @return A String representation including all attributes of the eagle.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nAltitudeOfFlight: " + this.altitudeOfFlight;
    }
}
