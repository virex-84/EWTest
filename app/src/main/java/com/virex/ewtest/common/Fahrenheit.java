package com.virex.ewtest.common;

import java.util.Locale;

public class Fahrenheit implements ITemperature {
    private static double kelvin=273.15;
    private double valueKelvin;

    public Fahrenheit(double value){
        this.valueKelvin=(value - 32) * 5/9.0 + kelvin;
    }

    public Fahrenheit(ITemperature temperature){
        this.valueKelvin= temperature.getInKelvin();
    }

    @Override
    public double getInKelvin(){
        return valueKelvin;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%.2f F",(valueKelvin - kelvin) * 9/5 + 32);
    }
}
