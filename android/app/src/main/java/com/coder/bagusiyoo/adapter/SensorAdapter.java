package com.coder.bagusiyoo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.bagusiyoo.R;
import com.coder.bagusiyoo.model.LabModel;
import com.coder.bagusiyoo.sensor.Detailsensor;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.MySensorHolder>{
    public List<LabModel> mSensorList;

    public SensorAdapter(List<LabModel> mSensorList) {
        this.mSensorList = mSensorList;
    }

    @NonNull
    @Override
    public SensorAdapter.MySensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_menu_sensor, parent, false);
        SensorAdapter.MySensorHolder holder;
        holder = new SensorAdapter.MySensorHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull SensorAdapter.MySensorHolder holder, final int position){
        holder.mJudul.setText(mSensorList.get(position).getNamaLab());
        holder.itemView.setOnClickListener(view -> {
            Intent mIntent = new Intent(view.getContext(), Detailsensor.class);
            mIntent.putExtra("idLab",mSensorList.get(position).getIdLab());
            mIntent.putExtra("namaLab",mSensorList.get(position).getNamaLab());
            view.getContext().startActivity(mIntent);
        });
    }

    public int getItemCount () {
        return mSensorList.size();
    }

    public void replaceData(List<LabModel> dataSensor) {
        this.mSensorList = dataSensor;
        notifyDataSetChanged();
    }

    public static class MySensorHolder extends RecyclerView.ViewHolder {
        private final TextView mJudul;

        MySensorHolder(@NonNull View itemView) {
            super(itemView);
            mJudul = itemView.findViewById(R.id.txt_judul);
        }
    }
}
