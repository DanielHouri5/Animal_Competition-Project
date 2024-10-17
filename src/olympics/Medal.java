/**
 * Name: Daniel Houri
 * ID: 209445071
 */
package olympics;
/**
 * Represents a Medal object with type, tournament, and year attributes.
 */
public class Medal
{
    /**
     * Enum representing the type of the medal (bronze, silver, gold).
     */
    public enum Type {bronze, silver, gold}
    private Type type;
    private String tournament;
    private int year;
    /**
     * Default constructor initializes the medal as bronze, with empty tournament and year 2024.
     */
    public Medal()
    {
        this.type = Type.bronze;
        this.tournament = "";
        this.year = 2024;
    }
    /**
     * Parameterized constructor initializes the medal with given type, tournament, and year.
     * @param type The type of the medal (bronze, silver, gold)
     * @param tournament The name of the tournament where the medal was won
     * @param year The year the medal was won
     */
    public Medal(Type type, String tournament, int year)
    {
        this.type = type;
        this.tournament = tournament;
        if (year < 0)
            year = 2024;
        this.year = year;
    }
    /**
     * Copy constructor initializes the medal with attributes from another Medal object.
     * @param other The other Medal object to copy attributes from
     */
    public Medal(Medal other)
    {
        this.type = other.type;
        this.tournament = other.tournament;
        this.year = other.year;
    }
    /**
     * Checks if this Medal is equal to another object.
     * Two medals are considered equal if the object is a Medal with the same type, tournament, and year.
     * @param o The object to compare with
     * @return True if the object is equal to this Medal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Medal))
            return false;
        Medal other = (Medal) o;
        return this.type == other.type && this.tournament.equals(other.tournament) && this.year == other.year;
    }
    /**
     * Returns a string representation of the Medal object.
     * @return String representation including type, tournament, and year
     */
    @Override
    public String toString()
    {
        return "Type: " + this.type + ", Tournament: " + tournament + ", Year: " + year;
    }
}
