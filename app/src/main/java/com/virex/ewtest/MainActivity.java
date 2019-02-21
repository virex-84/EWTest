package com.virex.ewtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.virex.ewtest.common.Celsium;
import com.virex.ewtest.common.Fahrenheit;
import com.virex.ewtest.common.Kelvin;
import com.virex.ewtest.entity.City;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyViewModel model;
    Spinner spinner_choise_city;
    Spinner choise_seasons;
    TextView tv_cityType;
    TextView tv_AmountTempC;
    TextView tv_AmountTempF;
    TextView tv_AmountTempK;

    City currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = ViewModelProviders.of(this).get(MyViewModel.class);
        spinner_choise_city = findViewById(R.id.choise_city);
        choise_seasons = findViewById(R.id.choise_seasons);
        tv_cityType=findViewById(R.id.tv_cityType);
        tv_AmountTempC=findViewById(R.id.tv_AmountTempC);
        tv_AmountTempF=findViewById(R.id.tv_AmountTempF);
        tv_AmountTempK=findViewById(R.id.tv_AmountTempK);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //подписываемся на список городов
        model.getAllCites().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cities) {
                if (cities==null) return;
                spinner_choise_city.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,cities));
            }
        });

        //обработка выбора города
        spinner_choise_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCity = (City) parent.getAdapter().getItem(position);
                showInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        choise_seasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showInfo(){
        if (currentCity==null) return;

        String cityType="";
        switch (currentCity.type){
            case small:
                cityType="Малый город";
                break;
            case middle:
                cityType="Средний город";
                break;
            case big:
                cityType="Большой город";
                break;
        }
        tv_cityType.setText(cityType);

        double middleTemp=0;
        String season=(String)choise_seasons.getSelectedItem();
        int pos = choise_seasons.getSelectedItemPosition();
        switch (pos){
            case 0:
                //зима
                middleTemp=(currentCity.decTemp+currentCity.janTemp+currentCity.febTemp)/3.0;
                break;
            case 1:
                //весна
                middleTemp=(currentCity.marTemp+currentCity.aprTemp+currentCity.mayTemp)/3.0;
                break;
            case 2:
                //лето
                middleTemp=(currentCity.junTemp+currentCity.julTemp+currentCity.augTemp)/3.0;
                break;
            case 3:
                //осень
                middleTemp=(currentCity.sepTemp+currentCity.octTemp+currentCity.decTemp)/3.0;
                break;
        }
        Celsium celsium = new Celsium(middleTemp);
        Fahrenheit fahrenheit = new Fahrenheit(celsium);
        Kelvin kelvin = new Kelvin(celsium);

        tv_AmountTempC.setText(celsium.toString());
        tv_AmountTempF.setText(fahrenheit.toString());
        tv_AmountTempK.setText(kelvin.toString());

        String message=String.format("Средняя температура за сезон (%s):\n%s,  %s,  %s",season, celsium.toString(), fahrenheit.toString(), kelvin.toString());
        Snackbar.make(tv_AmountTempK, message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //открываем настройки
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
