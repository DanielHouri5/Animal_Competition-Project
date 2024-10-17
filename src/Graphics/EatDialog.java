package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A dialog for selecting an animal to feed and entering the amount of energy to add.
 */
public class EatDialog extends JDialog {
    private JComboBox<String> comboBox;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField energyField;
    private JLabel energyErrorLabel;
    private String[] options;
    private String selectedOption = null;

    /**
     * Constructs an {@code EatDialog} with the specified options.
     *
     * @param parent The parent {@code JFrame} for this dialog.
     * @param options An array of {@code String} representing the options for the JComboBox.
     */
    public EatDialog(JFrame parent, String[] options) {
        super(parent, true);
        this.options = options;
        setLayout(new BorderLayout());

        comboBox = new JComboBox<>(this.options);
        comboBox.setPreferredSize(new Dimension(200, 50));

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption = (String) comboBox.getSelectedItem();
                if (validateEnergy()) {
                    dispose();
                }
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption = null;
                dispose();
            }
        });

        // Panel for ComboBox and labels
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        // Add label for ComboBox
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        comboPanel.add(new JLabel("Select an animal to feed:"), gbc);

        // Add ComboBox
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        comboPanel.add(comboBox, gbc);

        // Panel for feed and energy input
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcFeed = new GridBagConstraints();
        gbcFeed.insets = new Insets(10, 10, 10, 10); // Padding around components

        // Add "Feed" label
        gbcFeed.gridx = 0;
        gbcFeed.gridy = 0;
        gbcFeed.anchor = GridBagConstraints.WEST;
        feedPanel.add(new JLabel("Enter an energy to add:"), gbcFeed);

        // Add energyField
        energyField = new JTextField(20);
        gbcFeed.gridy = 1;
        gbcFeed.anchor = GridBagConstraints.WEST;
        feedPanel.add(energyField, gbcFeed);

        // Add error label for energyField
        energyErrorLabel = new JLabel();
        energyErrorLabel.setForeground(Color.RED);
        gbcFeed.gridy = 2; // Place error message below the field
        feedPanel.add(energyErrorLabel, gbcFeed);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        add(comboPanel, BorderLayout.NORTH);
        add(feedPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 300);
        setLocationRelativeTo(parent);
    }

    /**
     * Returns the selected option from the JComboBox.
     *
     * @return The selected option as a {@code String}. Returns {@code null} if no option is selected.
     */
    public String getSelectedOption() {
        return selectedOption;
    }

    /**
     * Returns the energy value entered by the user.
     *
     * @return The energy value as an {@code int}.
     * @throws NumberFormatException if the input is not a valid integer.
     */
    public int getEnergy()  {
        return Integer.parseInt(energyField.getText());
    }

    /**
     * Validates the energy input to ensure it is a valid integer.
     *
     * @return {@code true} if the energy input is valid; {@code false} otherwise.
     */
    private boolean validateEnergy() {
        boolean isValid = true;
        energyErrorLabel.setText(""); // Clear previous error message
        try {
            Integer.parseInt(energyField.getText());
        } catch (NumberFormatException e) {
            energyErrorLabel.setText("Energy must be an integer.");
            isValid = false;
        }
        return isValid;
    }
}
