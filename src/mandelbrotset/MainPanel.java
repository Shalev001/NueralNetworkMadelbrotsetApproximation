/*
 * Lukas Krampitz
 * Jan 18, 2023
 * 
 */
package mandelbrotset;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tacitor
 */
public class MainPanel extends javax.swing.JPanel {

    //ref to the super frame
    MainFrameUI mainFrameUI;

    private BufferedImage setImage;

    /**
     * Main Constructor
     *
     * @param m
     */
    public MainPanel(MainFrameUI m) {
        
        setImage = null;

        mainFrameUI = m;

    }

    /**
     * Override the JPanel default paintComponent() method.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //draw the compoments of the super class

        //draw the custom stuff (epic fractal)
        draw(g);
    }

    /**
     * Custom Draw method. Slap up the nifty BufferedImage.
     *
     * @param g
     */
    private void draw(Graphics g) {
        //stop using Graphics. Get G2D

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g2d.drawString("IF you are seeing this message please wait for the image to generate...", 
                50, 50);

        //draw the image
        g2d.drawImage(setImage,
                0,
                0,
                this
        );
        
    }

    /**
     * Update the image of the Mandelbrot Set
     *
     * @param image
     */
    public void setMandelSetImage(BufferedImage image) {
        setImage = image;
        repaint();
    }

}
