package Competitions;

import Graphics.BackgroundPanel;
import Graphics.CompetitionPanel;
import animals.Animal;
import animals.AnimalThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a regular tournament where animals compete against each other.
 * This class sets up the tournament by initializing threads for animals and referees.
 */
public class RegularTournament extends Tournament
{
    /**
     * Constructs a RegularTournament with the specified parameters.
     *
     * @param animals The 2D array of animals participating in the tournament.
     * @param groupsNames The names of the groups participating in the tournament.
     * @param tournamentName The name of the tournament.
     * @param backgroundPanel The panel used for the background of the competition.
     * @param selectedCategory The category of the competition (e.g., Terrestrial, Aquatic).
     * @param competitionPanel The panel where the competition results are displayed.
     */
    public RegularTournament(Animal[][] animals, Vector<String> groupsNames, String tournamentName, BackgroundPanel backgroundPanel, String selectedCategory, CompetitionPanel competitionPanel) {
        super(animals, groupsNames, tournamentName, backgroundPanel, selectedCategory, competitionPanel);
    }

    /**
     * Sets up the tournament by initializing and starting threads for animals and referees.
     * It calculates the needed distance for the competition based on the category and starts all necessary threads.
     *
     * @param animals The 2D array of animals participating in the tournament.
     * @param groupsNames The names of the groups participating in the tournament.
     * @param tournamentName The name of the tournament.
     * @param refreesList The list to be populated with referees for the tournament.
     * @param competitionPanel The panel where the competition results are displayed.
     */
    @Override
    public void setUp(Animal[][] animals, Vector<String> groupsNames, String tournamentName, List<Referee> refreesList, CompetitionPanel competitionPanel)
    {
        AtomicBoolean startFlag = new AtomicBoolean(false);
        Scores scores = new Scores();

        AtomicBoolean[] finishFlags = new AtomicBoolean[animals[0].length];
        AnimalThread[] animalThreads = new AnimalThread[animals[0].length];

        double neededDistance;
        if (getSelectedCategory().equals("Terrestrial"))
            neededDistance = (double) getBackGroundPanel().getWidth()*2 + getBackGroundPanel().getHeight()*2 - animals[0][0].getSize()*2;
        else
            neededDistance = (double) (getBackGroundPanel().getWidth() - getBackGroundPanel().getWidth()/12*2 - animals[0][0].getSize()*2);

        // Initialize and start threads for each animal and referee
        for (int i = 0; i < animals[0].length; i++)
        {
            finishFlags[i] = new AtomicBoolean(false);
            animalThreads[i] = new AnimalThread(animals[0][i], neededDistance, startFlag, finishFlags[i], competitionPanel);
            Thread thread = new Thread(animalThreads[i]);
            thread.start();

            Referee referee = new Referee(groupsNames.get(i), scores, finishFlags[i], animals, getSelectedCategory(), competitionPanel);
            refreesList.add(referee);
            Thread refereeThread = new Thread(referee);
            refereeThread.start();
        }
        // Create and start the tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, animals[0].length, competitionPanel, groupsNames, tournamentName);
        Thread thread = new Thread(this.tournamentThread);
        thread.start();
    }

}
