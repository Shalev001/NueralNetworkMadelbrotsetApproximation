/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package activationFunctions;

/**
 *
 * @author shale
 */
public class ReLU implements Function{
    
    public float compute(float x){
        if (x < 0){
            return 0;
        }
        return x;
    }
    
    public float computeDir(float x){
        if (x < 0){
            return 0;
        }
        return 1;
    }
}
