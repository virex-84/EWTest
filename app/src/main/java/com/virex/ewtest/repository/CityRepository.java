package com.virex.ewtest.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.virex.ewtest.database.AppDataBase;
import com.virex.ewtest.entity.City;

import java.util.List;

public class CityRepository {
    private AppDataBase database;

    public CityRepository(Context context) {
        database = AppDataBase.getInstance(context.getApplicationContext());
    }

    public void insert(City city){
        database.cityDao().insert(city);
    }

    public void update(City city){
        database.cityDao().update(city);
    }

    public void delete(City city){
        database.cityDao().delete(city);
    }

    public LiveData<List<City>> getAll() {
        return database.cityDao().getAll();
    }
}
