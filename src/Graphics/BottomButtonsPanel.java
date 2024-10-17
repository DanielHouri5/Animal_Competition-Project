package Graphics;

import animals.Animal;
import Graphics.ComboBoxDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a panel at the bottom of the competition window containing various buttons for user interactions.
 * The buttons allow users to add competitions, animals, start competitions, clear data, feed animals, show information,
 * view scores, and exit the application.
 */
public class BottomButtonsPanel extends JPanel {
    private JButton[] bottomButtons;

    /**
     * Constructs a BottomButtonsPanel with predefined buttons for various actions.
     *
     * @param competitionPanel The CompetitionPanel where the competition-related actions are managed.
     */
    public BottomButtonsPanel(CompetitionPanel competitionPanel) {
        final String[] buttonsNames = {"Add competition", "Add animal", "Start competition", "Clear", "Eat", "Info", "Scores", "Exit"};
        this.bottomButtons = new JButton[buttonsNames.length];

        setLayout(new GridLayout(1, 0));

        for (int i = 0; i < buttonsNames.length; i++) {
            bottomButtons[i] = new JButton(buttonsNames[i]);
            add(bottomButtons[i]);
        }
    }

    /**
     * Sets an ActionListener for a specific button on the panel.
     *
     * @param index The index of the button for which the ActionListener is being set.
     * @param listener The ActionListener to be set for the button.
     */
    public void setActionListener(int index, ActionListener listener) {
        this.bottomButtons[index].addActionListener(listener);
    }

}