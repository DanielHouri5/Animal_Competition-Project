package Graphics;

import animals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The AddAnimalDialog class represents a dialog window for adding a new animal.
 * It provides input fields for various animal attributes based on the selected animal type.
 */
public class AddAnimalDialog extends JDialog {
    private JComboBox<String> categories;
    private JComboBox<String> types;
    private JTextField nameField;
    private JComboBox<Animal.Gender> genderField;
    private JTextField weightField;
    private JTextField speedField;
    private JTextField energyField;
    private JTextField maxEnergyField;
    private JTextField energyPerMeterField;
    private JTextField breedField; // Dog
    private JTextField areaOfLivingField; // Alligator
    private JTextField foodTypeField; // Whale
    private JComboBox<String> castratedComboBox; // Cat
    private JLabel nameError;
    private JLabel genderError;
    private JLabel weightError;
    private JLabel speedError;
    private JLabel energyError;
    private JLabel maxEnergyError;
    private JLabel energyPerMeterError;
    private JLabel breedError; // Dog
    private JLabel castratedError; // Cat
    private JLabel areaOfLivingError; // Alligator
    private JLabel foodTypeError; // Whale
    private JLabel breedLabel;
    private JLabel castratedLabel; // Cat
    private JLabel areaOfLivingLabel; // Alligator
    private JLabel foodTypeLabel; // Whale
    private JComboBox<String> waterTypeComboBox; // Dolphin
    private JLabel waterTypeError; // Dolphin
    private JLabel waterTypeLabel; // Dolphin

    private JComboBox<String> poisonLevelComboBox; // Snake
    private JLabel poisonLevelError; // Snake
    private JLabel poisonLevelLabel; // Snake
    private JTextField lengthField; // Snake
    private JLabel lengthError; // Snake
    private JLabel lengthLabel; // Snake

    private JTextField familyField; // Pigeon
    private JLabel familyError; // Pigeon
    private JLabel familyLabel; // Pigeon

    private JTextField altitudeOfFlightField; // Eagle
    private JLabel altitudeOfFlightError; // Eagle
    private JLabel altitudeOfFlightLabel; // Eagle
    private String selectedAnimal;
    private CompetitionPanel competitionPanel;
    /**
     * Constructs an AddAnimalDialog.
     *
     * @param parent the parent frame
     * @param competitionPanel the competition panel associated with the dialog
     */
    public AddAnimalDialog(JFrame parent, CompetitionPanel competitionPanel) {
        super(parent, "Add animal", true);
        this.competitionPanel = competitionPanel;

        setLayout(new BorderLayout());

        // Initialize components
        categories = new JComboBox<>(new String[]{"Terrestrial", "Water", "Air"});
        types = new JComboBox<>();
        nameField = new JTextField(20);
        genderField = new JComboBox<>(Animal.Gender.values());
        weightField = new JTextField(20);
        speedField = new JTextField(20);
        energyField = new JTextField(20);
        maxEnergyField = new JTextField(20);
        energyPerMeterField = new JTextField(20);
        breedField = new JTextField(20); // Dog
        areaOfLivingField = new JTextField(20); // Alligator
        foodTypeField = new JTextField(20); // Whale
        familyField = new JTextField(20); // Whale
        altitudeOfFlightField = new JTextField(20); // Whale
        castratedComboBox = new JComboBox<>(new String[]{"YES", "NO"}); // Cat
        waterTypeComboBox = new JComboBox<>(new String[]{"Sea", "Sweet"}); // Dolphin
        poisonLevelComboBox = new JComboBox<>(new String[]{"low", "medium", "high"}); // Dolphin
        lengthField = new JTextField(20); // Whale


        // Initialize error labels
        nameError = new JLabel();
        genderError = new JLabel();
        weightError = new JLabel();
        speedError = new JLabel();
        energyError = new JLabel();
        maxEnergyError = new JLabel();
        energyPerMeterError = new JLabel();
        breedError = new JLabel(); // Dog
        castratedError = new JLabel(); // Cat
        areaOfLivingError = new JLabel(); // Alligator
        foodTypeError = new JLabel(); // Whale
        familyError = new JLabel(); // Whale
        altitudeOfFlightError = new JLabel();
        waterTypeError = new JLabel();
        poisonLevelError = new JLabel();
        lengthError = new JLabel();


        nameError.setForeground(Color.RED);
        genderError.setForeground(Color.RED);
        weightError.setForeground(Color.RED);
        speedError.setForeground(Color.RED);
        energyError.setForeground(Color.RED);
        maxEnergyError.setForeground(Color.RED);
        energyPerMeterError.setForeground(Color.RED);
        breedError.setForeground(Color.RED); // Dog
        castratedError.setForeground(Color.RED); // Cat
        areaOfLivingError.setForeground(Color.RED); // Alligator
        foodTypeError.setForeground(Color.RED); // Whale
        familyError.setForeground(Color.RED); // Whale
        altitudeOfFlightError.setForeground(Color.RED);
        waterTypeError.setForeground(Color.RED);
        poisonLevelError.setForeground(Color.RED);
        lengthError.setForeground(Color.RED);

        // Add action listener for category combo box
        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBox2((String) categories.getSelectedItem());
            }
        });

        // Add action listener for types combo box
        types.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDynamicPanel((String) types.getSelectedItem());
            }
        });

        // Layout setup
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components with appropriate layout constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Categories:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(categories, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Types:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(types, gbc);

        addLabelAndFieldWithError(inputPanel, gbc, "Name:", nameField, nameError, 2);
        addLabelAndFieldWithError(inputPanel, gbc, "Gender:", genderField, genderError, 4);
        addLabelAndFieldWithError(inputPanel, gbc, "Weight:", weightField, weightError, 6);
        addLabelAndFieldWithError(inputPanel, gbc, "Speed:", speedField, speedError, 8);
        addLabelAndFieldWithError(inputPanel, gbc, "Energy:", energyField, energyError, 10);
        addLabelAndFieldWithError(inputPanel, gbc, "Max Energy:", maxEnergyField, maxEnergyError, 12);
        addLabelAndFieldWithError(inputPanel, gbc, "Energy Per Meter:", energyPerMeterField, energyPerMeterError, 14);

        // Add breed field, initially hidden
        breedLabel = new JLabel("Breed:");
        gbc.gridy = 16;
        addLabelAndFieldWithError(inputPanel, gbc, "Breed:", breedField, breedError, 16);
        breedField.setVisible(false);
        breedLabel.setVisible(false);

        // Add castrated field (ComboBox), initially hidden
        castratedLabel = new JLabel("Castrated:");
        gbc.gridy = 18;
        addLabelAndFieldWithError(inputPanel, gbc, "Castrated:", castratedComboBox, castratedError, 18);
        castratedComboBox.setVisible(false);
        castratedLabel.setVisible(false);

        // Add areaOfLiving field, initially hidden
        areaOfLivingLabel = new JLabel("Area of Living:");
        gbc.gridy = 20;
        addLabelAndFieldWithError(inputPanel, gbc, "Area of Living:", areaOfLivingField, areaOfLivingError, 20);
        areaOfLivingField.setVisible(false);
        areaOfLivingLabel.setVisible(false);

        // Add foodType field, initially hidden
        foodTypeLabel = new JLabel("Food Type:");
        gbc.gridy = 22;
        addLabelAndFieldWithError(inputPanel, gbc, "Food Type:", foodTypeField, foodTypeError, 22);
        foodTypeField.setVisible(false);
        foodTypeLabel.setVisible(false);

        // Add family field, initially hidden
        familyLabel = new JLabel("Family:");
        gbc.gridy = 24;
        addLabelAndFieldWithError(inputPanel, gbc, "Family:", familyField, familyError, 24);
        familyField.setVisible(false);
        familyLabel.setVisible(false);

        // Add altitudeOfFlight field, initially hidden
        altitudeOfFlightLabel = new JLabel("AltitudeOfFlight:");
        gbc.gridy = 24;
        addLabelAndFieldWithError(inputPanel, gbc, "AltitudeOfFlight:", altitudeOfFlightField, altitudeOfFlightError, 24);
        altitudeOfFlightField.setVisible(false);
        altitudeOfFlightLabel.setVisible(false);

        // Add waterType field (ComboBox), initially hidden
        waterTypeLabel = new JLabel("WaterType:");
        gbc.gridy = 18;
        addLabelAndFieldWithError(inputPanel, gbc, "WaterType:", waterTypeComboBox, waterTypeError, 18);
        waterTypeComboBox.setVisible(false);
        waterTypeLabel.setVisible(false);

        // Add poisonLevel field (ComboBox), initially hidden
        poisonLevelLabel = new JLabel("PoisonLevel:");
        gbc.gridy = 18;
        addLabelAndFieldWithError(inputPanel, gbc, "PoisonLevel:", poisonLevelComboBox, poisonLevelError, 18);
        poisonLevelComboBox.setVisible(false);
        poisonLevelLabel.setVisible(false);

        // Add length field, initially hidden
        lengthLabel = new JLabel("Length:");
        gbc.gridy = 20;
        addLabelAndFieldWithError(inputPanel, gbc, "Length:", lengthField, lengthError, 20);
        lengthField.setVisible(false);
        lengthLabel.setVisible(false);

        // Add an empty row at the bottom for spacing
        gbc.gridy = 26;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        inputPanel.add(new JLabel(), gbc); // Empty label for spacing

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    dispose();
                    selectedAnimal = (String) types.getSelectedItem();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                selectedAnimal = null;
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 700);
        setLocationRelativeTo(parent);
    }

    /**
     * Adds a label, field, and an optional error label to the given panel with GridBagConstraints.
     *
     * @param panel the panel to which the components will be added
     * @param gbc the GridBagConstraints used for positioning
     * @param label the text for the label
     * @param field the field to be added (could be a JTextField, JComboBox, etc.)
     * @param errorLabel the label for displaying error messages (can be null if no error message)
     * @param row the row position for the components
     */
    private void addLabelAndFieldWithError(JPanel panel, GridBagConstraints gbc, String label, JComponent field, JLabel errorLabel, int row) {
        JLabel jLabel = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);

        if (errorLabel != null) {
            gbc.gridy = row + 1; // Place error message below the field
            gbc.insets = new Insets(0, 5, 5, 5); // Add bottom padding for error message
            panel.add(errorLabel, gbc);
            gbc.insets = new Insets(5, 5, 5, 5); // Reset insets
        }

        if (label.equals("Breed:")) { // Dog
            breedLabel = jLabel;
            breedLabel.setVisible(false);
            field.setVisible(false);
        } else if (label.equals("Castrated:")) { // Cat
            castratedLabel = jLabel;
            castratedLabel.setVisible(false);
            field.setVisible(false);
        } else if (label.equals("Area of Living:")) { // Alligator
            areaOfLivingLabel = jLabel;
            areaOfLivingLabel.setVisible(false);
            field.setVisible(false);
        } else if (label.equals("Food Type:")) { // Whale
            foodTypeLabel = jLabel;
            foodTypeLabel.setVisible(false);
            field.setVisible(false);
        }else if (label.equals("Family:")) { // Whale
            familyLabel = jLabel;
            familyLabel.setVisible(false);
            field.setVisible(false);
        }else if (label.equals("AltitudeOfFlight:")) { // Whale
            altitudeOfFlightLabel = jLabel;
            altitudeOfFlightLabel.setVisible(false);
            field.setVisible(false);
        }else if (label.equals("WaterType:")) {
            waterTypeLabel = jLabel;
            waterTypeLabel.setVisible(false);
            field.setVisible(false);
        }else if (label.equals("PoisonLevel:")) {
            poisonLevelLabel = jLabel;
            poisonLevelLabel.setVisible(false);
            field.setVisible(false);
        }else if (label.equals("Length:")) {
            lengthLabel = jLabel;
            lengthLabel.setVisible(false);
            field.setVisible(false);
        }
    }

    /**
     * Updates the combo box with animal types based on the selected category.
     *
     * @param category the category based on which the animal types are updated (e.g., "Terrestrial", "Water", "Air")
     */
    private void updateComboBox2(String category) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        switch (category) {
            case "Terrestrial":
                model.addElement("Dog");
                model.addElement("Cat");
                model.addElement("Snake");
                break;
            case "Water":
                model.addElement("Whale");
                model.addElement("Dolphin");
                model.addElement("Alligator");
                break;
            case "Air":
                model.addElement("Eagle");
                model.addElement("Pigeon");
                break;
        }
        types.setModel(model);
        types.setSelectedIndex(0);
        updateDynamicPanel((String) types.getSelectedItem());
    }

    /**
     * Updates the visibility of fields based on the selected animal type.
     *
     * @param animalType the type of animal selected (e.g., "Dog", "Cat", "Alligator", "Whale", "Pigeon", "Eagle", "Dolphin", "Snake")
     */
    private void updateDynamicPanel(String animalType) {
        // Hide all fields by default
        breedField.setVisible(false);
        breedLabel.setVisible(false);
        castratedComboBox.setVisible(false);
        castratedLabel.setVisible(false);
        areaOfLivingField.setVisible(false);
        areaOfLivingLabel.setVisible(false);
        foodTypeField.setVisible(false);
        foodTypeLabel.setVisible(false);
        familyField.setVisible(false);
        familyLabel.setVisible(false);
        altitudeOfFlightField.setVisible(false);
        altitudeOfFlightLabel.setVisible(false);
        waterTypeComboBox.setVisible(false);
        poisonLevelComboBox.setVisible(false);
        lengthField.setVisible(false);

        // Show fields based on animal type
        if ("Cat".equals(animalType)) {
            castratedComboBox.setVisible(true);
            castratedLabel.setVisible(true);
        } else if ("Dog".equals(animalType)) {
            breedField.setVisible(true);
            breedLabel.setVisible(true);
        } else if ("Alligator".equals(animalType)) {
            areaOfLivingField.setVisible(true);
            areaOfLivingLabel.setVisible(true);
        } else if ("Whale".equals(animalType)) {
            foodTypeField.setVisible(true);
            foodTypeLabel.setVisible(true);
        }else if ("Pigeon".equals(animalType)) {
            familyField.setVisible(true);
            familyLabel.setVisible(true);
        }else if ("Eagle".equals(animalType)) {
            altitudeOfFlightField.setVisible(true);
            altitudeOfFlightLabel.setVisible(true);
        }else if ("Dolphin".equals(animalType)) {
            waterTypeComboBox.setVisible(true);
            waterTypeLabel.setVisible(true);
        }else if ("Snake".equals(animalType)) {
            poisonLevelComboBox.setVisible(true);
            poisonLevelLabel.setVisible(true);
            lengthField.setVisible(true);
            lengthLabel.setVisible(true);
        }

    }

    /**
     * Validates the input fields to ensure all required fields are filled out correctly.
     *
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateInput() {
        // Validate the inputs
        boolean valid = true;
        nameError.setText("");
        genderError.setText("");
        weightError.setText("");
        speedError.setText("");
        energyError.setText("");
        maxEnergyError.setText("");
        energyPerMeterError.setText("");
        breedError.setText(""); // Dog
        castratedError.setText(""); // Cat
        areaOfLivingError.setText(""); // Alligator
        foodTypeError.setText(""); // Whale
        familyError.setText(""); // Whale
        altitudeOfFlightError.setText("");
        waterTypeError.setText("");
        poisonLevelError.setText("");
        lengthError.setText("");

        if (nameField.getText().trim().isEmpty()) {
            nameError.setText("Name is required");
            valid = false;
        }
        if (genderField.getSelectedItem() == null) {
            genderError.setText("Gender is required");
            valid = false;
        }
        if (weightField.getText().trim().isEmpty()) {
            weightError.setText("Weight is required");
            valid = false;
        }
        if (speedField.getText().trim().isEmpty()) {
            speedError.setText("Speed is required");
            valid = false;
        }
        if (energyField.getText().trim().isEmpty()) {
            energyError.setText("Energy is required");
            valid = false;
        }
        if (maxEnergyField.getText().trim().isEmpty()) {
            maxEnergyError.setText("Max Energy is required");
            valid = false;
        }
        if (energyPerMeterField.getText().trim().isEmpty()) {
            energyPerMeterError.setText("Energy Per Meter is required");
            valid = false;
        }

        String selectedType = (String) types.getSelectedItem();
        if ("Dog".equals(selectedType) && breedField.getText().trim().isEmpty()) {
            breedError.setText("Breed is required for Dog");
            valid = false;
        } else if ("Cat".equals(selectedType) && castratedComboBox.getSelectedItem() == null) {
            castratedError.setText("Castrated option is required for Cat");
            valid = false;
        } else if ("Alligator".equals(selectedType) && areaOfLivingField.getText().trim().isEmpty()) {
            areaOfLivingError.setText("Area of Living is required for Alligator");
            valid = false;
        } else if ("Whale".equals(selectedType) && foodTypeField.getText().trim().isEmpty()) {
            foodTypeError.setText("Food Type is required for Whale");
            valid = false;
        }else if ("Pigeon".equals(selectedType) && familyField.getText().trim().isEmpty()) {
            familyError.setText("Family is required for Pigeon");
            valid = false;
        }else if ("Eagle".equals(selectedType) && altitudeOfFlightField.getText().trim().isEmpty()) {
            altitudeOfFlightError.setText("AltitudeOfFlight is required for Eagle");
            valid = false;
        }else if ("Dolphin".equals(selectedType) && waterTypeComboBox.getSelectedItem() == null) {
            waterTypeError.setText("WaterType option is required for Dolphin");
            valid = false;
        }else if ("Snake".equals(selectedType)) {
            if (poisonLevelComboBox.getSelectedItem() == null) {
                poisonLevelError.setText("PoisonLevel option is required for Snake");
                valid = false;
            }
            if (lengthField.getText().trim().isEmpty()) {
                lengthError.setText("Length is required for Snake");
                valid = false;
            }
        }

        return valid;
    }

    /**
     * Gets the currently selected animal type.
     *
     * @return the selected animal type
     */
    public String getSelectedAnimal() {
        return selectedAnimal;
    }

    public Animal getAnimal() {
        // Implement this method to return an Animal instance based on user input
        return null;
    }

    /**
     * Creates an instance of Animal based on user input. The specific type of Animal is created based on the selected animal type.
     *
     * @return a new Animal instance or null if the selected animal type is invalid
     */
    public Animal createAnimal() {
        if (selectedAnimal == null) {
            return null;
        }
        String name = nameField.getText();
        Animal.Gender gender = (Animal.Gender) genderField.getSelectedItem();
        if (genderField.getSelectedItem() == Animal.Gender.Male)
            gender = Animal.Gender.Male;
        else if (genderField.getSelectedItem() == Animal.Gender.Female)
            gender = Animal.Gender.Female;
        else
            gender = Animal.Gender.Hermaphrodite;

        double weight = Double.parseDouble(weightField.getText());
        int speed = Integer.parseInt(speedField.getText());
        int energy = Integer.parseInt(energyField.getText());
        int maxEnergy = Integer.parseInt(maxEnergyField.getText());
        int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());

        return switch (selectedAnimal) {
            case "Dog" ->
                    new Dog(breedField.getText(), name, gender, weight, speed, null, maxEnergy, energy, energyPerMeter, competitionPanel);
            case "Cat" -> {
                boolean cas = castratedComboBox.getSelectedItem().equals("YES");
                yield new Cat(
                        cas,
                        name,
                        gender,
                        weight,
                        speed,
                        null,
                        maxEnergy,
                        energy,
                        energyPerMeter,
                        competitionPanel
                );}
                case "Snake" -> {
                    Snake.Poisonous level;
                    if (poisonLevelComboBox.getSelectedItem() == Snake.Poisonous.low)
                        level = Snake.Poisonous.low;
                    else if (poisonLevelComboBox.getSelectedItem() == Snake.Poisonous.medium)
                        level = Snake.Poisonous.medium;
                    else
                        level = Snake.Poisonous.high;

                    yield new Snake(
                            level,
                            Double.parseDouble(lengthField.getText()),
                            name,
                            gender,
                            weight,
                            speed,
                            null,
                            maxEnergy,
                            energy,
                            energyPerMeter,
                            competitionPanel
                    );
                }
            case "Alligator" ->
                    new Alligator(areaOfLivingField.getText(), name, gender, weight, speed, null, maxEnergy, energy, energyPerMeter, competitionPanel);
            case "Dolphin" -> {
                Dolphin.WaterType watertype;
                if (waterTypeComboBox.getSelectedItem() == Dolphin.WaterType.Sea)
                    watertype = Dolphin.WaterType.Sea;
                else
                    watertype = Dolphin.WaterType.Sweet;

                yield new Dolphin(
                        watertype,
                        name,
                        gender,
                        weight,
                        speed,
                        null,
                        maxEnergy,
                        energy,
                        energyPerMeter,
                        competitionPanel
                );
            }
            case "Whale" ->
                    new Whale(
                            foodTypeField.getText(),
                            name,
                            gender,
                            weight,
                            speed,
                            null,
                            maxEnergy,
                            energy,
                            energyPerMeter,
                            competitionPanel
                    );
            case "Eagle" ->
                    new Eagle(
                            Double.parseDouble(altitudeOfFlightField.getText()),
                            name,
                            gender,
                            weight,
                            speed,
                            null,
                            maxEnergy,
                            energy,
                            energyPerMeter,
                            competitionPanel
                    );
            case "Pigeon" ->
                    new Pigeon(
                            familyField.getText(),
                            name,
                            gender,
                            weight,
                            speed,
                            null,
                            maxEnergy,
                            energy,
                            energyPerMeter,
                            competitionPanel
                    );
            default -> null;
        };
    }


    /**
     * Gets the currently selected category from the categories combo box.
     *
     * @return the selected category
     */
    public String getSelectedCategory() {
        return (String) categories.getSelectedItem();
    }

    /**
     * Gets the currently selected animal type from the animal types combo box.
     *
     * @return the selected animal type
     */
    public String getSelectedType() {
        return selectedAnimal;
    }

    /**
     * Gets the text of the name field.
     *
     * @return the name entered by the user
     */
    public String getSelectedName() {
        return nameField.getText();
    }

    /**
     * Gets the text of the speed field.
     *
     * @return the speed entered by the user
     */
    public String getSelectedSpeed() {
        return speedField.getText();
    }

    /**
     * Gets the text of the energy field.
     *
     * @return the energy entered by the user
     */
    public String getEnergyAmount() {
        return energyField.getText();
    }

    /**
     * Gets the text of the energy consumption field.
     *
     * @return the energy consumption per meter entered by the user
     */
    public String getEnergyConsumption() {
        return energyPerMeterField.getText();
    }
}
