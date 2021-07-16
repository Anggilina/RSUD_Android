package com.example.project_uts.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_uts.R;

public class PasienViewHolder extends RecyclerView.ViewHolder {
    public ImageView avatar;
    public TextView nama;
    public TextView alamat;
    public ImageView delete;
    public ImageView edit;

    public PasienViewHolder(View itemView){
        super(itemView);
        avatar = (ImageView)itemView.findViewById(R.id.avatar);
        nama = (TextView)itemView.findViewById(R.id.nama);
        alamat = (TextView)itemView.findViewById(R.id.alamat);
    }
}
