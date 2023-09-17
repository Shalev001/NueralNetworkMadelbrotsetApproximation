/*
 * Lukas Krampitz
 * Jan 18, 2023
 * Frame that hold the GUI for the Mandelbrot Set
 */
package mandelbrotset;

import java.awt.image.BufferedImage;

/**
 *
 * @author Tacitor
 */
public class MainFrameUI extends javax.swing.JFrame {
    
    private MainPanel mainPanel;

    /**
     * Main constructor
     */
    public MainFrameUI() {
        
        mainPanel = new MainPanel(this);
        
        this.setTitle("Le Mandelbrot Set");
        this.setResizable(false);
        this.setSize(480, 480);
        this.setUndecorated(false); //make sure it has min, max, and close buttons
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //centre it on screen
        this.setLocationRelativeTo(null);

        //add the panel
        this.add(mainPanel);
        mainPanel.setVisible(true);
        
        this.setVisible(true);
        
    }

    /**
     * Pass over the image of the Mandelbrot Set to the JPanel
     *
     * @param image
     */
    public void setMandelSetImage(BufferedImage image) {
        mainPanel.setMandelSetImage(image);
    }
    
}
