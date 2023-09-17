/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mandelbrotset;

import java.awt.Color;
import java.awt.image.BufferedImage;
import mandelbrotSet.Complex;

/**
 *
 * @author shale
 */
public class Multithread extends Thread {

    Color color;
    Color[] colors;
    double Im, stepRe, stepIm, ReMin, ReMax, ImMin, ImMax, real, imaginary;
    BufferedImage image;
    int rep, repititions, width, height;

    public Multithread(double Im, double stepRe, double stepIm, double ReMin, double ReMax, double ImMin, double ImMax,
            BufferedImage image, int repititions, int width, int height, Color[] colors, double real, double imaginary) {
        this.Im = Im;
        this.stepRe = stepRe;
        this.stepIm = stepIm;
        this.ReMin = ReMin;
        this.ReMax = ReMax;
        this.ImMin = ImMin;
        this.ImMax = ImMax;
        this.image = image;
        this.repititions = repititions;
        this.width = width;
        this.height = height;
        this.colors = colors;
        this.real = real;
        this.imaginary = imaginary;
    }

    public void run() {
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

            rep = Fractals.MandelbrotSubSampled(z, c, repititions, 20, stepRe, stepIm);
            //rep = Fractals.Mandelbrot(z,c, repititions);

            if (rep > 2) {
                //color = new Color((int)(((Math.sin(rep)+1)*255)/2)/(int)(((float)(1+rep/10)/repititions)*255),(int)(((Math.cos(rep+Math.PI/2)+1)*255)/2)/(int)(((float)(1+rep/10)/repititions)*255),(int)(((Math.cos(rep)+1)*255)/2)/(int)(((float)(1+rep/10)/repititions)*255 ));
                color = Fractals.expgradiant(2, repititions, rep, colors);
                try {
                    if (Im < ImMax) {
                        image.setRGB((int) (((Re - ReMin) / (ReMax - ReMin)) * width), (int) (((Im - ImMin) / (ImMax - ImMin)) * height), color.getRGB());//work in progress
                    }
                } catch (Exception e) {
                    System.out.println((Re > ReMax) + ": out of bounds exception");
                }
            }
            rep = 0;

            /*else{
                    System.out.print(" ");
                }*/
        }

    }

}
