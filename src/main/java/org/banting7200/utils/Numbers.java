package org.banting7200;

public class Numbers {
    public static double range(double min, double max, double newmin, double newmax, double number) {
        /* This function just maps one integer to another */
        return (number-min)/(max-min) * (newmax-newmin) + newmin;
    }
}