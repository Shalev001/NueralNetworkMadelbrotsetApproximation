/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package activationFunctions;

/**
 *
 * @author shale
 */
public class sigmoid implements Function{
    
    public float compute(float x){
        return (float)(1/(1+Math.pow(Math.E,-x)));
    }
    
    public float computeDir(float x){
        return compute(x) * (1 - compute(x));
    }
    
}
