package com.virex.ewtest.common;

import java.util.Locale;

public class Celsium implements ITemperature {
    private static double kelvin=273.15;
    private double valueKelvin;

    public Celsium(double value){
        this.valueKelvin=value+kelvin;
    }

    public Celsium(ITemperature temperature){
        this.valueKelvin= temperature.getInKelvin();
    }

    @Override
    public double getInKelvin(){
        return valueKelvin;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%.1f °C",valueKelvin-kelvin);
    }
}
