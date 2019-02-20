package com.virex.ewtest.common;

import java.util.Locale;

public class Kelvin implements ITemperature {

    private double valueKelvin;

    public Kelvin(double value){
        this.valueKelvin=value;
    }

    public Kelvin(ITemperature temperature){
        this.valueKelvin= temperature.getInKelvin();
    }

    @Override
    public double getInKelvin(){
        return valueKelvin;
    }

    @Override
    public String toString() {
        //return String.valueOf(valueKelvin);
        return String.format(Locale.ENGLISH, "%f K",valueKelvin);
    }
}
