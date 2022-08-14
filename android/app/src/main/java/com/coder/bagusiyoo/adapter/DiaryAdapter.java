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
    public DiaryAdapter.ClickListener clickListener;

    public DiaryAdapter(List<DiaryModel> mDiaryList, ClickListener clickListener) {
        this.mDiaryList = mDiaryList;
        this.clickListener = clickListener;
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
    public void onBindViewHolder (@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position){
        holder.mHarike.setText("Hari ke "+mDiaryList.get(position).getHarike());
        holder.mMulai.setText("Mulai: "+mDiaryList.get(position).getMulaiTanam());
        holder.mTanaman.setText(mDiaryList.get(position).getNamaTanaman());
        holder.mPanen.setText("Selesai: "+mDiaryList.get(position).getPanen());
        holder.mJudultebal.setText(mDiaryList.get(position).getJudul());
        holder.mHapus.setOnClickListener(v ->
                clickListener.dataItemDiary(mDiaryList.get(position).getIdDiary(),"Data berhasil dihapus!")
        );
        holder.itemView.setOnClickListener(view -> {
            Intent mIntent = new Intent(view.getContext(), Detailtanaman.class);
            mIntent.putExtra("idDiary",mDiaryList.get(position).getIdDiary());
            mIntent.putExtra("idTanaman",mDiaryList.get(position).getIdTanaman());
            view.getContext().startActivity(mIntent);
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("StaticFieldLeak")
        private final TextView mHarike,mMulai,mTanaman,mPanen,mHapus,mJudultebal;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mHarike = itemView.findViewById(R.id.txtHarike);
            mMulai = itemView.findViewById(R.id.txtMulai);
            mTanaman = itemView.findViewById(R.id.txtTanaman);
            mPanen = itemView.findViewById(R.id.txtPanen);
            mHapus = itemView.findViewById(R.id.txt_hapus);
            mJudultebal = itemView.findViewById(R.id.judultebal);
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

    public interface ClickListener{
        void dataItemDiary(String idDiary, String msg);
    }
}
