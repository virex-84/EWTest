package com.virex.ewtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.virex.ewtest.entity.City;
import com.virex.ewtest.ui.CitiesAdapter;
import com.virex.ewtest.ui.CitiesEditDialog;

import java.util.List;

public class SettingsActivity extends AppCompatActivity implements CitiesEditDialog.DialogListener {
    private int ADD_CITY = 1;
    private int EDIT_CITY = 2;

    MyViewModel model;
    RecyclerView recyclerView;
    CitiesAdapter citiesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        model = ViewModelProviders.of(this).get(MyViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesAdapter = new CitiesAdapter(this, new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(City city) {
                CitiesEditDialog citiesEditDialog = new CitiesEditDialog(SettingsActivity.this,EDIT_CITY, city);
                if (getSupportFragmentManager() != null) {
                    citiesEditDialog.show(getSupportFragmentManager(), "");
                }
            }

            @Override
            public void onRemoveClick(final City city) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle(SettingsActivity.this.getTitle());
                dialog.setMessage(getString(R.string.delete_city_dialog_message));
                dialog.setPositiveButton(getString(R.string.action_delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //удаляем
                        model.deleteCity(city);
                    }
                }).setNegativeButton(getString(R.string.action_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }});
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });
        recyclerView.setAdapter(citiesAdapter);

        model.getAllCites().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cities) {
                citiesAdapter.submitList(cities);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CitiesEditDialog citiesEditDialog = new CitiesEditDialog(SettingsActivity.this,ADD_CITY, null);
                if (getSupportFragmentManager() != null) {
                    citiesEditDialog.show(getSupportFragmentManager(), "");
                }
            }
        });
    }

    @Override
    public void onClick(Bundle data, int request, City city) {
        if (request==ADD_CITY)
            model.addCity(city);
        if (request==EDIT_CITY)
            model.updateCity(city);
    }
}
