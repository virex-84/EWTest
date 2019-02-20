package com.virex.ewtest.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.virex.ewtest.common.ICity;

@Entity
public class City implements ICity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    //наименование города
    public String name;

    //тип города
    public CityType type;

    //температуры по месяцам
    public float janTemp;
    public float febTemp;
    public float marTemp;
    public float aprTemp;
    public float mayTemp;
    public float junTemp;
    public float julTemp;
    public float augTemp;
    public float sepTemp;
    public float octTemp;
    public float nowTemp;
    public float decTemp;

    public City(String name, CityType type){
        this.name=name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
