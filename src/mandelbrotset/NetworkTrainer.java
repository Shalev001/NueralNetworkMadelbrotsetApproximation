/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mandelbrotset;

import MatrixVector.Vector;
import activationFunctions.Function;
import activationFunctions.sigmoid;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import neuralnetwork.Network;
import mandelbrotSet.Complex;

/**
 *
 * @author shale
 */
public class NetworkTrainer {

    public static void train(Network mandelbot, double learningSpeed, int trainingCycles, Function actiFunc, int repetitions,double ImMin, double ImMax, double ReMin, double ReMax) {

        /* //code to generate new bot
        
        File loc = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\mandelbrotBots\\madelbot");
        
        int[] networkInfo = {2,50,50,1};
        
        Network mandelbot = new Network(networkInfo);
        
        mandelbot.InitializeRandomBiases();
        mandelbot.InitializeWeightsAsIdentities();
        
        try {
            mandelbot.export(loc);
        } catch (IOException ex) {
            Logger.getLogger(NetworkTrainer.class.getName()).log(Level.SEVERE, null, ex);
        }
         */

        for (int i = 0; i < trainingCycles; i++) {

            double real = ReMin + (Math.random() * (ReMax - ReMin));
            double imaginary = ImMin + (Math.random() * (ImMax - ImMin));

            double[] input = {(real - ReMin) / (ReMax - ReMin), (imaginary - ImMin) / (ImMax - ImMin)};
            
            mandelbot.setInput(new Vector(input));

            Complex point = new Complex(real, imaginary);

            //getting the number of repetitions compered to how many allowed repetitions
            double[] reps = {(double) Fractals.Mandelbrot(new Complex(), point, repetitions) / repetitions};
            
            Vector expected = new Vector(reps);

            mandelbot.stocasticGradientDiscent(expected, learningSpeed, actiFunc);
        }
    }

}
