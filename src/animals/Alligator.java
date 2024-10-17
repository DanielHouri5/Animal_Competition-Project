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
 * The Alligator class represents an alligator, which is a type of water animal
 * and also implements reptilian characteristics as defined by the IReptile interface.
 */
public class Alligator extends Animal implements IReptile, IWaterAnimal, ITerrestrialAnimal
{
    private String areaOfLiving;
    private IWaterAnimal waterAnimal;
    private ITerrestrialAnimal terrestrialAnimal;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Alligator()
    {
        super();
        this.areaOfLiving = "Unknown";
        waterAnimal = new WaterAnimal();
        terrestrialAnimal = new TerrestrialAnimals();
    }
    /**
     * Parameterized constructor to initialize an alligator with specific attributes.
     * @param areaOfLiving Area where the alligator lives.
     * @param name Name of the alligator.
     * @param gender Gender of the alligator (Male, Female, Hermaphrodite).
     * @param weight Weight of the alligator.
     * @param speed Speed of the alligator.
     * @param medals Array of medals earned by the alligator.
     */
    public Alligator(String areaOfLiving, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                    , int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan)
    {
        super( new Point(0, 0), name, gender, weight, speed, medals, 65, energy, maxEnergy, energyPerMeter, pan
                , "src/graphics2/alligator1.png"
                , "src/graphics2/alligator2.png"
                , "src/graphics2/alligator3.png"
                , "src/graphics2/alligator4.png");
        this.areaOfLiving = areaOfLiving;
        waterAnimal = new WaterAnimal(10,  new Point(0, 0), name, gender, weight, speed, medals, 65,energy, maxEnergy, energyPerMeter, null, "C:\\Users\\hdani\\IdeaProjects\\Task2\\src\\graphics2\\alligator1.png", null, null, null);
        terrestrialAnimal = new TerrestrialAnimals(4,  new Point(0, 0), name, gender, weight, speed, medals, 65,energy, maxEnergy, energyPerMeter, null, "C:\\Users\\hdani\\IdeaProjects\\Task2\\src\\graphics2\\alligator1.png", null, null, null);
        setCount(getCount()-2);
    }
    /**
     * Specifies the individual sound made by the alligator.
     * @return A String representing the sound made by the alligator.
     */
    @Override
    protected String animalIndividualSound()
    {
        return "Roar";
    }

    /**
     * Increases the speed of the alligator by the specified amount.
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

    public boolean Dive(double dive)
    {
        return this.waterAnimal.Dive(dive);
    }

    @Override
    public int getNoLegs() {
        return terrestrialAnimal.getNoLegs();
    }

    /**
     * Checks if this alligator is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Alligator))
            return false;
        Alligator other = (Alligator) o;
        return super.equals(other) && this.areaOfLiving.equals(other.areaOfLiving);
    }
    /**
     * Generates a String representation of the alligator.
     * @return A String representation including all attributes of the alligator.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nAreaOfLiving: " + this.areaOfLiving;
    }

}
