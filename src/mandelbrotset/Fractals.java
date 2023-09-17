/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mandelbrotset;

import java.awt.Color;
import mandelbrotSet.Complex;

/**
 *
 * @author shale
 */
public class Fractals {

    public static int Mandelbrot(Complex z, Complex c, int repititions) {

        int rep = 0;

        for (int i = 0; i < repititions; i++) {
            z = Complex.multiply(z, z);
            z = Complex.add(z, c);
            if (i > 2 && z.magnitude() < 2) {
                rep = i;
            } else if (i > 2) {
                break;
            }
        }

        return rep;
    }

    public static int MandelbrotSubSampled(Complex z, Complex c, int repititions, int samples, double stepRe, double stepIm) {

        int rep = 0;
        double averep = 0;
        double totalDistance = 0;// variable to store the total distance between each index and the middle of the array
        double distance;
        int totalRep = 0;
        for (int x = 0; x < samples; x++) {
            for (int y = 0; y < samples; y++) {
                for (int i = 0; i < repititions; i++) {
                    // computeing the number of repititions each subpixel goes through before growing larger than 2
                    z = Complex.multiply(z, z);
                    z = Complex.add(z, new Complex(c.getReal() + x * (stepRe / samples), c.getImaginary() + y * (stepIm / samples)));
                    if (i > 2 && z.magnitude() < 2) {
                        rep = i;
                    } else if (i > 2) {
                        break;
                    }
                }
                //computing the distance to the center of the matrix
                distance = Math.sqrt((x-(samples/2))*(x-samples/2)+(y-samples/2)*(y-samples/2));
                if (distance == 0){// avoiding a divition by 0 error
                    distance++;
                }
                
                /*        
                totalDistance += 1/(distance*distance*distance); 
                averep += (rep*rep)/(distance*distance)/2;
                totalRep += rep;
                */

                totalDistance += distance; 
                averep += rep/(distance*distance);
                totalRep += rep;
            }   
        }
        
        averep /= samples;
        
        return (int)averep;
    }

    public static Color gradiant(int min, int max, int num, Color[] colors) {
        int step = (max - min) / colors.length;
        if (((num - min) / step) + 1 <= colors.length - 1) {
            int index = (num - min) / step;
            Color color1 = colors[index];
            Color color2 = colors[index + 1];
            Color finalColor = new Color((int) (color1.getRed() + ((color2.getRed() - color1.getRed()) * (((num - min) - index * step) / (float) step))),
                    (int) (color1.getGreen() + ((color2.getGreen() - color1.getGreen()) * (((num - min) - index * step) / (float) step))),
                    (int) (color1.getBlue() + ((color2.getBlue() - color1.getBlue()) * (((num - min) - index * step) / (float) step))));
            return finalColor;
        } else {
            return colors[colors.length - 1];
        }
    }

    public static Color expgradiant(int min, int max, int num, Color[] colors) {
        int step = (max - min) / colors.length;
        if (((num - min) / step) + 1 <= colors.length - 1) {
            int index = (num - min) / step;
            Color color1 = colors[index];
            Color color2 = colors[index + 1];
            Color finalColor = new Color((int) (color1.getRed() + ((color2.getRed() - color1.getRed()) * (((num - min) - index * step) / (float) step) * (((num - min) - index * step) / (float) step))),
                    (int) (color1.getGreen() + ((color2.getGreen() - color1.getGreen()) * (((num - min) - index * step) / (float) step) * (((num - min) - index * step) / (float) step))),
                    (int) (color1.getBlue() + ((color2.getBlue() - color1.getBlue()) * (((num - min) - index * step) / (float) step) * (((num - min) - index * step) / (float) step))));
            return finalColor;
        } else {
            return colors[colors.length - 1];
        }
    }

    public static Color averageColor(Color[] colors) {
        int red = 0;
        int green = 0;
        int blue = 0;
        for (Color color : colors) {
            red += color.getRed();
            green += color.getGreen();
            blue += color.getBlue();
        }
        red /= colors.length;
        green /= colors.length;
        blue /= colors.length;

        return new Color(red, green, blue);
    }

}
