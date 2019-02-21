package com.virex.ewtest.common;

public class CityFactory {
    public static ICity newCity(String name, ICity.CityType type){
        switch(type){
            case small:
                return new SmallCity(name);
            case middle:
                return new MiddleCity(name);
            case big:
                return new BigCity(name);
            default:
                return null;
        }
    }
}
