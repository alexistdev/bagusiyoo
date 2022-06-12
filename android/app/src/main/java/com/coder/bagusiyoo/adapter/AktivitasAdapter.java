package com.coder.bagusiyoo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.bagusiyoo.R;
import com.coder.bagusiyoo.model.AktivitasModel;
import com.coder.bagusiyoo.model.DiaryModel;

import java.util.List;

public class AktivitasAdapter extends RecyclerView.Adapter<AktivitasAdapter.MyViewHolder>{
    List<AktivitasModel> mAktivitasList;

    public AktivitasAdapter(List<AktivitasModel> mAktivitasList) {
        this.mAktivitasList = mAktivitasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_aktivitas, parent, false);
        AktivitasAdapter.MyViewHolder holder;
        holder = new MyViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder,final int position){
        holder.mAktivitas.setText(mAktivitasList.get(position).getNamaAktivitas());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("StaticFieldLeak")
        private final TextView mAktivitas;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mAktivitas = itemView.findViewById(R.id.txtAktivitas);
        }
    }



    public int getItemCount () {
        return mAktivitasList.size();
    }

    public void replaceData(List<AktivitasModel> daftarAktivitas) {
        this.mAktivitasList = daftarAktivitas;
        notifyDataSetChanged();
    }

}
