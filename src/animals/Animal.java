/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package animals;

import Graphics.IAnimal;
import Graphics.IDrawable;
import Graphics.IMoveable;
import Graphics.CompetitionPanel;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
/**
 * Abstract base class representing an animal in a virtual ecosystem.
 * Extends Mobile to provide mobility features.
 */
public abstract class Animal extends Mobile implements IAnimal, ILocatable, IDrawable, Cloneable {
    private String name;
    public enum Gender{Male, Female, Hermaphrodite}
    private Gender gender;
    private double weight;
    private int speed;
    private Medal[] medals;
    private int size;
    private static int count = 0;
    private int id;
    public enum Orientation{EAST, SOUTH, WEST, NORTH}
    private Orientation orien;
    private int energy;
    private int maxEnergy;
    private int energyPerMeter;
    private CompetitionPanel pan;
    private BufferedImage img1 = null, img2 = null, img3 = null, img4 = null;
    private IMoveable iMove;
    private IDrawable iDraw;
    private Cloneable iClon;

    /**
     * Default constructor initializing basic attributes to default values.
     */
    public Animal()
    {
        super();
        this.name = "Unknown";
        this.gender = Gender.Male;
        this.weight = 10;
        this.speed = 10;
        this.medals = null;
        this.size = 0;
        this.id = 0;
        this.orien = Orientation.EAST;
        this.energy = 0;
        this.maxEnergy = 0;
        this.energyPerMeter = 0;
        this.pan = null;
        this.img1 = null;
        this.img2 = null;
        this.img3 = null;
        this.img4 = null;

    }

    /**
     * Parameterized constructor to initialize an animal with specific attributes.
     * @param location Initial location of the animal.
     * @param name Name of the animal.
     * @param gender Gender of the animal (Male, Female, Hermaphrodite).
     * @param weight Weight of the animal.
     * @param speed Speed of the animal.
     * @param medals Array of medals earned by the animal.
     * @param size Size of the animal.
     * @param maxEnergy Maximum energy capacity of the animal.
     * @param energy Initial energy level of the animal.
     * @param energyPerMeter Energy consumption per meter.
     * @param pan The competition panel where the animal is displayed.
     * @param img1 Path to the image representing the animal facing EAST.
     * @param img2 Path to the image representing the animal facing SOUTH.
     * @param img3 Path to the image representing the animal facing WEST.
     * @param img4 Path to the image representing the animal facing NORTH.
     */
    public Animal(Point location, String name, Gender gender, double weight, int speed, Medal[] medals
                , int size, int maxEnergy, int energy, int energyPerMeter, CompetitionPanel pan
                , String img1, String img2, String img3, String img4)
    {
        super(location);
        this.name = name;
        this.gender = gender;
        if (weight < 0)
            weight = 10;
        this.weight = weight;
        if (speed < 0)
            speed = 10;
        this.speed = speed;
        if (medals != null) {
            this.medals = new Medal[medals.length];
            System.arraycopy(medals, 0, this.medals, 0, medals.length);
        }
        else
            this.medals = null;
        this.size = size;
        this.id = count++;
        this.orien = Orientation.EAST;
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.pan = pan;
        loadImages(img1);
        if (img2 != null) {
            loadImages(img2);
            loadImages(img3);
            loadImages(img4);
        }
        else {
            this.img2 = null;
            this.img3 = null;
            this.img4 = null;
        }
    }

    /**
     * Copy constructor to create a deep copy of another Animal object.
     * @param other Another Animal object to copy.
     */
    public Animal(Animal other)
    {
        super(other);
        this.name = other.name;
        this.gender = other.gender;
        this.weight = other.weight;
        this.speed = other.speed;
        this.medals = new Medal[other.medals.length];
        System.arraycopy(other.medals, 0, this.medals, 0, medals.length);
        this.size = other.size;
        this.id = other.id;
        this.orien = other.orien;
        this.maxEnergy = other.maxEnergy;
        this.energyPerMeter = other.energyPerMeter;
        this.pan = other.pan;
        this.img1 = other.img1;
        this.img2 = other.img2;
        this.img3 = other.img3;
        this.img4 = other.img4;
    }

    public static void setCount(int newCount){count = newCount;}
    public static int getCount(){return count;}
    @Override
    public String getAnimaleName() {
        return null;
    }

    /**
     * Retrieves the speed of the object.
     *
     * @return the current speed of the object
     */
    public int getSpeed(){return this.speed;}

    /**
     * Sets the speed of the object.
     *
     * @param speed the new speed to set; must be non-negative
     * @return {@code true} if the speed was successfully set;
     *         {@code false} if the provided speed is negative
     */
    public boolean setSpeed(int speed)
    {
        if (speed < 0)
            return false;
        this.speed = speed;
        return true;
    }

    /**
     * Retrieves the size of the animal.
     * @return the size of the animal.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Retrieves the unique ID of the animal.
     * @return the ID of the animal.
     */
    public int getId(){return this.id;}

    /**
     * Retrieves the name of the animal.
     * @return the name of the animal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the current energy level of the animal.
     * @return the current energy level.
     */
    public int getEnergy()
    {
        synchronized (this) {
            return energy;
        }
    }

    /**
     * Retrieves the energy consumption per meter.
     * @return the energy consumption per meter.
     */
    public int getEnergyPerMeter(){return this.energyPerMeter;}

    /**
     * Abstract method to be implemented by subclasses.
     * Specifies the individual sound made by each type of animal.
     * @return A String representing the sound made by the animal.
     */
    protected abstract String animalIndividualSound();

    /**
     * Method to make the animal produce its individual sound.
     */
    public void makeSound()
    {
        System.out.println("Animal <" + this.getClass().getSimpleName() + "> said <" + animalIndividualSound() +">");
    }

    /**
     * Decreases the energy level of the animal.
     * @param energy the amount of energy to decrease.
     */
    public void decreaseEnergy(int energy)
    {
        synchronized (this) {
            this.energy -= energy;
            if (this.energy <= 0) {
                this.energy = 0;
                this.notify(); // Notify any waiting threads
            }
        }
    }

    /**
     * Increases the energy level of the animal by the specified amount.
     * If the new energy level exceeds the maximum capacity, it will be capped at the maximum energy.
     *
     * @param energy The amount of energy to add. Must be non-negative.
     * @return {@code true} if the energy was successfully added and the animal's energy was updated;
     *         {@code false} if the energy is already at maximum capacity and cannot be increased.
     */
    @Override
    public boolean eat(int energy)
    {
        synchronized (this) {
            if (this.energy == maxEnergy)
                return false;
            int newEnergy = this.energy + energy;
            if (newEnergy > this.maxEnergy)
                this.energy = this.maxEnergy;
            else
                this.energy = newEnergy;
            this.notifyAll();
            return true;
        }
    }

    /**
     * Loads an image from the specified file path.
     * @param nm The path to the image file.
     */
    @Override
    public void loadImages(String nm) {
        try {
            BufferedImage img = ImageIO.read(new File(nm));
            if (this.img1 == null)
                this.img1 = img;
            else if (this.img2 == null)
                this.img2 = img;
            else if (this.img3 == null)
                this.img3 = img;
            else if (this.img4 == null)
                this.img4 = img;
            System.out.println("Image loaded successfully: " + nm);
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
            e.printStackTrace();
        }
    }

    /**
     * Sets the size of the animal.
     * @param size the new size to set; must be positive.
     * @return {@code true} if the size was successfully set;
     *         {@code false} if the provided size is non-positive.
     */
    public boolean setSize(int size)
    {
        if (size <= 0)
            return false;
        this.size = size;
        return true;
    }

    /**
     * Moves the animal to a new location.
     * @param location the new location to move to.
     * @return {@code true} if the move was successful;
     *         {@code false} if the move would go beyond the panel's bounds.
     */
    public boolean move(Point location)
    {
        if (location.getX() + this.size * 2 >= pan.getWidth())
        {
            this.setLocation(new Point(pan.getWidth() - this.size * 2, location.getY()));
            return false;
        }
        this.setLocation(new Point(location.getX() + this.speed, location.getY()));
        return true;
    }

    /**
     * Sets the orientation of the animal.
     * @param orien the new orientation of the animal.
     */
    public void setOrien(Orientation orien)
    {
        this.orien = orien;
    }

    /**
     * Retrieves the current orientation of the animal.
     * @return the current orientation of the animal.
     */
    public Orientation getOrien()
    {
        return this.orien;
    }

    /**
     * Draws the animal on the given graphics context based on its orientation.
     * @param g the graphics context to draw on.
     */
    @Override
    public void drawObject(Graphics g)
    {
        if(this.orien==Orientation.EAST) // animal move to the east side
            g.drawImage(this.img1, getLocation().getX(), getLocation().getY(), size*2, size, pan.getBackgroundPanel());
        else if(this.orien==Orientation.SOUTH) // animal move to the south side
            g.drawImage(this.img2, getLocation().getX(), getLocation().getY(), size, size*2, pan.getBackgroundPanel());
        else if(this.orien==Orientation.WEST) // animal move to the west side
            g.drawImage(this.img3, getLocation().getX(), getLocation().getY(), size*2, size, pan.getBackgroundPanel());
        else if(this.orien==Orientation.NORTH) // animal move to the north side
            g.drawImage(this.img4, getLocation().getX(), getLocation().getY(), size, size*2, pan.getBackgroundPanel());

    }

    /**
     * Checks if this animal is equal to another object.
     * @param o Object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Animal))
            return false;
        Animal other = (Animal) o;
        return super.equals(other) && this.name.equals(other.name)
                && this.gender == other.gender && this.weight == other.weight
                && this.speed == other.speed && Arrays.equals(this.medals, other.medals);
    }

    /**
     * Generates a String representation of the animal.
     * @return A String representation including all attributes of the animal.
     */
    @Override
    public String toString()
    {
        String str = super.toString() + "\nName: " + this.name + "\nGender: " + this.gender
                + "\nWeight: " + this.weight + "\nSpeed: " + speed + "\nMedals: ";
        if (medals != null) {
            str += this.medals[0].toString();
            for (int i = 1; i < this.medals.length; i++)
                str += "\n        " + this.medals[i].toString();
        }
        else
            str += "\n";
        return str;
    }

}
