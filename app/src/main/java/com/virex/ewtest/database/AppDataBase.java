package com.virex.ewtest.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.virex.ewtest.common.ICity;
import com.virex.ewtest.converter.RoomConverters;
import com.virex.ewtest.dao.CityDao;
import com.virex.ewtest.entity.City;

import java.util.concurrent.Executors;

@Database(entities = {City.class}, version = 1, exportSchema = false)
@TypeConverters({RoomConverters.class})
public abstract class AppDataBase extends RoomDatabase {
    private static final String APPLICATION_DB = "ewtest.db";
    private static AppDataBase instance;
    public abstract CityDao cityDao();

    public static AppDataBase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    APPLICATION_DB)

                    //разрешить работать с БД в главном потоке
                    .allowMainThreadQueries()

                    //убиваем всё если схема данных не совпадает
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    //getInstance(context).cityDao().insert(new City("Екатеринбург", ICity.CityType.big));
                                    //getInstance(context).cityDao().insert(new City("Челябинск", ICity.CityType.middle));
                                    //getInstance(context).cityDao().insert(new City("Арамиль", ICity.CityType.small));

                                    City city=new City("Екатеринбург", ICity.CityType.big);
                                    city.janTemp=-25;
                                    city.febTemp=-32;
                                    city.marTemp=3;
                                    city.aprTemp=10;
                                    city.mayTemp=15;
                                    city.junTemp=25;
                                    city.julTemp=30;
                                    city.augTemp=25;
                                    city.sepTemp=10;
                                    city.octTemp=0;
                                    city.nowTemp=-5;
                                    city.decTemp=-18;
                                    getInstance(context).cityDao().insert(city);

                                    city.name="Челябинск";
                                    city.type = ICity.CityType.middle;
                                    city.janTemp=-22;
                                    city.febTemp=-35;
                                    city.marTemp=2;
                                    city.aprTemp=11;
                                    city.mayTemp=13;
                                    city.junTemp=24;
                                    city.julTemp=29;
                                    city.augTemp=21;
                                    city.sepTemp=11;
                                    city.octTemp=1;
                                    city.nowTemp=-7;
                                    city.decTemp=-19;
                                    getInstance(context).cityDao().insert(city);

                                    city.name="Арамиль";
                                    city.type = ICity.CityType.small;
                                    city.janTemp=-24;
                                    city.febTemp=-32;
                                    city.marTemp=8;
                                    city.aprTemp=12;
                                    city.mayTemp=19;
                                    city.junTemp=26;
                                    city.julTemp=29;
                                    city.augTemp=22;
                                    city.sepTemp=9;
                                    city.octTemp=4;
                                    city.nowTemp=-8;
                                    city.decTemp=-17;
                                    getInstance(context).cityDao().insert(city);

                                }
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
