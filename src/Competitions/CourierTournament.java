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
 * Represents a courier tournament where animals compete in a relay-style race.
 * The competition consists of multiple stages, and animals need to pass through each stage.
 * This class sets up the tournament by initializing threads for animals and referees.
 */
public class CourierTournament extends Tournament
{
    /**
     * Constructs a CourierTournament with the specified parameters.
     *
     * @param animals The 2D array of animals participating in the tournament, where each row represents a stage.
     * @param groupsNames The names of the groups participating in the tournament.
     * @param tournamentName The name of the tournament.
     * @param backgroundPanel The panel used for the background of the competition.
     * @param selectedCategory The category of the competition (e.g., Terrestrial, Aquatic).
     * @param competitionPanel The panel where the competition results are displayed.
     */
    public CourierTournament(Animal[][] animals, Vector<String> groupsNames, String tournamentName, BackgroundPanel backgroundPanel, String selectedCategory, CompetitionPanel competitionPanel)
    {
        super(animals, groupsNames, tournamentName, backgroundPanel, selectedCategory, competitionPanel);
    }

    /**
     * Sets up the courier tournament by initializing and starting threads for animals and referees.
     * It calculates the needed distance for each stage based on the category and starts all necessary threads.
     *
     * @param animals The 2D array of animals participating in the tournament, where each row represents a stage.
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

        AtomicBoolean[][] finishFlags = new AtomicBoolean[animals.length][animals[0].length];
        AnimalThread[][] animalThreads = new AnimalThread[animals.length][animals[0].length];
        Thread thread;

        double neededDistance;
        if (!getSelectedCategory().equals("Terrestrial"))
            neededDistance = (double) ((getBackGroundPanel().getWidth() - getBackGroundPanel().getWidth()/12*2 )/ animals.length - animals[0][0].getSize()*2);
        else
            neededDistance = getBackGroundPanel().getWidth() - animals[0][0].getSize()*3;

        // Initialize and start threads for animals in the first stage
        for (int i = 0; i < animals[0].length; i++)
        {
            finishFlags[0][i] = new AtomicBoolean(false);
            animalThreads[0][i] = new AnimalThread(animals[0][i], neededDistance, startFlag, finishFlags[0][i], competitionPanel);
            thread = new Thread(animalThreads[0][i]);
            thread.start();
        }

        // Initialize and start threads for animals in subsequent stages
        for (int i = 1; i < animals.length; i++)
            for (int j = 0; j < animals[0].length; j++)
            {
                if (getSelectedCategory().equals("Terrestrial"))
                {
                    if (i == 1)
                        neededDistance = getBackGroundPanel().getHeight() - animals[0][0].getSize() * 3;
                    else if (i == 2)
                        neededDistance = getBackGroundPanel().getWidth() - animals[0][0].getSize()*3;
                    else
                        neededDistance = getBackGroundPanel().getHeight() - animals[0][0].getSize()*2;
                }
                finishFlags[i][j] = new AtomicBoolean(false);
                animalThreads[i][j] = new AnimalThread(animals[i][j], neededDistance, finishFlags[i - 1][j], finishFlags[i][j], competitionPanel);
                thread = new Thread(animalThreads[i][j]);
                thread.start();
            }

        // Initialize referees and start referee threads
        for (int j = 0; j < animals[0].length; j++)
        {
            Referee referee = new Referee(groupsNames.get(j), scores, finishFlags[animals.length - 1][j], animals, getSelectedCategory(), competitionPanel);
            refreesList.add(referee);
            Thread refereeThread = new Thread(referee);
            refereeThread.start();
        }

        // Create and start the tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, animals[0].length, competitionPanel, groupsNames, tournamentName);
        Thread thread2 = new Thread(this.tournamentThread);
        thread2.start();

    }
}
