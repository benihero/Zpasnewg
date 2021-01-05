package com.ggf.zpasnew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggf.zpasnew.DetailKegiatanTk;
import com.ggf.zpasnew.Model.ResultKegiatan;
import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultAktifitas;

import java.util.List;

public class AdapterKegiatanTK extends RecyclerView.Adapter<AdapterKegiatanTK.ViewHolder> {
    private Context context;
    private List<ResultKegiatan> resultTK;

    public AdapterKegiatanTK(Context context, List<ResultKegiatan> resultTK) {
        this.context = context;
        this.resultTK = resultTK;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listkegiatantk, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultKegiatan resulttk = resultTK.get(position);
        holder.tanggal.setText(resulttk.getTanggalreal());
        holder.kegiatan.setText(resulttk.getAktifitas());


    }

    @Override
    public int getItemCount() {
        return resultTK.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal,kegiatan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggal = itemView.findViewById(R.id.TKtanggal);
            kegiatan = itemView.findViewById(R.id.TKkegiatan);


        }
    }
}
