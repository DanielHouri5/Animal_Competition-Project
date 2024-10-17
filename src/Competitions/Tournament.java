package Competitions;

import Graphics.BackgroundPanel;
import Graphics.CompetitionPanel;
import animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Represents a generic tournament involving animals.
 * This abstract class provides the basic setup and management for a tournament.
 * It includes details about the tournament, background panel, selected category,
 * and a list of referees.
 */
public abstract class Tournament
{
    protected TournamentThread tournamentThread;
    private BackgroundPanel backgroundPanel;
    private String selectedCategory;
    private Vector<String>groupsNames;
    private String tournamentName;
    private List<Referee> refreesList = new ArrayList<>();
    private CompetitionPanel competitionPanel;

    /**
     * Constructs a Tournament with the specified parameters.
     *
     * @param animals The 2D array of animals participating in the tournament.
     * @param groupsNames A vector containing the names of the groups participating in the tournament.
     * @param tournamentName The name of the tournament.
     * @param backgroundPanel The background panel for graphical display.
     * @param selectedCategory The selected category for the tournament.
     * @param competitionPanel The panel where competition results are displayed.
     */
    public Tournament(Animal[][] animals, Vector<String> groupsNames, String tournamentName, BackgroundPanel backgroundPanel, String selectedCategory, CompetitionPanel competitionPanel)
    {
        this.backgroundPanel = backgroundPanel;
        this.selectedCategory = selectedCategory;
        this.groupsNames = groupsNames;
        this.tournamentName = tournamentName;
        this.competitionPanel = competitionPanel;
        setUp(animals, groupsNames, tournamentName, refreesList, competitionPanel);
    }

    /**
     * Sets up the tournament with the given parameters.
     * This method should be implemented by subclasses to initialize the tournament
     * based on specific requirements.
     *
     * @param animals The 2D array of animals participating in the tournament.
     * @param groupsNames A vector containing the names of the groups participating in the tournament.
     * @param tournamentName The name of the tournament.
     * @param refreesList The list of referees for the tournament.
     * @param competitionPanel The panel where competition results are displayed.
     */
    public abstract void setUp(Animal[][] animals, Vector<String>groupsNames, String tournamentName, List<Referee> refreesList, CompetitionPanel competitionPanel);

    /**
     * Gets the TournamentThread associated with this tournament.
     *
     * @return The TournamentThread object managing the tournament.
     */
    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }

    /**
     * Gets the background panel used for graphical display.
     *
     * @return The BackgroundPanel object used for the tournament.
     */
    protected BackgroundPanel getBackGroundPanel() {
        return backgroundPanel;
    }

    /**
     * Gets the selected category for the tournament.
     *
     * @return The selected category.
     */
    protected String getSelectedCategory(){return selectedCategory;}

    /**
     * Gets the list of referees for the tournament.
     *
     * @return The list of referees.
     */
    public List<Referee> getRefreesList() {return refreesList;}
}
