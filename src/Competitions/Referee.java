package Competitions;

import Graphics.CompetitionPanel;
import animals.Animal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a referee in the competition who monitors the progress and updates the competition panel
 * when the race for a specific group has finished.
 * The referee will update scores and reset the competition panel based on the category of the competition.
 */
public class Referee implements Runnable
{
    private String name;
    private Scores scores;
    private AtomicBoolean finishFlag;
    private Animal[][]animals;
    private String selectedCategory;
    private CompetitionPanel competitionPanel;

    /**
     * Constructs a Referee with the specified parameters.
     *
     * @param name The name of the referee.
     * @param scores The scores object where the results of the competition are recorded.
     * @param finishFlag An AtomicBoolean that signals when the competition for the group is finished.
     * @param animals A 2D array of animals participating in the competition.
     * @param selectedCategory The category of the competition (e.g., Terrestrial, Water, Air).
     * @param competitionPanel The panel where the competition results are displayed.
     */
    public Referee(String name, Scores scores, AtomicBoolean finishFlag, Animal[][] animals, String selectedCategory, CompetitionPanel competitionPanel)
    {
        this.name = name;
        this.scores = scores;
        this.finishFlag = finishFlag;
        this.animals = animals;
        this.selectedCategory = selectedCategory;
        this.competitionPanel = competitionPanel;
    }

    /**
     * Executes the referee's responsibilities. This includes waiting for the finish flag to be set,
     * updating the scores, and resetting the competition panel based on the competition category.
     */
    @Override
    public void run()
    {
        while (!finishFlag.get()) {
            synchronized (finishFlag)
            {
                try {
                    finishFlag.wait();
                } catch (InterruptedException e) {return;}
            }

            scores.add(name);

            // Reset and update the competition panel based on the competition category
            if (selectedCategory.equals("Terrestrial") && competitionPanel.checkIfCompetitionFinished(0)) {
                competitionPanel.getBackgroundPanel().setTerrestrialAnimalsGroupsMat(null);
                competitionPanel.resetTerrestrialAnimalsGroupsMat();
                competitionPanel.resetCategoriesFlags(0);
                competitionPanel.resetGroupNames(0);
                competitionPanel.resetTournamentName(0);
                competitionPanel.resetAddCompetitionDialog(0);
                competitionPanel.resetTournament(0);
                competitionPanel.resetFlagForSleepTime(false);
            }
            else if (selectedCategory.equals("Water") && competitionPanel.checkIfCompetitionFinished(1)) {
                competitionPanel.getBackgroundPanel().setWaterAnimalsGroupsMat(null);
                competitionPanel.resetWaterAnimalsGroupsMat();
                competitionPanel.resetCategoriesFlags(1);
                competitionPanel.resetGroupNames(1);
                competitionPanel.resetTournamentName(1);
                competitionPanel.resetAddCompetitionDialog(1);
                competitionPanel.resetTournament(1);
                competitionPanel.resetFlagForSleepTime(false);
            }
            else if (selectedCategory.equals("Air") && competitionPanel.checkIfCompetitionFinished(2)) {
                competitionPanel.getBackgroundPanel().setAirAnimalsGroupsMat(null);
                competitionPanel.resetAirAnimalsGroupsMat();
                competitionPanel.resetCategoriesFlags(2);
                competitionPanel.resetGroupNames(2);
                competitionPanel.resetTournamentName(2);
                competitionPanel.resetAddCompetitionDialog(2);
                competitionPanel.resetTournament(2);
                competitionPanel.resetFlagForSleepTime(false);
            }

            competitionPanel.getBackgroundPanel().repaint();
        }

    }

    /**
     * Gets the finish flag that indicates whether the competition for the group is finished.
     *
     * @return The AtomicBoolean finish flag.
     */
    public AtomicBoolean getFinishFlag() {return finishFlag;}
}
