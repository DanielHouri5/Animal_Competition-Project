package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A dialog for selecting an option from a JComboBox.
 */
public class ComboBoxDialog extends JDialog
{
    private JComboBox<String> comboBox;
    private JButton okButton;
    private JButton cancelButton;
    private String[] options;
    private String selectedOption = null;

    /**
     * Constructs a {@code ComboBoxDialog} with the specified options and label.
     *
     * @param parent The parent {@code JFrame} for this dialog.
     * @param options An array of {@code String} representing the options for the JComboBox.
     * @param label The label to be displayed above the JComboBox.
     */
    public ComboBoxDialog(JFrame parent, String[] options, String label)
    {
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
                dispose();
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

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        comboPanel.add(new JLabel(label), gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        comboPanel.add(comboBox, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(comboPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 300);
        setLocationRelativeTo(parent);

    }

    /**
     * Returns the selected option from the JComboBox.
     *
     * @return The selected option as a {@code String}. Returns {@code null} if no option is selected.
     */
    public String getSelectedOption(){return selectedOption;}

    /**
     * Returns the {@code JComboBox} used in the dialog.
     *
     * @return The {@code JComboBox} instance.
     */
    public JComboBox<String> getComboBox(){return this.comboBox;}
}
