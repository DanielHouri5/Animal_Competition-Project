/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package mobility;


import Graphics.IMoveable;

/**
 * Abstract class representing a mobile object with location and movement capabilities.
 */
public abstract class Mobile implements ILocatable, IMoveable
{
    private Point location;
    private double totalDistance;
    /**
     * Default constructor initializes the mobile object at the origin (0, 0) with an initial total distance of 1.
     */
    public Mobile()
    {
        this.location = new Point(0, 0);
        this.totalDistance = 0;
    }
    /**
     * Constructor initializes the mobile object at a specified location with an initial total distance of 1.
     * @param location The initial location of the mobile object
     */
    public Mobile(Point location)
    {
        this.location = new Point(location);
        this.totalDistance = 0;
    }
    /**
     * Copy constructor initializes a mobile object using another Mobile object.
     * @param other The Mobile object to copy from
     */
    public Mobile(Mobile other)
    {
        this.location = new Point(other.location);
        this.totalDistance = other.totalDistance;
    }
    /**
     * Checks if this Mobile object is equal to another object.
     * Two Mobile objects are considered equal if they have the same location and total distance.
     * @param o The object to compare with
     * @return True if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Mobile))
            return false;
        Mobile other = (Mobile) o;
        return this.location.equals(other.location) && this.totalDistance == other.totalDistance;
    }
    /**
     * Adds a given distance to the total distance traveled by the mobile object.
     * @param distance The distance to add
     */
    public void addTotalDistance(double distance)
    {
        this.totalDistance += distance;
    }
    /**
     * Calculates the Euclidean distance between the current location and a specified location.
     * @param location The target location
     * @return The distance between the current location and the target location
     */
    public double calcDistance(Point location)
    {
        return Math.sqrt(Math.pow(this.location.getX() - location.getX(), 2)+(Math.pow(this.location.getY() - location.getY(), 2)));
    }
    /**
     * Moves the mobile object to a new location, calculates the distance traveled,
     * updates the total distance, and sets the new location.
     * @param location The new location to move to
     * @return The distance traveled to the new location
     */
    public boolean move(Point location)
    {
        if (this.location.getX() + location.getX() > 800 || this.location.getY() + location.getY() > 600)
            return false;
        //double distance = calcDistance(location);
        addTotalDistance(calcDistance(location));
        this.setLocation(new Point(location.getX(), location.getY()));
        return true;
        //return distance;
    }
    public double getTotalDistance(){return this.totalDistance;}
    /**
     * Retrieves the current location of the mobile object.
     * @return The current location as a Point object
     */
    @Override
    public Point getLocation()
    {
        return this.location;
    }
    /**
     * Sets the location of the mobile object.
     * Validates that the new location coordinates are non-negative.
     * @param location The new location to set
     * @return True if the location was set successfully, false otherwise
     */
    @Override
    public boolean setLocation(Point location)
    {
        if (location.getX() < 0 || location.getY() < 0)
            return false;
        this.location.setX(location.getX());
        this.location.setY(location.getY());
        return true;
    }
    /**
     * Returns a string representation of the mobile object.
     * Includes its current location and total distance traveled.
     * @return String representation of the mobile object
     */
    @Override
    public String toString()
    {
        return "Location: " + this.location.toString() + "\nTotal distance: " + this.totalDistance;
    }
}
