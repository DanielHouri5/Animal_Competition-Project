package Graphics;

import animals.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a panel that displays a background image and renders different groups of animals (terrestrial, water, and air) on top of it.
 * The panel supports setting and displaying matrices of animals for various categories.
 */
public class BackgroundPanel extends JPanel
{
    private BufferedImage img = null;
    private Animal[][] terrestrialAnimalsGroupsMat;
    private Animal[][] waterAnimalsGroupsMat;
    private Animal[][] airAnimalsGroupsMat;

    /**
     * Constructs a BackgroundPanel and attempts to load a background image.
     * If the image cannot be loaded, an error message is printed.
     */
    public BackgroundPanel()
    {
        try {
            this.img = ImageIO.read(new File("src/graphics2/competitionBackground.png"));
        } catch (IOException e) {System.out.println("Cannot load image"  + e.getMessage()); }
        setLayout(null);
    }

    /**
     * Sets the matrix of terrestrial animals to be displayed on the panel.
     *
     * @param terrestrialAnimalsGroupsMat A 2D array of terrestrial animals.
     */
    public void setTerrestrialAnimalsGroupsMat(Animal[][] terrestrialAnimalsGroupsMat){this.terrestrialAnimalsGroupsMat = terrestrialAnimalsGroupsMat;}

    /**
     * Sets the matrix of water animals to be displayed on the panel.
     *
     * @param waterAnimalsGroupsMat A 2D array of water animals.
     */
    public void setWaterAnimalsGroupsMat(Animal[][] waterAnimalsGroupsMat) {this.waterAnimalsGroupsMat = waterAnimalsGroupsMat;}

    /**
     * Sets the matrix of air animals to be displayed on the panel.
     *
     * @param airAnimalsGroupsMat A 2D array of air animals.
     */
    public void setAirAnimalsGroupsMat(Animal[][] airAnimalsGroupsMat) {this.airAnimalsGroupsMat = airAnimalsGroupsMat;}

    /**
     * Paints the component by drawing the background image and any animals that have been set.
     * Animals are drawn on top of the background image.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        if (terrestrialAnimalsGroupsMat != null)
            for (Animal[] arr : terrestrialAnimalsGroupsMat)
                for (Animal animal : arr)
                    animal.drawObject((Graphics2D) g);
        if (waterAnimalsGroupsMat != null) {
            for (Animal[] arr : waterAnimalsGroupsMat)
                for (Animal animal : arr)
                    animal.drawObject((Graphics2D) g);
        }
        if (airAnimalsGroupsMat != null)
            for (Animal[] arr : airAnimalsGroupsMat)
                for (Animal animal : arr)
                    animal.drawObject((Graphics2D) g);
    }
}
