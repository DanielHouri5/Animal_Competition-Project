package animals;

import Graphics.BackgroundPanel;
import Graphics.CompetitionPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a thread that manages the competition behavior of an animal.
 * This thread will handle the movement and energy consumption of the animal during a competition.
 */
public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private AtomicBoolean startFlag;
    private AtomicBoolean finishFlag;
    private static long sleepTime = 30;
    private CompetitionPanel competitionPanel;

    /**
     * Constructs an AnimalThread with the specified parameters.
     * @param participant The animal participating in the competition.
     * @param neededDistance The distance the animal needs to cover.
     * @param startFlag An AtomicBoolean that signals when the race should start.
     * @param finishFlag An AtomicBoolean that signals when the race has finished.
     * @param competitionPanel The panel where the competition results are displayed.
     */
    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag, CompetitionPanel competitionPanel) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.competitionPanel = competitionPanel;
    }

    /**
     * Executes the animal's competition behavior. The animal will move and consume energy until it covers the needed distance.
     * The thread will wait for the start signal before beginning the competition.
     */
    @Override
    public void run() {
        while (!startFlag.get()) {
            synchronized (startFlag) {
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {return;}
            }
        }
        int currentCompetitionDistance = 0;
        while (currentCompetitionDistance < this.neededDistance)
        {
            if (Thread.interrupted())
                return;
            synchronized (participant) {
                if (participant.getEnergy() <= 0)
                    waitForFeeding();
                participant.move(participant.getLocation());
                currentCompetitionDistance += participant.getSpeed();
                participant.addTotalDistance(participant.getSpeed());

                competitionPanel.setValueAtInfoTable(participant.getTotalDistance(), participant.getId(), 5);

                participant.decreaseEnergy(participant.getEnergyPerMeter()*participant.getSpeed());
                competitionPanel.setValueAtInfoTable(participant.getEnergy(), participant.getId(), 4);
            }
            competitionPanel.getBackgroundPanel().repaint();
            try {
                Thread.sleep(sleepTime);
            }catch (InterruptedException e){}
        }

        synchronized (finishFlag)
        {
            finishFlag.set(true);
            finishFlag.notify();
        }
    }

    /**
     * Sets the sleep time for the thread.
     * @param time The new sleep time in milliseconds.
     */
    public static void setSleepTime(long time)
    {
        sleepTime = time;
    }

    /**
     * Makes the thread wait until the participant's energy is replenished.
     * The thread will block until the energy level of the participant is greater than zero.
     */
    public void waitForFeeding()
    {
        synchronized (participant) {
            while (participant.getEnergy() == 0) {
                try {
                    participant.wait();
                } catch (InterruptedException e) { return; }
            }
        }
    }
}