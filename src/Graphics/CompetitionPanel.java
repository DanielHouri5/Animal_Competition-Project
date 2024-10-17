package Graphics;

import Competitions.CourierTournament;
import Competitions.RegularTournament;
import Competitions.Tournament;
import animals.Animal;
import animals.AnimalThread;
import mobility.Point;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * CompetitionPanel class handles the main interface for the animal competition application.
 * It includes a menu bar, background panel, and bottom buttons panel.
 * This class manages the various aspects of animal competitions, including adding animals,
 * starting competitions, and displaying information.
 */
public class CompetitionPanel extends JPanel
{
    // Components ****************************
    private MenuBar menuBar;
    private BackgroundPanel background;
    private BottomButtonsPanel buttons;
    //****************************************
    private List<Animal> animalsContainer = new ArrayList<>();
    private java.util.List<java.util.List<Animal>> animalsGroups = new ArrayList<>();
    //private List<AddCompetition> addCompetitionDialogs = new ArrayList<>();
    private Animal[][] terrestrialAnimalsGroupsMat = null;
    private Animal[][] waterAnimalsGroupsMat = null;
    private Animal[][] airAnimalsGroupsMat = null;
    private boolean[] categoriesFlags = {false, false, false};
    private Tournament[] tournaments = new Tournament[3];
    private AddCompetition[]addCompetitionDialogs = {null, null, null};
    private Vector<String>[] groupsNames = new Vector[]{null, null, null};
    private String[] tournamentsNames ={null, null, null};
    private int numOfTournament = 1;
    private java.util.List<String> animalsForClear = new ArrayList<>();
    private ComboBoxDialog positionDialog;
    private Info infoTable;
    private Info groupsNamesAndTimesTable;
    private CompetitionFrame frame;
    private boolean flagForChangeTheSleep = false;

    /**
     * Constructor for CompetitionPanel.
     * Initializes the panel with a menu bar, background panel, and bottom buttons panel.
     * Sets up the action listeners for the buttons.
     *
     * @param frame The parent frame for this panel.
     */
    public CompetitionPanel(CompetitionFrame frame)
    {
        this.setLayout(new BorderLayout());
        this.frame = frame;

        Vector<String> columnNames = new Vector<>();
        Collections.addAll(columnNames, "Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption");
        infoTable = new Info(columnNames);

        Vector<String> columnNames2 = new Vector<>();
        Collections.addAll(columnNames2, "Group name", "Tournament name", "Time");
        groupsNamesAndTimesTable = new Info(columnNames2);

        this.menuBar = new MenuBar();
        add(this.menuBar, BorderLayout.NORTH);

        this.background = new BackgroundPanel();
        add(this.background, BorderLayout.CENTER);

        this.buttons = new BottomButtonsPanel(this);
        add(this.buttons, BorderLayout.SOUTH);

        setUpButtons();
    }
    /**
     * Gets the background panel.
     *
     * @return The background panel.
     */
    public BackgroundPanel getBackgroundPanel() {
        return this.background;
    }


    /**
     * Sets up action listeners for the buttons in the bottom panel.
     */
    private void setUpButtons() {
        BottomButtonsPanel bottomButtonsPanel = buttons;

        // Set action listeners for the bottom buttons button
        bottomButtonsPanel.setActionListener(0, e -> addCompetition());
        bottomButtonsPanel.setActionListener(1, e -> addAnimal());
        bottomButtonsPanel.setActionListener(2, e -> startCompetition());
        bottomButtonsPanel.setActionListener(3, e -> clear());
        bottomButtonsPanel.setActionListener(4, e ->  eat());
        bottomButtonsPanel.setActionListener(5, e -> info());
        bottomButtonsPanel.setActionListener(6, e -> infoScores());
        bottomButtonsPanel.setActionListener(7, e -> exit());
    }

    /**
     * Opens a dialog to add a competition. If competitions are already present for all categories,
     * an error message is shown. Otherwise, it initializes a new competition based on user selection.
     */
    public void addCompetition()
    {
        if (terrestrialAnimalsGroupsMat != null && waterAnimalsGroupsMat != null && airAnimalsGroupsMat != null)
            JOptionPane.showMessageDialog(frame, "Error: There are terrestrial, water and air competitions already!", "Error" , JOptionPane.ERROR_MESSAGE);
        else
        {
            AddCompetition dialog = new AddCompetition(frame,animalsGroups, animalsForClear, infoTable, positionDialog, categoriesFlags);
            dialog.setVisible(true);
            if ((categoriesFlags[0] || categoriesFlags[1] || categoriesFlags[2]) &&dialog.getSelectedTournament() != null && dialog.getSelectedCategory() != null && !animalsGroups.isEmpty())
            {
                if (dialog.getSelectedCategory().equals("Terrestrial"))
                {
                    addCompetitionDialogs[0] = dialog;
                    groupsNames[0] = addCompetitionDialogs[0].getTableHeadersValues();
                    tournamentsNames[0] = "Tournament" + numOfTournament++;
                    convertMatByCategory("Terrestrial");
                }
                else if (dialog.getSelectedCategory().equals("Water"))
                {
                    addCompetitionDialogs[1] = dialog;
                    groupsNames[1] = addCompetitionDialogs[1].getTableHeadersValues();
                    tournamentsNames[1] = "Tournament" + numOfTournament++;
                    convertMatByCategory("Water");
                }
                else if (dialog.getSelectedCategory().equals("Air"))
                {
                    addCompetitionDialogs[2] = dialog;
                    groupsNames[2] = addCompetitionDialogs[2].getTableHeadersValues();
                    tournamentsNames[2] = "Tournament" + numOfTournament++;
                    convertMatByCategory("Air");
                }
                animalsGroups = new ArrayList<>();
                background.setTerrestrialAnimalsGroupsMat(terrestrialAnimalsGroupsMat);
                background.setWaterAnimalsGroupsMat(waterAnimalsGroupsMat);
                background.setAirAnimalsGroupsMat(airAnimalsGroupsMat);
                background.repaint();
            }
        }
    }

    /**
     * Converts the list of animal groups to a matrix based on the selected category.
     *
     * @param selectedCategory The category for which to convert the list to a matrix.
     */
    private void convertMatByCategory(String selectedCategory)
    {
        if (selectedCategory.equals("Terrestrial"))
            terrestrialAnimalsGroupsMat = convertListOfListsToMatrix();
        else if (selectedCategory.equals("Water"))
            waterAnimalsGroupsMat = convertListOfListsToMatrix();
        else if (selectedCategory.equals("Air"))
            airAnimalsGroupsMat = convertListOfListsToMatrix();
    }

    /**
     * Converts the list of animal groups to a matrix format.
     *
     * @return A 2D array representing the matrix of animal groups.
     */
    private Animal[][] convertListOfListsToMatrix()
    {
        Animal[][] matrix = new Animal[animalsGroups.get(0).size()][animalsGroups.size()];
        for (int i = 0; i < animalsGroups.get(0).size(); i++)
            for (int j = 0; j < animalsGroups.size(); j++)
                matrix[i][j] = animalsGroups.get(j).get(i);
        return matrix;
    }

    /**
     * Opens a dialog to add a new animal and updates the information table.
     */
    public void addAnimal(){
        AddAnimalDialog dialog = new AddAnimalDialog(this.frame, frame.getCompetitionPanel());
        dialog.setVisible(true);
        Animal animal = dialog.createAnimal();
        if (animal != null) {
            animalsContainer.add(animal);
            String animalStr = dialog.getSelectedType() + ": " + "Name - " + animal.getName() + " Id - " + animal.getId();
            animalsForClear.add(animalStr);

            infoTable.addRow(new Object[]{
                    dialog.getSelectedCategory(),
                    dialog.getSelectedType(),
                    dialog.getSelectedName(),
                    dialog.getSelectedSpeed(),
                    dialog.getEnergyAmount(),
                    animal.getTotalDistance(),
                    dialog.getEnergyConsumption()}
            );

        }
    }

    /**
     * Starts the competition for the selected category and tournament type. Displays an error if
     * the tournaments have already begun or no competition was chosen.
     */
    public void startCompetition()
    {
        if (addCompetitionDialogs[0] == null && addCompetitionDialogs[1] == null && addCompetitionDialogs[2] == null) {
            JOptionPane.showMessageDialog(frame, "Error: You didn't choose a competition yet!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if (!flagForChangeTheSleep) {
                setSleepTime();
                flagForChangeTheSleep = true;
            }
            if (categoriesFlags[0] && addCompetitionDialogs[0] != null && addCompetitionDialogs[0].getSelectedCategory().equals("Terrestrial")) {
                if (tournaments[0] != null) {
                        JOptionPane.showMessageDialog(frame, "Error: The tournaments has begun already!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                else {
                    if (addCompetitionDialogs[0].getSelectedTournament().equals("Regular")) {
                        tournaments[0] = new RegularTournament(terrestrialAnimalsGroupsMat, groupsNames[0], tournamentsNames[0], background, "Terrestrial", this);
                    }
                    else if (addCompetitionDialogs[0].getSelectedTournament().equals("Courier")) {
                        tournaments[0] = new CourierTournament(terrestrialAnimalsGroupsMat, groupsNames[0], tournamentsNames[0], background, "Terrestrial", this);
                    }
                }
            }
            if (categoriesFlags[1] && addCompetitionDialogs[1] != null && addCompetitionDialogs[1].getSelectedCategory().equals("Water")) {
                if (tournaments[1] != null) {
                    JOptionPane.showMessageDialog(frame, "Error: The tournaments has begun already!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    if (addCompetitionDialogs[1].getSelectedTournament().equals("Regular")) {
                        tournaments[1] = new RegularTournament(waterAnimalsGroupsMat, groupsNames[1], tournamentsNames[1], background, "Water", this);
                    }
                    else if (addCompetitionDialogs[1].getSelectedTournament().equals("Courier")) {
                        tournaments[1] = new CourierTournament(waterAnimalsGroupsMat, groupsNames[1], tournamentsNames[1], background, "Water", this);
                    }
                }
            }
            if (categoriesFlags[2] && addCompetitionDialogs[2] != null && addCompetitionDialogs[2].getSelectedCategory().equals("Air")) {
                if (tournaments[2] != null) {
                    JOptionPane.showMessageDialog(frame, "Error: The tournaments has begun already!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (addCompetitionDialogs[2].getSelectedTournament().equals("Regular")) {
                        tournaments[2] = new RegularTournament(airAnimalsGroupsMat, groupsNames[2], tournamentsNames[2], background, "Air", this);
                    }
                    else if (addCompetitionDialogs[2].getSelectedTournament().equals("Courier")) {
                        tournaments[2] = new CourierTournament(airAnimalsGroupsMat, groupsNames[2], tournamentsNames[2], background, "Air", this);
                    }
                }
            }
        }
    }

    /**
     * Opens a dialog to set the sleep time for the competition threads.
     * Updates the sleep time if a valid value is provided.
     */
    public void setSleepTime()
    {
        SleepTimeDialog dialog = new SleepTimeDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        if (dialog.getSleepTime() > 0)
            AnimalThread.setSleepTime(dialog.getSleepTime());
    }

    /**
     * Clears an animal from the background.
     */
    public void clear()
    {
        if (animalsContainer.isEmpty())
            JOptionPane.showMessageDialog(frame, "Error: There are not exist animals.", "Error" , JOptionPane.ERROR_MESSAGE);
        else {
            ComboBoxDialog dialog = new ComboBoxDialog((JFrame) SwingUtilities.getWindowAncestor(this), animalsForClear.toArray(new String[0]), "Select an animal to remove:");
            dialog.setVisible(true);
            dialog.getComboBox().removeItem(dialog.getSelectedOption());
            if (dialog.getSelectedOption() != null)
            {
                String animalId = dialog.getSelectedOption().split(" ")[6];
                for (int i = 0; i < animalsContainer.size(); i++)
                    if (animalsContainer.get(i).getId() == Integer.parseInt(animalId)) {
                        if (!isInCompetition(animalsContainer.get(i))) {
                            animalsContainer.remove(i);
                            animalsForClear.remove(dialog.getSelectedOption());
                        } else
                            JOptionPane.showMessageDialog(frame, "Error: You can't clear an animal that participant in a competition.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
            }
        }
    }

    /**
     * Checks if an animal is currently participating in any competition.
     *
     * @param animal The animal to check.
     * @return true if the animal is in any ongoing competition, false otherwise.
     */
    public boolean isInCompetition(Animal animal)
    {
        return (terrestrialAnimalsGroupsMat != null && checkInMat(terrestrialAnimalsGroupsMat, animal) && !checkIfCompetitionFinished(0)) ||
               (waterAnimalsGroupsMat != null && checkInMat(waterAnimalsGroupsMat, animal) && !checkIfCompetitionFinished(1)) ||
               (airAnimalsGroupsMat != null && checkInMat(airAnimalsGroupsMat, animal) && !checkIfCompetitionFinished(2));
    }

    /**
     * Checks if the specified animal is in the provided matrix of animals.
     *
     * @param animalsMat The matrix to search.
     * @param animal The animal to check for.
     * @return true if the animal is found in the matrix, false otherwise.
     */
    public boolean checkInMat(Animal[][] animalsMat, Animal animal)
    {
        for (Animal[] animalsArr : animalsMat)
            for (Animal a : animalsArr)
                if (animal.getId() == a.getId())
                    return true;
        return false;

    }

    /**
     * Checks if the competition associated with the specified tournament number has finished.
     *
     * @param tournamentNum The index of the tournament.
     * @return true if the competition is finished, false otherwise.
     */
    public boolean checkIfCompetitionFinished(int tournamentNum)
    {
        if (tournaments[tournamentNum] == null)
            return false;
        for (int i = 0; i < tournaments[tournamentNum].getRefreesList().size(); i++)
            if (!tournaments[tournamentNum].getRefreesList().get(i).getFinishFlag().get())
                return false;
        return true;
    }

    /**
     * Opens a dialog to feed an animal. Updates the energy amount in the information table.
     */
    public void eat()
    {
        if (animalsContainer.isEmpty())
            JOptionPane.showMessageDialog(frame, "Error: There are not exist animals.", "Error" , JOptionPane.ERROR_MESSAGE);
        else
        {
            EatDialog dialog = new EatDialog((JFrame) SwingUtilities.getWindowAncestor(this), animalsForClear.toArray(new String[0]));
            dialog.setVisible(true);
            if (dialog.getSelectedOption() != null)
            {
                String animalId = dialog.getSelectedOption().split(" ")[6];
                for (Animal animal : animalsContainer)
                    if (animal.getId() == Integer.parseInt(animalId)) {
                        animal.eat(dialog.getEnergy());
                        setValueAtInfoTable(animal.getEnergy(), animal.getId(), 4);
                        break;
                    }
            }
        }
    }
    /**
     * Displays a dialog showing detailed information about all animals.
     */
    public void info()
    {
        //ScrollPane creation
        JScrollPane scrollPane = new JScrollPane(infoTable);
        //Dialog creation
        JDialog infoDialog = new JDialog(this.frame, "Animal Information", true);
        //Dialog size
        infoDialog.setSize(600, 400);
        //Close button
        infoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //Adding the scrollPane to the dialog
        infoDialog.add(scrollPane);
        //Set the dialog location to the middle of the frame
        infoDialog.setLocationRelativeTo(this);

        infoDialog.setVisible(true);
    }

    /**
     * Displays a dialog showing the scores information of the competitions.
     */
    public void infoScores()
    {
        //ScrollPane creation
        JScrollPane scrollPane = new JScrollPane(groupsNamesAndTimesTable);
        //Dialog creation
        JDialog infoDialog = new JDialog(this.frame, "Scores Information", true);
        //Dialog size
        infoDialog.setSize(600, 400);
        //Close button
        infoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //Adding the scrollPane to the dialog
        infoDialog.add(scrollPane);
        //Set the dialog location to the middle of the frame
        infoDialog.setLocationRelativeTo(this);

        infoDialog.setVisible(true);
    }

    /**
     * Sets a specific value in the information table.
     *
     * @param num The value to set.
     * @param row The row index.
     * @param col The column index.
     */
    public void setValueAtInfoTable(Object num, int row, int col)
    {
        infoTable.setValueAt(num, row, col);
    }

    /**
     * Exits the application.
     */
    public void exit()
    {
        System.exit(0);
    }

    /**
     * Gets the table displaying group names and times.
     *
     * @return The Info object containing group names and times.
     */
    public Info getGroupsNamesAndTimesTable(){return groupsNamesAndTimesTable;}

    /**
     * Gets the container holding all animals.
     *
     * @return The list of animals.
     */
    public List<Animal> getAnimalsContainer(){return animalsContainer;}

    /**
     * Adds an animal to the container.
     *
     * @param animal The animal to add.
     */
    public void addToAnimalsContainer(Animal animal){animalsContainer.add(animal);}

    /**
     * Resets the flag for a specific category.
     *
     * @param index The index of the category to reset.
     */
    public void resetCategoriesFlags(int index) {this.categoriesFlags[index] = false;}

    /**
     * Resets the matrix for terrestrial animals.
     */
    public void resetTerrestrialAnimalsGroupsMat(){terrestrialAnimalsGroupsMat = null;}

    /**
     * Resets the matrix for water animals.
     */
    public void resetWaterAnimalsGroupsMat(){waterAnimalsGroupsMat = null;}

    /**
     * Resets the matrix for air animals.
     */
    public void resetAirAnimalsGroupsMat(){airAnimalsGroupsMat = null;}

    /**
     * Resets the competition dialog for a specific category.
     *
     * @param index The index of the competition dialog to reset.
     */
    public void resetAddCompetitionDialog(int index) {addCompetitionDialogs[index] = null;}

    /**
     * Resets the tournament for a specific category.
     *
     * @param index The index of the tournament to reset.
     */
    public void resetTournament(int index){tournaments[index] = null;}

    /**
     * Resets the group names for a specific category.
     *
     * @param index The index of the category whose group names are to be reset.
     */
    public void resetGroupNames(int index) {groupsNames[index] = null;}

    /**
     * Resets the name of the tournament for a specific category.
     *
     * @param index The index of the category whose tournament name is to be reset.
     */
    public void resetTournamentName(int index){tournamentsNames[index] = null;}
    public void resetFlagForSleepTime(boolean flag){flagForChangeTheSleep = flag;}
}