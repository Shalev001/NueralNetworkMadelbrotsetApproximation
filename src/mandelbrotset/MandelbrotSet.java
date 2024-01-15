package mandelbrotset;

import MatrixVector.Vector;
import activationFunctions.Function;
import activationFunctions.ReLU;
import activationFunctions.sigmoid;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import mandelbrotSet.Complex;
import mandelbrotset.Fractals;
import neuralnetwork.Network;

/**
 *
 * @author shale
 */
class MandelbrotSet {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        //The panel to display it on
        MainFrameUI theFrame;

        //Make the frame
        theFrame = new MainFrameUI();

        File loc = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\mandelbrotBots\\madelbot5");
        Network mandelbot = Network.importf(loc);
        int trainingCycles = 100000;
        float learningSpeed = 0.0001f;
        Function actiFunc = new ReLU();

        Color color;

        float real = 0;
        float imaginary = 0;

        int repetitions = 100;
        int repMin;

        float scale = 1.7f;//50;

        float xoff = -0.5f;//-0.74132652;
        float yoff = +0.1f;//-0.23506;

        float ImMin = -(2 / scale) + yoff;
        float ImMax = (2 / scale) + yoff;
        float ReMin = -(2 / scale) + xoff;
        float ReMax = (2 / scale) + xoff;

        int width = 480;
        int height = 480;

        int rep = 0;

        float stepRe = (ReMax - ReMin) / width;
        float stepIm = (ImMax - ImMin) / height;

        int threadnum = 8;
        Multithread[] threads = new Multithread[threadnum];

        Color[] colors = {new Color(230, 50, 38),
            new Color(38, 177, 255),
            new Color(38, 255, 64),
            new Color(0, 0, 0)};

        BufferedImage image = new BufferedImage(width, height + 1, BufferedImage.TYPE_INT_RGB);
        int num = 0;
        while (true) {

            NetworkTrainer.train(mandelbot, learningSpeed, trainingCycles, actiFunc, repetitions, ImMin, ImMax, ReMin, ReMax);
            
            num++;
            System.out.println(num);
            
            
            
            for (float Im = ImMin; Im < ImMax; Im += stepIm) {
                for (float Re = ReMin; Re < ReMax; Re += stepRe) {
                    color = new Color(255, 255, 255);

                    try {
                        if (Im < ImMax) {
                            image.setRGB((int) ((Re - ReMin) / (ReMax - ReMin) * width), (int) ((Im - ImMin) / (ImMax - ImMin) * height), color.getRGB());//work in progress
                        }
                    } catch (Exception e) {
                    }

                    Complex c = new Complex(Re, Im);
                    Complex z = new Complex(real, imaginary);

                    float[] input = {(Re - ReMin) / (ReMax - ReMin), (Im - ImMin) / (ImMax - ImMin)};

                    mandelbot.setInput(new Vector(input));
                    
                    mandelbot.compute(actiFunc);
                    
                    //System.out.println(mandelbot.getOutput().toString());

                    //rep = (int) (mandelbot.getOutput().getValue(0) * repetitions);
                    rep = Fractals.Mandelbrot(z,c, repetitions);
                    //System.out.println(mandelbot.getOutput().getValue(0));

                    if (rep > 2) {
                        //color = new Color((int)(((Math.sin(rep)+1)*255)/2)/(int)(((float)(1+rep/10)/repititions)*255),(int)(((Math.cos(rep+Math.PI/2)+1)*255)/2)/(int)(((float)(1+rep/10)/repititions)*255),(int)(((Math.cos(rep)+1)*255)/2)/(int)(((float)(1+rep/10)/repititions)*255 ));
                        color = Fractals.expgradiant(2, repetitions, rep, colors);
                        try {
                            if (Im < ImMax) {
                                image.setRGB((int) (((Re - ReMin) / (ReMax - ReMin)) * width), (int) (((Im - ImMin) / (ImMax - ImMin)) * height), color.getRGB());//work in progress
                            }
                        } catch (Exception e) {
                            System.out.println((Re > ReMax) + ": out of bounds exception");
                        }
                    }
                    rep = 0;

                }
                theFrame.setMandelSetImage(image);
            }
            
            
            //operations to be done between frames
            
            learningSpeed *= 1;
            System.out.println(learningSpeed);
            //Now that the image has been made pass it over to the panel
            
            //xoff+=0.05;
            //yoff+=0.05;
            //TimeUnit.MILLISECONDS.sleep(100);
            scale *= 1;
            ImMin = -(2 / scale) + yoff;
            ImMax = (2 / scale) + yoff;
            ReMin = -(2 / scale) + xoff;
            ReMax = (2 / scale) + xoff;
            stepRe = (ReMax - ReMin) / width;
            stepIm = (ImMax - ImMin) / height;
            real += 0;
            imaginary += 0;
            //File file = new File("mandelbrot3.png");

            //ImageIO.write(image, "png", file);
        }

    }
}
