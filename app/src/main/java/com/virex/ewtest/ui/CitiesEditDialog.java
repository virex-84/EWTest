package com.virex.ewtest.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.virex.ewtest.R;
import com.virex.ewtest.common.CityFactory;
import com.virex.ewtest.common.ICity;
import com.virex.ewtest.databinding.CitiesEditDialogBinding;
import com.virex.ewtest.entity.City;


@SuppressLint("ValidFragment")
public class CitiesEditDialog extends DialogFragment {

    public interface DialogListener {
        void onClick(Bundle data, int request, City city);
    }


    City city;
    DialogListener mListener;
    int request;
    CitiesEditDialogBinding binding;

    @SuppressLint("ValidFragment")
    public CitiesEditDialog(DialogListener caller, int request, City city){
        this.city=city;
        this.request=request;
        this.mListener=caller;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (city==null)
            city = (City) CityFactory.newCity("", ICity.CityType.middle);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),  R.layout.cities_edit_dialog, null, false);
        binding.setCity(city);
        binding.choiseCityType.setSelection(city.type.ordinal());


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.app_name))
                .setCancelable(false)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.action_ok,null)
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        AlertDialog dialog = (AlertDialog)getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data= new Bundle();

                city.name=binding.tvName.getText().toString();
                city.type= ICity.CityType.values()[binding.choiseCityType.getSelectedItemPosition()];
                city.janTemp= getValue(binding.janTemp);
                city.febTemp= getValue(binding.febTemp);
                city.marTemp= getValue(binding.marTemp);
                city.aprTemp= getValue(binding.aprTemp);
                city.mayTemp= getValue(binding.mayTemp);
                city.junTemp= getValue(binding.junTemp);
                city.julTemp= getValue(binding.julTemp);
                city.augTemp= getValue(binding.augTemp);
                city.sepTemp= getValue(binding.sepTemp);
                city.octTemp= getValue(binding.octTemp);
                city.nowTemp= getValue(binding.nowTemp);
                city.decTemp= getValue(binding.decTemp);

                if (city.name.isEmpty()){
                    binding.tvName.setError(getString(R.string.text_is_empty));
                    return;
                }

                //вызываем обработчик и закрываем диалог
                dismiss();
                mListener.onClick(data, request, city);
            }
        });
    }

    private float getValue(EditText edittext){
        if (edittext.getText().toString().isEmpty()){
            return 0;
        } else
            return (float) Double.parseDouble(edittext.getText().toString());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //сохраняем состояние при перевороте экрана
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
