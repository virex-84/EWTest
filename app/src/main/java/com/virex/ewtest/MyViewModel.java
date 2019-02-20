package com.virex.ewtest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.virex.ewtest.entity.City;
import com.virex.ewtest.repository.CityRepository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private CityRepository cityRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        cityRepository=new CityRepository(application);
    }

    public void addCity(City city){
        cityRepository.insert(city);
    }

    public void updateCity(City city){
        cityRepository.update(city);
    }

    public void deleteCity(City city){
        cityRepository.delete(city);
    }

    public LiveData<List<City>> getAllCites() {
        return cityRepository.getAll();
    }
}
