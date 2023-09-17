/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mandelbrotSet;
/**
 *
 * @author shale
 */
public class Complex {
    
    double real;
    double imaginary;
    
    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }
    
    public Complex(){
        this.real = 0;
        this.imaginary = 0;
    }
    
    public double getReal(){
        return real;
    }
    
    public double getImaginary(){
        return imaginary;
    }
    
    public double magnitude(){        
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }
    
    public static Complex add(Complex a, Complex b){
        return new Complex(a.real + b.real,a.imaginary + b.imaginary);
    }
    
    public static Complex multiply(Complex a,Complex b){
        
        double real;
        double imaginary;
        real = a.real * b.real - a.imaginary * b.imaginary;
        imaginary = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(real,imaginary);
    }
}
