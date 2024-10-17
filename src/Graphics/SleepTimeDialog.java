package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * A dialog for inputting sleep time, allowing users to either enter a custom value or use a default one.
 * Provides validation to ensure that the entered sleep time is an integer.
 */
public class SleepTimeDialog extends JDialog
{
        private JButton okButton;
        private JButton cancelButton;
        private JTextField sleepTimeField;
        private JLabel sleepTimeErrorLabel;

        /**
         * Constructs a {@code SleepTimeDialog} with the specified parent frame.
         * Sets up the layout, buttons, and input field for the dialog.
         *
         * @param parent The parent frame for this dialog.
         */
        public SleepTimeDialog(JFrame parent) {
            super(parent, true);
            setLayout(new BorderLayout());

            okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (validateEnergy()) {
                        dispose();
                    }
                }
            });

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            JPanel feedPanel = new JPanel();
            feedPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbcFeed = new GridBagConstraints();
            gbcFeed.insets = new Insets(10, 10, 10, 10); // Padding around components

            gbcFeed.gridx = 0;
            gbcFeed.gridy = 0;
            gbcFeed.anchor = GridBagConstraints.WEST;
            feedPanel.add(new JLabel("Enter the sleep time yourself or use what the system has:"), gbcFeed);

            sleepTimeField = new JTextField(20);
            gbcFeed.gridy = 1;
            gbcFeed.anchor = GridBagConstraints.WEST;
            feedPanel.add(sleepTimeField, gbcFeed);

            sleepTimeErrorLabel = new JLabel();
            sleepTimeErrorLabel.setForeground(Color.RED);
            gbcFeed.gridy = 2; // Place error message below the field
            feedPanel.add(sleepTimeErrorLabel, gbcFeed);

            // Panel for buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            // Add panels to the dialog
            add(feedPanel, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.CENTER);

            setSize(600, 300);
            setLocationRelativeTo(parent);
        }

        /**
         * Retrieves the sleep time entered by the user.
         * If no value is entered, returns 0.
         *
         * @return The sleep time as an integer, or 0 if no value is entered.
         */
        public int getSleepTime()
        {
            if (!Objects.equals(sleepTimeField.getText(), ""))
                return Integer.parseInt(sleepTimeField.getText());
            return 0;
        }

        /**
         * Validates the entered sleep time to ensure it is an integer.
         * Displays an error message if the input is invalid.
         *
         * @return {@code true} if the entered sleep time is valid, {@code false} otherwise.
         */
        private boolean validateEnergy() {
            boolean isValid = true;
            sleepTimeErrorLabel.setText(""); // Clear previous error message
            try {
                Integer.parseInt(sleepTimeField.getText());
            } catch (NumberFormatException e) {
                sleepTimeErrorLabel.setText("Sleep time must be an integer.");
                isValid = false;
            }
            return isValid;
        }

}
