package com.coder.bagusiyoo.adapter;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.bagusiyoo.Detailtanaman;
import com.coder.bagusiyoo.R;
import com.coder.bagusiyoo.model.DiaryModel;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MyViewHolder>{
    List<DiaryModel> mDiaryList;

    public DiaryAdapter(List<DiaryModel> daftarDiary) {
        this.mDiaryList = daftarDiary;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_diary, parent, false);
        DiaryAdapter.MyViewHolder holder;
        holder = new MyViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder,final int position){
        holder.mHarike.setText("Hari ke "+mDiaryList.get(position).getHarike());
        holder.mMulai.setText("Start: "+mDiaryList.get(position).getMulaiTanam());
        holder.mTanaman.setText(mDiaryList.get(position).getNamaTanaman());
        holder.mPanen.setText("Estimasi Panen: "+mDiaryList.get(position).getPanen());
        holder.itemView.setOnClickListener(view -> {
            Intent mIntent = new Intent(view.getContext(), Detailtanaman.class);
//            mIntent.putExtra("idMapel",mMapelList.get(position).getId_pelajaran());
            view.getContext().startActivity(mIntent);
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("StaticFieldLeak")
        private final TextView mHarike,mMulai,mTanaman,mPanen;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mHarike = itemView.findViewById(R.id.txtHarike);
            mMulai = itemView.findViewById(R.id.txtMulai);
            mTanaman = itemView.findViewById(R.id.txtTanaman);
            mPanen = itemView.findViewById(R.id.txtPanen);
        }
    }

    @Override
    public int getItemCount () {
        return mDiaryList.size();
    }

    public void replaceData(List<DiaryModel> daftarDiary) {
        this.mDiaryList = daftarDiary;
        notifyDataSetChanged();
    }
}
