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

        File loc = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\mandelbrotBots\\madelbot4");
        Network mandelbot = Network.importf(loc);
        int trainingCycles = 10000;
        double learningSpeed = 0.0009;
        Function actiFunc = new ReLU();

        Color color;

        double real = 0;
        double imaginary = 0;

        int repetitions = 100;
        int repMin;

        double scale = 1.7;//50;

        double xoff = -0.5;//-0.74132652;
        double yoff = +0.1;//-0.23506;

        double ImMin = -(2 / scale) + yoff;
        double ImMax = (2 / scale) + yoff;
        double ReMin = -(2 / scale) + xoff;
        double ReMax = (2 / scale) + xoff;

        int width = 480;
        int height = 480;

        int rep = 0;

        double stepRe = (ReMax - ReMin) / width;
        double stepIm = (ImMax - ImMin) / height;

        int threadnum = 8;
        Multithread[] threads = new Multithread[threadnum];

        Color[] colors = {new Color(230, 50, 38),
            new Color(38, 177, 255),
            new Color(38, 255, 64),
            new Color(0, 0, 0)};

        BufferedImage image = new BufferedImage(width, height + 1, BufferedImage.TYPE_INT_RGB);
        int num = 0;
        while (true) {

            
            num++;
            System.out.println(num);
            for (double Im = ImMin; Im < ImMax; Im += stepIm) {
                for (double Re = ReMin; Re < ReMax; Re += stepRe) {
                    color = new Color(255, 255, 255);

                    try {
                        if (Im < ImMax) {
                            image.setRGB((int) ((Re - ReMin) / (ReMax - ReMin) * width), (int) ((Im - ImMin) / (ImMax - ImMin) * height), color.getRGB());//work in progress
                        }
                    } catch (Exception e) {
                    }

                    Complex c = new Complex(Re, Im);
                    Complex z = new Complex(real, imaginary);

                    double[] input = {(Re - ReMin) / (ReMax - ReMin), (Im - ImMin) / (ImMax - ImMin)};

                    mandelbot.setInput(new Vector(input));

                    mandelbot.compute(actiFunc);

                    rep = (int) (mandelbot.getOutput().getValue(0) * repetitions);
                    //rep = Fractals.Mandelbrot(z,c, repetitions);
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
            NetworkTrainer.train(mandelbot, learningSpeed, trainingCycles, actiFunc, repetitions, ImMin, ImMax, ReMin, ReMax);

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
