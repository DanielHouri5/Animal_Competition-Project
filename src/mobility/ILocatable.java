/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package mobility;

/**
 * Interface representing a locatable object with location management capabilities.
 */
public interface ILocatable
{
    /**
     * Retrieves the current location of the object.
     * @return The current location as a Point object
     */
    public Point getLocation();
    /**
     * Sets the location of the object.
     * @param p The new location to set
     * @return True if the location was set successfully, false otherwise
     */
    public boolean setLocation(Point p);
}
