package com.virex.ewtest.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.virex.ewtest.R;
import com.virex.ewtest.entity.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onEditClick(City city);
        void onRemoveClick(City city);
    }

    LayoutInflater inflater;
    private List<City> items=new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public CitiesAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.onItemClickListener=onItemClickListener;
    }

    public void submitList(List<City> data){
        this.items.clear();
        this.items.addAll(data);
        //уведомляем себя об изменении данных для перерисовки
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.city_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        City city = items.get(i);
        viewHolder.tv_city.setText(city.toString());
        viewHolder.tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onEditClick(items.get(i));
            }
        });
        viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onRemoveClick(items.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_city;
        TextView tv_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}
