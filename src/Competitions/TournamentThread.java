package Competitions;

import Graphics.CompetitionPanel;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a thread that manages the tournament competition.
 * This thread is responsible for starting the competition, updating the competition panel with scores,
 * and checking when all groups have finished.
 */
public class TournamentThread implements Runnable
{
    private Scores scores;
    private AtomicBoolean startSignal;
    private int groups;
    private CompetitionPanel competitionPanel;
    private Vector<String>groupsNames;
    private String tournamentName;

    /**
     * Constructs a TournamentThread with the specified parameters.
     *
     * @param scores The Scores object that holds the final results for each group.
     * @param startSignal An AtomicBoolean that signals when the tournament should start.
     * @param groups The number of groups participating in the tournament.
     * @param competitionPanel The panel where the competition results are displayed.
     * @param groupsNames A vector of group names participating in the tournament.
     * @param tournamentName The name of the tournament.
     */
    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups, CompetitionPanel competitionPanel, Vector<String> groupsNames, String tournamentName)
    {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.competitionPanel = competitionPanel;
        this.groupsNames = groupsNames;
        this.tournamentName = tournamentName;
    }

    /**
     * Starts the tournament by signaling the start of the competition, and then
     * continuously updates the competition panel with the latest scores until all
     * groups have finished. The thread sleeps for a short period between updates.
     */
    @Override
    public void run()
    {
        synchronized (startSignal)
        {
            startSignal.set(true);
            startSignal.notifyAll();
        }
        for (int i = 0; i < groups; ++i)
            competitionPanel.getGroupsNamesAndTimesTable().addRow(new Object[]{groupsNames.get(i), tournamentName, ""});

        // Continuously update the competition panel with the current scores
        while (!allGroupsFinished()) {
            updateCompetitionPanel();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                return;
            }
        }
        updateCompetitionPanel();
    }

    /**
     * Checks if all groups have finished the competition.
     * @return {@code true} if all groups have finished, {@code false} otherwise.
     */
    private boolean allGroupsFinished() {
        for (String groupName : groupsNames) {
            if (scores.getAll().get(groupName) == null) { // Assuming getScore returns null if the group hasn't finished
                return false;
            }
        }
        return true;
    }

    /**
     * Updates the competition panel with the latest scores for each group.
     * It retrieves the current scores from the Scores object and updates the table.
     */
    private void updateCompetitionPanel() {
        for (int i = 0; i < groups; ++i) {
            for (int j = 0; j < competitionPanel.getGroupsNamesAndTimesTable().getRowCount(); j++) {
                if (String.valueOf(competitionPanel.getGroupsNamesAndTimesTable().getValueAt(j, 0)).equals(groupsNames.get(i)) && String.valueOf(competitionPanel.getGroupsNamesAndTimesTable().getValueAt(j, 1)).equals(tournamentName))
            competitionPanel.getGroupsNamesAndTimesTable().setValueAt(scores.getAll().get(groupsNames.get(i)), j, 2); // Update the time in the table
        }
    }
    }
}
