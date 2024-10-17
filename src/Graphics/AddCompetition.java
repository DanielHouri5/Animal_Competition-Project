package Graphics;

import Competitions.RegularTournament;
import animals.*;
import mobility.Point;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;
import java.util.List;

/**
 * A dialog for adding a new competition to the {@link CompetitionFrame}.
 * Allows users to create a tournament by selecting options for tournament type,
 * competition category, and specifying the groups of animals.
 */
public class AddCompetition extends JDialog {
    private CompetitionFrame frame;
    private List<List<Animal>> animalsGroups;
    private java.util.List<String> routes = new ArrayList<>();
    private ComboBoxDialog positionDialog;
    private java.util.List<String> animalsForClear = new ArrayList<>();
    private List<String> animalsForAddToAnimalsForClear = new ArrayList<>();
    private List<Animal> animalsForReturnToContainer = new ArrayList<>();
    private Info infoTable;
    private List<List<String>> infoToAddToInfoTable = new ArrayList<>();
    private Vector<String> tableHeadersValues = new Vector<>();
    private List<JButton> columnsButtons = new ArrayList<>();
    JPanel radioButtonsPanel = new JPanel(new BorderLayout());
    private JPanel buttonsPanel = new JPanel();
    ;
    private boolean[] categoriesFlags;
    private Vector<String> groupsNames;
    private Info groupsTable;
    private JScrollPane scrollPane;
    private int numOfGroups = 0;
    private String selectedTournament = null;
    private String selectedCategory = null;
    private List<String> groupsPositions = new ArrayList<>();
    private JRadioButton regularTournament;
    private JRadioButton courierTournament;
    private JRadioButton terrestrialButton;
    private JRadioButton waterButton;
    private JRadioButton airButton;
    private JButton okButton;
    private JButton cancelButton;
    private int x = 0;
    private int y = 0;
    private int backgroundWidth;
    private int backgroundHeight;

    private static int groupNumber = 1;

    /**
     * Constructs a new {@code AddCompetition} dialog.
     *
     * @param frame             the parent {@link CompetitionFrame}
     * @param animalsGroups     the list of animal groups to be used in the competition
     * @param animalsForClear   list of animal IDs to be cleared
     * @param infoTable         the {@link Info} table to display competition information
     * @param positionDialog    dialog for selecting positions
     * @param categoriesFlags   flags indicating selected competition categories
     */
    public AddCompetition(CompetitionFrame frame, List<List<Animal>> animalsGroups, java.util.List<String> animalsForClear, Info infoTable, ComboBoxDialog positionDialog, boolean[] categoriesFlags) {
        super(frame, true);
        BackgroundPanel background = frame.getCompetitionPanel().getBackgroundPanel();
        background.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                backgroundWidth = background.getWidth();
                backgroundHeight = background.getHeight();
            }
        });
        backgroundWidth = background.getWidth();
        backgroundHeight = background.getHeight();

        this.frame = frame;
        this.animalsGroups = animalsGroups;
        this.animalsForClear = animalsForClear;
        this.infoTable = infoTable;
        this.positionDialog = positionDialog;
        this.categoriesFlags = categoriesFlags;
        tableHeadersValues.add("");

        setLayout(new BorderLayout()); // Ensure the layout is set to BorderLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components


        radioButtonsPanel.add(pageTitle(gbc), BorderLayout.PAGE_START);

        // Tournament options
        radioButtonsPanel.add(tournamentOptions(gbc), BorderLayout.CENTER);
        // Competition categories
        radioButtonsPanel.add(competitionCategories(gbc), BorderLayout.SOUTH);

        add(radioButtonsPanel, BorderLayout.NORTH);

        // Table
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(addButtons(gbc), BorderLayout.NORTH);
        combinedPanel.add(addTable(gbc), BorderLayout.CENTER);
        combinedPanel.add(addOkCancelButtons(gbc), BorderLayout.SOUTH);


        // Add combined panel to the dialog
        add(combinedPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(frame);
    }

    /**
     * Creates a panel with the title of the dialog.
     *
     * @param gbc the {@link GridBagConstraints} used for layout
     * @return the panel containing the title
     */
    public JPanel pageTitle(GridBagConstraints gbc) {
        JPanel titlePanel = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("Create a tournament:");
        title.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 24));

        gbc.gridx = x;
        gbc.gridy = y;
        titlePanel.add(title, gbc);
        return titlePanel;
    }

    /**
     * Creates a panel with radio buttons for selecting the type of tournament.
     *
     * @param gbc the {@link GridBagConstraints} used for layout
     * @return the panel with tournament options
     */
    public JPanel tournamentOptions(GridBagConstraints gbc) {
        // Panel for the label and the radio buttons
        JPanel radioButtons = new JPanel(new GridBagLayout());

        // Label of "Select the type of tournament:"
        gbc.gridx = x;
        gbc.gridy = ++y;
        radioButtons.add(new JLabel("Select a tournament type:"), gbc);


        /* Radio buttons */

        // Regular tournament
        regularTournament = new JRadioButton("Regular");
        gbc.gridx = x + 1;
        radioButtons.add(regularTournament, gbc);

        regularTournament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTournament = "Regular";
            }
        });

        // Courier tournament
        courierTournament = new JRadioButton("Courier");
        gbc.gridx = x + 2;
        radioButtons.add(courierTournament, gbc);

        courierTournament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTournament = "Courier";
            }
        });

        // Create a group of radio buttons to ensure only one option can be selected at a time
        ButtonGroup group = new ButtonGroup();

        group.add(regularTournament);
        group.add(courierTournament);

        return radioButtons;
    }

    /**
     * Creates a panel with radio buttons for selecting the competition category.
     *
     * @param gbc the {@link GridBagConstraints} used for layout
     * @return the panel with competition categories
     */
    public JPanel competitionCategories(GridBagConstraints gbc) {
        // Panel for the label and the radio buttons
        JPanel optionsPanel = new JPanel(new GridBagLayout());

        // Label of "Select a competition category:"
        gbc.gridx = x;
        gbc.gridy = ++y;
        optionsPanel.add(new JLabel("Select a competition category:"), gbc);

        /* Radio buttons */

        // Terrestrial button
        terrestrialButton = new JRadioButton("Terrestrial");
        gbc.gridx = x + 1;
        optionsPanel.add(terrestrialButton, gbc);

        // Water button
        waterButton = new JRadioButton("Water");
        gbc.gridx = x + 2;
        optionsPanel.add(waterButton, gbc);

        // Air button
        airButton = new JRadioButton("Air");
        gbc.gridx = x + 3;
        optionsPanel.add(airButton, gbc);

        // Create a group of radio buttons to ensure only one option can be selected at a time
        ButtonGroup group = new ButtonGroup();

        group.add(terrestrialButton);
        group.add(waterButton);
        group.add(airButton);

        terrestrialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCategory = "Terrestrial";
                routesOptions();
            }
        });
        waterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCategory = "Water";
                routesOptions();
            }
        });
        airButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCategory = "Air";
                routesOptions();
            }
        });

        return optionsPanel;
    }

    /**
     * Creates a panel with a button to add a new group.
     *
     * @param gbc the {@link GridBagConstraints} used for layout
     * @return the panel with the add group button
     */
    public JPanel addButtons(GridBagConstraints gbc) {
        buttonsPanel.setLayout(new GridLayout());
        JButton addGroupButton = new JButton("Add group +");
        gbc.gridx = x;
        gbc.gridy = ++y;
        // Panel to hold the buttons
        columnsButtons.add(addGroupButton);
        buttonsPanel.add(addGroupButton);
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addingCol();
            }
        });
        buttonsPanel.setSize(new Dimension(150, 50));
        return buttonsPanel;
    }

    /**
     * Creates a panel with a table displaying the groups.
     *
     * @param gbc the {@link GridBagConstraints} used for layout
     * @return the panel containing the table
     */
    public JPanel addTable(GridBagConstraints gbc) {
        groupsNames = new Vector<>();
        groupsNames.add("");
        groupsTable = new Info(groupsNames);

        gbc.gridx = x;
        gbc.gridy = ++y;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;

        // Scroll pane with table
        scrollPane = new JScrollPane(groupsTable);
        scrollPane.setPreferredSize(new Dimension(110, 200));

        setColumnsWidth();

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane, gbc);

        return tablePanel;
    }


    /**
     * Creates a panel with OK and Cancel buttons.
     *
     * @param gbc the {@link GridBagConstraints} used for layout
     * @return the panel with OK and Cancel buttons
     */
    public JPanel addOkCancelButtons(GridBagConstraints gbc) {
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonAction();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTournament = null;
                selectedCategory = null;
                animalsGroups = new ArrayList<>();
                categoriesFlags = new boolean[]{false, false, false};
                dispose();
            }
        });
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        return buttonsPanel;
    }

    /**
     * Handles the OK button action. Validates input and processes the competition setup.
     */
    private void okButtonAction() {
        if (animalsGroups.isEmpty())
            JOptionPane.showMessageDialog(frame, "Error: You didn't create any group!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (!checkIfSizesEquals())
            JOptionPane.showMessageDialog(frame, "Error: In any tournament, you must put the same number of animals in all the groups!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (animalsGroups.size() < 2)
            JOptionPane.showMessageDialog(frame, "Error: In any tournament, you must put the at least 2 groups!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedTournament.equals("Courier") && selectedCategory.equals("Terrestrial") && !check4InGroup())
            JOptionPane.showMessageDialog(frame, "Error: In a terrestrial courier tournament, you must put 4 animals in a group!", "Error", JOptionPane.ERROR_MESSAGE);
        if (selectedTournament.equals("Courier") && !checkIfSizesEquals())
            JOptionPane.showMessageDialog(frame, "Error: In a courier tournament, the groups sizes must be equals!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedTournament.equals("Courier") && !checkAtLeast2InGroup())
            JOptionPane.showMessageDialog(frame, "Error: In a courier tournament, you must put at least 2 animals in a group!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedTournament.equals("Courier") && animalsGroups.size() < 2)
            JOptionPane.showMessageDialog(frame, "Error: In a courier tournament, you must put at least 2 groups!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            if (selectedTournament.equals("Courier"))
                setAllAnimalsLocationCourierTournament();
            else if (selectedTournament.equals("Regular"))
                setAllAnimalsLocationRegularTournament();

            if (selectedCategory.equals("Terrestrial"))
                categoriesFlags[0] = true;
            else if (selectedCategory.equals("Water"))
                categoriesFlags[1] = true;
            else if (selectedCategory.equals("Air"))
                categoriesFlags[2] = true;
            if (!animalsForReturnToContainer.isEmpty())
                for (Animal animal : animalsForReturnToContainer)
                    frame.getCompetitionPanel().addToAnimalsContainer(animal);
            if (!animalsForAddToAnimalsForClear.isEmpty())
                animalsForClear.addAll(animalsForAddToAnimalsForClear);
            if (!infoToAddToInfoTable.isEmpty())
                for (List<String> list : infoToAddToInfoTable)
                {
                    infoTable.addRow(new Object[] {
                            list.get(0),
                            list.get(1),
                            list.get(2),
                            list.get(3),
                            list.get(4),
                            list.get(5),
                            list.get(6)
                    });
                }
            dispose();
        }
    }

    /**
     * Checks if all groups contain exactly 4 animals.
     *
     * @return true if all groups contain exactly 4 animals, false otherwise
     */
    private boolean check4InGroup() {
        for (List<Animal> list : animalsGroups)
            if (list.size() != 4)
                return false;
        return true;
    }

    /**
     * Checks if all groups have the same size.
     *
     * @return true if all groups have the same size, false otherwise
     */
    private boolean checkIfSizesEquals() {
        for (int i = 0; i < animalsGroups.size() - 1; i++)
            if (animalsGroups.get(i).size() != animalsGroups.get(i + 1).size())
                return false;
        return true;
    }

    /**
     * Checks if the first group has at least 2 animals.
     *
     * @return true if the first group has at least 2 animals, false otherwise
     */
    private boolean checkAtLeast2InGroup() {
        if (animalsGroups != null && animalsGroups.get(0) != null)
            return animalsGroups.get(0).size() >= 2;
        return false;
    }

    /**
     * Sets the preferred width of the columns in the groups table.
     */
    public void setColumnsWidth() {
        TableColumnModel columnModel = groupsTable.getColumnModel();
        for (int i = 0; i < groupsNames.size(); i++)
            columnModel.getColumn(i).setPreferredWidth(110); // Adjust width as needed
    }

    /**
     * Adds a new column to the groups table and updates related components based on the selected tournament and category.
     * It handles various scenarios, including error messages if the category or tournament settings are invalid.
     */
    public void addingCol() {
        if (selectedTournament == null)
            JOptionPane.showMessageDialog(frame, "Error: You have to choose a tournament!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedCategory == null)
            JOptionPane.showMessageDialog(frame, "Error: You have to choose a category!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedCategory.equals("Terrestrial") && categoriesFlags[0])
            JOptionPane.showMessageDialog(frame, "Error: There is a terrestrial competition already!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedCategory.equals("Water") && categoriesFlags[1])
            JOptionPane.showMessageDialog(frame, "Error: There is a water competition already!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedCategory.equals("Air") && categoriesFlags[2])
            JOptionPane.showMessageDialog(frame, "Error: There is a Air competition already!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            // Position dialog
            if (selectedCategory.equals("Water") || selectedCategory.equals("Air")) {
                positionDialog.setVisible(true); // Select position of the group
            }
            if (selectedCategory.equals("Terrestrial") || positionDialog.getSelectedOption() != null)
            {
                ++numOfGroups;
                animalsGroups.add(new ArrayList<Animal>());
                Animal animal = selectAddAnimalOption(numOfGroups - 1);
                if (animal != null) {// Update the names of the groups
                    if (selectedCategory.equals("Water") || selectedCategory.equals("Air"))
                    {
                        groupsPositions.add(positionDialog.getSelectedOption());
                        positionDialog.getComboBox().removeItem(positionDialog.getSelectedOption());
                        routes.add(positionDialog.getSelectedOption());
                    }

                    if (selectedTournament.equals("Regular") && selectedCategory.equals("Terrestrial"))
                        animal.setLocation(new mobility.Point(0, 0));

                    animalsGroups.get(numOfGroups - 1).add(animal);
                    setEnableCategoryRadioButtons();

                    tableHeadersValues.remove("");
                    tableHeadersValues.add("Group" + (groupNumber++));
                    groupsTable.setTableHeaderValues(tableHeadersValues);

                    if (selectedCategory.equals("Air")) {
                        if (numOfGroups != 5)
                            groupsTable.addCol("");
                    } else {
                        if (numOfGroups != 4)
                            groupsTable.addCol("");
                    }

                    // Remove the "Add group button"
                    JButton addGroupButton = columnsButtons.remove(columnsButtons.size() - 1);
                    // Adding to the list of "Add animal button" another one
                    columnsButtons.add(columnsButtons.size(), new JButton("Add animal +"));
                    // Update the panel with the new button (And add him action listener) and return the "Add group button"
                    updateButtons(addGroupButton, numOfGroups - 1);

                    addRowToColumn(numOfGroups - 1, animal.getName());

                    scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth() * 2, 200));
                    scrollPane.revalidate();
                }
                else {
                    animalsGroups.remove(animalsGroups.size() - 1);
                    --numOfGroups;
                }
            }
        }
    }

    /**
     * Updates the buttons panel with new buttons for adding groups and animals based on the current state.
     * The "Add animal" button is updated or added based on the number of groups and the selected category.
     *
     * @param addGroupButton the button to be added back to the panel after updating.
     * @param buttonIndex the index of the button being updated.
     */
    public void updateButtons(JButton addGroupButton, int buttonIndex) {
        buttonsPanel.removeAll();
        columnsButtons.get(columnsButtons.size() - 1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Animal animal = selectAddAnimalOption(buttonIndex);
                if (animal != null) {
                    if (animalsGroups.size() == buttonIndex)
                        animalsGroups.add(new ArrayList<Animal>());
                    animalsGroups.get(buttonIndex).add(animal);
                    setEnableTournamentRadioButtons();

                    addRowToColumn(buttonIndex, animal.getName());
                }
            }
        });

        columnsButtons.add(columnsButtons.size(), addGroupButton);
        for (int i = 0; i < columnsButtons.size() - 1; i++)
            buttonsPanel.add(columnsButtons.get(i));

        if (selectedCategory.equals("Air")) {
            if (numOfGroups != 5)
                buttonsPanel.add(columnsButtons.get(columnsButtons.size() - 1));
        } else {
            if (numOfGroups != 4)
                buttonsPanel.add(columnsButtons.get(columnsButtons.size() - 1));
        }

        buttonsPanel.revalidate();
    }

    /**
     * Provides a dialog for selecting an option to add an animal: either an existing animal or a new one.
     * Depending on the option selected, it will return the appropriate animal object.
     *
     * @param groupNum the index of the group to which the animal is being added.
     * @return the selected Animal object, or null if no valid selection is made.
     */
    public Animal selectAddAnimalOption(int groupNum)
    {
        if (selectedTournament.equals("Regular") && animalsGroups.get(groupNum).size() == 1)
            JOptionPane.showMessageDialog(frame, "Error: In a regular tournament, there can be 1 animal in a group!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedTournament.equals("Courier") && animalsGroups.get(groupNum).size() == 4)
            JOptionPane.showMessageDialog(frame, "Error: In a courier tournament, there can be 4 animal in a group!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedCategory.equals("Water") && animalsGroups.get(groupNum).size() == 4)
            JOptionPane.showMessageDialog(frame, "Error: There are 4 animals on the background already!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (selectedCategory.equals("Air") && animalsGroups.get(groupNum).size() == 5)
            JOptionPane.showMessageDialog(frame, "Error: There are 5 animals on the background already!", "Error", JOptionPane.ERROR_MESSAGE);
        else
        {
            String[] addAnimalOptions = {"Exist animal", "New animal"};
            ComboBoxDialog dialog = new ComboBoxDialog((JFrame) SwingUtilities.getWindowAncestor(this), addAnimalOptions, "Select how do you want to add animal:");
            dialog.setVisible(true);
            if (dialog.getSelectedOption() != null) {
                if (dialog.getSelectedOption().equals("Exist animal"))
                    return addExistAnimal();
                else if (dialog.getSelectedOption().equals("New animal"))
                    return addNewAnimal();
            }
        }
        return null;
    }

    /**
     * Opens a dialog for adding a new animal. The new animal's details are captured and validated.
     * If the animal is valid and fits the competition category, it is added to the appropriate lists.
     *
     * @return the newly created Animal object, or null if the animal is invalid or not created.
     */
    public Animal addNewAnimal()
    {
            AddAnimalDialog dialog = new AddAnimalDialog(this.frame, frame.getCompetitionPanel());
            dialog.setVisible(true);

            if (dialog.getSelectedAnimal() != null && !dialog.getSelectedCategory().equals(selectedCategory))
                JOptionPane.showMessageDialog(frame, "Error: Animal category is not fit to the competition!", "Error", JOptionPane.ERROR_MESSAGE);
            else {
                Animal animal = dialog.createAnimal();
                if (animal != null) {
                    String animalStr = dialog.getSelectedType() + ": " + "Name - " + animal.getName() + " Id - " + animal.getId();
                    animalsForAddToAnimalsForClear.add(animalStr);

                    List<String> newRow = new ArrayList<>(List.of(
                            dialog.getSelectedCategory(),
                            dialog.getSelectedType(),
                            dialog.getSelectedName(),
                            dialog.getSelectedSpeed(),
                            dialog.getEnergyAmount(),
                            String.valueOf(animal.getTotalDistance()),
                            dialog.getEnergyConsumption()
                    ));
                    infoToAddToInfoTable.add(newRow);
                    animalsForReturnToContainer.add(animal);
                    return animal;
                }
            }
        return null;
    }

    /**
     * Opens a dialog to select an existing animal from a container. The selected animal is returned and removed from the container.
     *
     * @return the selected Animal object, or null if no valid animal is selected.
     */
    public Animal addExistAnimal()
    {

        List<Animal> animalsContainer = AddCompetition.this.frame.getCompetitionPanel().getAnimalsContainer();
        if (animalsContainer.isEmpty())
            JOptionPane.showMessageDialog(frame, "Error: The container of the animals is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        else
        {
            Animal animal = null;
            List<String> names = new ArrayList<>();
            for (Animal value : animalsContainer) {
                if ((selectedCategory.equals("Terrestrial") && value instanceof TerrestrialAnimals) ||
                    (selectedCategory.equals("Air") && value instanceof AirAnimal) ||
                    (selectedCategory.equals("Water") && (value instanceof WaterAnimal || value instanceof Alligator)))
                    names.add(value.getClass().getSimpleName() + ": " + "Name - " + value.getName() + " Id - " + value.getId());
            }

            String[] animalsContainerNames = new String[names.size()];
            for (int i = 0; i < names.size(); i++) {
                animalsContainerNames[i] = names.get(i);
            }
            if (animalsContainerNames.length == 0)
                JOptionPane.showMessageDialog(frame, "Error: The container of the " + selectedCategory + " animals is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            else
            {
                ComboBoxDialog addAnimalFromContainer = new ComboBoxDialog(AddCompetition.this.frame, animalsContainerNames, "Select animal from the existed animals from container");
                addAnimalFromContainer.setVisible(true);
                if (addAnimalFromContainer.getSelectedOption() != null) {
                    String animalId = addAnimalFromContainer.getSelectedOption().split(" ")[6];
                    for (int i = 0; i < animalsContainerNames.length; i++)
                        if (animalsContainer.get(i).getId() == Integer.parseInt(animalId)) {
                            animal = animalsContainer.get(i);
                            animalsContainer.remove(animal);
                            animalsForReturnToContainer.add(animal);
                            return animal;
                        }
                }
            }
        }
        return null;
    }

    /**
     * Initializes the options for positioning groups based on the selected category.
     * This method configures the position dialog with appropriate options.
     */
    private void routesOptions()
    {
        if (selectedCategory != null)
        {
            String[] waterOptions = {"1", "2", "3", "4"};
            if (this.selectedCategory.equals("Water"))
                positionDialog = new ComboBoxDialog((JFrame) SwingUtilities.getWindowAncestor(this), waterOptions, "Select a water group position:");
            else if (this.selectedCategory.equals("Air")) {
                positionDialog = new ComboBoxDialog((JFrame) SwingUtilities.getWindowAncestor(this), waterOptions, "Select an air group position:");
                positionDialog.getComboBox().addItem("5");
            }
        }
    }

    /**
     * Sets the locations of all animals for a regular tournament based on their group.
     */
    private void setAllAnimalsLocationRegularTournament()
    {
        for (int i = 0; i < animalsGroups.size(); i++)
            setAnimalLocationRegularTournament(animalsGroups.get(i).get(0), i);
    }

    /**
     * Sets the location of a single animal in a regular tournament based on its group number and category.
     *
     * @param animal the Animal object to set the location for.
     * @param groupNum the index of the group the animal belongs to.
     */
    private void setAnimalLocationRegularTournament(Animal animal, int groupNum)
    {
        if (selectedCategory.equals("Water"))
        {
            switch (groupsPositions.get(groupNum))
            {
                case "1":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, backgroundHeight / 9 + (backgroundHeight / 9) / 7));
                    break;
                case "2":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 3 + (backgroundHeight / 9) / 7));
                    break;
                case "3":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 5 + (backgroundHeight / 9) / 7));
                    break;
                case "4":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 7 + (backgroundHeight / 9) / 7));
                    break;
            }
        }
        else if (selectedCategory.equals("Air"))
        {
            switch (groupsPositions.get(groupNum))
            {
                case "1":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9) / 7));
                    break;
                case "2":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 2 + (backgroundHeight / 9) / 7));
                    break;
                case "3":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 4 + (backgroundHeight / 9) / 7));
                    break;
                case "4":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 6 + (backgroundHeight / 9) / 7));
                    break;
                case "5":
                    animal.setLocation(new mobility.Point(backgroundWidth/12, (backgroundHeight / 9 ) * 8 + (backgroundHeight / 9) / 7));
                    break;
            }
        }
    }

    /**
     * Sets the locations of all animals for a courier tournament based on their group and individual index.
     */
    private void setAllAnimalsLocationCourierTournament()
    {
        for (int i = 0; i < animalsGroups.size(); i++)
            for (int j = 0; j < animalsGroups.get(i).size(); j++)
                setAnimalLocationCourierTournament(animalsGroups.get(i).get(j), i, j, animalsGroups.get(i).size());
    }

    /**
     * Sets the location of a single animal in a courier tournament based on its group number, index, and category.
     *
     * @param animal the Animal object to set the location for.
     * @param groupNum the index of the group the animal belongs to.
     * @param animalIndex the index of the animal within the group.
     * @param membersNum the number of animals in the group.
     */
    private void setAnimalLocationCourierTournament(Animal animal, int groupNum, int animalIndex, int membersNum)
    {
            if (selectedCategory.equals("Terrestrial"))
            {
                switch (String.valueOf(animalIndex))
                {
                    case "0":
                        animal.setLocation(new mobility.Point(0, 0));
                        break;
                    case "1":
                        animal.setLocation(new mobility.Point(backgroundWidth - animal.getSize(), 0));
                        animal.setOrien(Animal.Orientation.SOUTH);
                        break;
                    case "2":
                        animal.setLocation(new mobility.Point(backgroundWidth - animal.getSize()*2, backgroundHeight - animal.getSize()));
                        animal.setOrien(Animal.Orientation.WEST);
                        break;
                    case "3":
                        animal.setLocation(new mobility.Point(0, backgroundHeight - animal.getSize()*2));
                        animal.setOrien(Animal.Orientation.NORTH);
                        break;
                }
            }
            else if (selectedCategory.equals("Water"))
            {
                switch (groupsPositions.get(groupNum))
                {
                    case "1":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, backgroundHeight / 9 + (backgroundHeight / 9) / 7));
                        break;
                    case "2":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 3 + (backgroundHeight / 9) / 7));
                        break;
                    case "3":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 5 + (backgroundHeight / 9) / 7));
                        break;
                    case "4":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 7 + (backgroundHeight / 9) / 7));
                        break;
                }
            }
            else if (selectedCategory.equals("Air"))
            {
                switch (groupsPositions.get(groupNum))
                {
                    case "1":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9) / 7));
                        break;
                    case "2":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 2 + (backgroundHeight / 9) / 7));
                        break;
                    case "3":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 4 + (backgroundHeight / 9) / 7));
                        break;
                    case "4":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 6 + (backgroundHeight / 9) / 7));
                        break;
                    case "5":
                        animal.setLocation(new mobility.Point(backgroundWidth/12 + ((backgroundWidth - (backgroundWidth/12*2))/membersNum) * animalIndex, (backgroundHeight / 9 ) * 8 + (backgroundHeight / 9) / 7));
                        break;
                }
            }
    }

    /**
     * Adds a new row to a specific column in the groups table with the given data.
     *
     * @param columnIndex the index of the column to add data to.
     * @param data the data to be added to the column.
     */
    private void addRowToColumn(int columnIndex, String data) {
        // Get the current number of rows
        int rowCount = groupsTable.getRowCount();
        // Create a new row with default values
        int i;
        for (i = 0; i < rowCount; i++)
            if (groupsTable.getValueAt(i, columnIndex) == null || groupsTable.getValueAt(i, columnIndex).toString().isEmpty()) {
                System.out.println(groupsTable.getValueAt(i, columnIndex));
                groupsTable.setValueAt(data, i, columnIndex);
                break;
            }
        if (i == rowCount)
        {
            String[] newRow = new String[groupsTable.getColumnCount()];
            Arrays.fill(newRow, null);
            newRow[columnIndex] = data;
            groupsTable.addRow(newRow);
        }
    }

    /**
     * Enables or disables the tournament radio buttons based on the number of animals in the groups.
     */
    private void setEnableTournamentRadioButtons()
    {
        for (List<Animal> list : animalsGroups)
            if (list.size() == 2) {
                regularTournament.setEnabled(false);
                return;
            }
    }

    /**
     * Enables or disables the category radio buttons based on the number of animals in the groups and the selected category.
     */
    private void setEnableCategoryRadioButtons()
    {
        for (List<Animal> list : animalsGroups)
            if (list.size() == 1)
            {
                if (selectedCategory.equals("Terrestrial"))
                {
                    waterButton.setEnabled(false);
                    airButton.setEnabled(false);
                }
                else if (selectedCategory.equals("Water"))
                {
                    terrestrialButton.setEnabled(false);
                    airButton.setEnabled(false);
                }
                else if (selectedCategory.equals("Air"))
                {
                    terrestrialButton.setEnabled(false);
                    waterButton.setEnabled(false);
                }
                return;
            }
    }

    /**
     * Gets the selected tournament type.
     *
     * @return the selected tournament type.
     */
    public String getSelectedTournament(){return selectedTournament;}

    /**
     * Gets the selected category.
     *
     * @return the selected category.
     */
    public String getSelectedCategory(){return selectedCategory;}

    /**
     * Gets the table headers values.
     *
     * @return the vector of table header values.
     */
    public Vector<String> getTableHeadersValues(){return tableHeadersValues;}
}
