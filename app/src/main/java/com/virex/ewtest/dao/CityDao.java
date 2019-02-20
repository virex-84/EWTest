package com.virex.ewtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.virex.ewtest.entity.City;

import java.util.List;

@Dao
public interface CityDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(City...city);

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(City...city);

    @Transaction
    @Delete
    void delete(City...city);

    @Transaction
    @Query("SELECT * FROM City where id=:id" )
    City getByID(int id);

    @Transaction
    @Query("SELECT * FROM City" )
    LiveData<List<City>> getAll();
}