package Competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of scores for the competition.
 * Each score is associated with a name and a timestamp indicating when the score was recorded.
 */
public class Scores
{
    // A synchronized map to store scores with names as keys and timestamps as values
    private final Map<String, Date> scores;

    /**
     * Constructs a Scores object and initializes the scores map.
     * The map is synchronized to handle concurrent access.
     */
    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Adds a new score with the specified name. The current date and time are recorded as the score.
     *
     * @param name The name associated with the score. It could be a participant's name, a group name, etc.
     */
    public void add(String name)
    {
        scores.put(name, new Date());
    }

    /**
     * Returns a synchronized map containing all the scores.
     *
     * @return A map where keys are names and values are the timestamps of when the scores were recorded.
     */
    public Map<String, Date> getAll()
    {
        return scores;
    }
}
