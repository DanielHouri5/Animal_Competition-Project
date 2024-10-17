package Graphics;
/**
 * Name : Daniel Houri, ID : 209445071
 * Name : Eve Hackmon, ID : 209295914
 */
import javax.swing.*;
/**
 * CompetitionFrame class creates the main application window for the animal competition.
 */
public class CompetitionFrame extends JFrame
{
    private CompetitionPanel panel;
    /**
     * Constructor for CompetitionFrame.
     * Sets up the main window with a CompetitionPanel.
     */
    public CompetitionFrame()
    {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new CompetitionPanel(this);
        add(panel);

        pack();
        setVisible(true);
    }
    public CompetitionPanel getCompetitionPanel(){return panel;}
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args)
    {
        new CompetitionFrame();
    }
}
