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
 * Abstract class representing terrestrial animals.
 * Extends Animal to inherit basic animal attributes and behaviors.
 */
public class TerrestrialAnimals extends Animal implements ITerrestrialAnimal
{
    private int noLegs;
    private CompetitionPanel pan;
    /**
     * Default constructor initializing basic attributes to default values.
     */
    public TerrestrialAnimals()
    {
        super();
        this.setLocation(new Point(0, 20));
        this.noLegs = 0;
    }
    /**
     * Parameterized constructor to initialize a terrestrial animal with specific attributes.
     * @param noLegs Number of legs of the terrestrial animal.
     * @param location Initial location of the animal.
     * @param name Name of the animal.
     * @param gender Gender of the animal (Male, Female, Hermaphrodite).
     * @param weight Weight of the animal.
     * @param speed Speed of the animal.
     * @param medals Array of medals earned by the animal.
     */
    public TerrestrialAnimals(int noLegs, Point location, String name, Animal.Gender gender, double weight, int speed, Medal[] medals
                             , int size, int energy, int maxEnergy, int energyPerMeter, CompetitionPanel pan
                             , String img1, String img2, String img3, String img4)    {
        super(location, name, gender, weight, speed, medals, size, energy, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        if (noLegs < 0)
            noLegs = 0;
        this.noLegs = noLegs;
        this.pan = pan;
    }
    /**
     * Copy constructor to create a deep copy of another TerrestrialAnimals object.
     * @param other Another TerrestrialAnimals object to copy.
     */
    public TerrestrialAnimals(TerrestrialAnimals other)
    {
        super(other);
        this.noLegs = other.noLegs;
    }
    /**
     * Abstract method to be implemented by subclasses.
     * Specifies the individual sound made by each type of terrestrial animal.
     * @return A String representing the sound made by the terrestrial animal.
     */
    protected String animalIndividualSound(){return "";}

    public int getNoLegs()
    {
        return this.noLegs;
    }

    public boolean move(Point location)
    {
        int x = getLocation().getX();
        int y = getLocation().getY();
        switch (getOrien()) {
            case EAST:
                if (x + getSpeed() + getSize()*2 >= pan.getBackgroundPanel().getWidth())
                {
                    x = pan.getBackgroundPanel().getWidth() - getSize();
                    setOrien(Orientation.SOUTH);
                }
                else
                    x += getSpeed();
                break;
            case SOUTH:
                if(y + getSpeed() + getSize()*2 >= pan.getBackgroundPanel().getHeight())
                {
                    y = pan.getBackgroundPanel().getHeight() - getSize();
                    setOrien(Orientation.WEST);
                }
                else
                    y += getSpeed();
                break;
            case WEST:
                if(x - getSpeed() <= 0)
                {
                    x = 0;
                    setOrien(Orientation.NORTH);
                }
                else
                    x -= getSpeed();
                break;
            case NORTH:
                if(y - getSpeed() <= 0)
                {
                    setLocation(new Point(0 , 0));
                    return false;
                }
                else
                    y -= getSpeed();
                break;
        }
        setLocation(new Point(x , y));
        return true;
    }

    ;
    /**
     * Checks if this terrestrial animal is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof TerrestrialAnimals))
            return false;
        TerrestrialAnimals other = (TerrestrialAnimals) o;
        return super.equals(other) && this.noLegs == other.noLegs;
    }
    /**
     * Generates a String representation of the terrestrial animal.
     * @return A String representation including all attributes of the terrestrial animal.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nNoLegs: " + this.noLegs;
    }

}
