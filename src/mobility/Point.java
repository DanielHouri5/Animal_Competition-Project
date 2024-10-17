/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package mobility;
/**
 * Represents a point with integer coordinates (x, y).
 */
public class Point
{
    private int m_x;
    private int m_y;
    /**
     * Default constructor initializes the point at coordinates (0, 0).
     */
    public Point()
    {
        m_x = 0;
        m_y = 0;
    }
    /**
     * Parameterized constructor initializes the point at specified coordinates (x, y).
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     */
    public Point(int x, int y)
    {
        if (x < 0)
            x = 0;
        m_x = x;
        if (y < 0)
            y = 0;
        m_y = y;
    }
    /**
     * Copy constructor initializes a point using another Point object.
     * @param other The Point object to copy coordinates from
     */
    public Point(Point other)
    {
        m_x = other.m_x;
        m_y = other.m_y;
    }
    /**
     * Checks if this Point is equal to another object.
     * Two points are considered equal if they have the same x and y coordinates.
     * @param o The object to compare with
     * @return True if the object is equal to this Point, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Point))
            return false;
        Point other = (Point) o;
        return m_x == other.m_x && m_y == other.m_y;
    }
    /**
     * Retrieves the x-coordinate of the point.
     * @return The x-coordinate of the point
     */
    public int getX()
    {
        return m_x;
    }
    /**
     * Retrieves the y-coordinate of the point.
     * @return The y-coordinate of the point
     */
    public int getY()
    {
        return m_y;
    }
    /**
     * Sets the x-coordinate of the point.
     * @param x The new x-coordinate to set
     * @return True if the x-coordinate was set successfully (non-negative), false otherwise
     */
    protected boolean setX(int x)
    {
        if (x < 0)
            return false;
        m_x = x;
        return true;
    }
    /**
     * Sets the y-coordinate of the point.
     * @param y The new y-coordinate to set
     * @return True if the y-coordinate was set successfully (non-negative), false otherwise
     */
    protected boolean setY(int y)
    {
        if (y < 0)
            return false;
        m_y = y;
        return true;
    }
    /**
     * Returns a string representation of the point in the format "(x, y)".
     * @return String representation of the point
     */
    @Override
    public String toString()
    {
        return "(" + m_x + ", " + m_y + ")";
    }
}
