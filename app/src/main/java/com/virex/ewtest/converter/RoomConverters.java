package com.virex.ewtest.converter;

import android.arch.persistence.room.TypeConverter;

import com.virex.ewtest.common.ICity;

public class RoomConverters {

    @TypeConverter
    public static ICity.CityType toCityType(String cityType) {
        return ICity.CityType.valueOf(cityType);
    }
    @TypeConverter
    public static String toString(ICity.CityType cityType) {
        return cityType.toString();
    }
}
