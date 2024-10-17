package Graphics;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A custom {@code JMenuBar} that provides menu options for exiting the application and displaying help information.
 */
public class MenuBar extends JMenuBar
{
    /** A map storing action listeners for menu items, keyed by their names. */
    private Map<String, ActionListener> actionsMap = new HashMap<>();

    /**
     * Constructs a {@code MenuBar} and initializes the menu items with their respective actions.
     */
    public MenuBar()
    {
        // Initialize actions for menu items
        actionsMap.put("Exit", e -> System.exit(0));
        actionsMap.put("Help", e -> JOptionPane.showMessageDialog(null, "Home Work 2\nGUI"));

        // Create and add menus to the menu bar
        add(createJMenu("File", "Exit"));
        add(createJMenu("Help", "Help"));
    }

    /**
     * Creates a {@code JMenu} with a single menu item.
     *
     * @param menuName The name of the menu.
     * @param itemName The name of the menu item.
     * @return The created {@code JMenu} with the specified item.
     */
    public JMenu createJMenu(String menuName, String itemName)
    {
        JMenu menu = new JMenu(menuName);
        JMenuItem item = new JMenuItem(itemName);
        item.addActionListener(actionsMap.get(itemName)); // Add action listener to the menu item
        menu.add(item); // Add the menu item to the menu
        return menu;
    }
}
