/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mandelbrotset;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import neuralnetwork.Network;

/**
 *
 * @author shale
 */
public class NetworkGenerator {
    
    public static void main(String[] args){
    File loc = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\mandelbrotBots\\madelbot5");
        
        int[] networkInfo = {2,50,200,500,200,50,1};
        
        Network mandelbot = new Network(networkInfo);
        
        mandelbot.InitializeRandomBiases(0,1);
        mandelbot.InitializeRandomWeights(-0.259f,0.25f);
        //mandelbot.InitializeWeightsAsIdentities();
        
        try {
            mandelbot.export(loc);
        } catch (IOException ex) {
            Logger.getLogger(NetworkTrainer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
