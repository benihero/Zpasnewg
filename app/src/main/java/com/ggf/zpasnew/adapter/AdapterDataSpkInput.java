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

import com.ggf.zpasnew.DetailSPK;
import com.ggf.zpasnew.DetailSpkInput;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultInputSPK;

import java.util.List;

public class AdapterDataSpkInput extends RecyclerView.Adapter<AdapterDataSpkInput.ViewHolder> {
    private Context context;
    private List<ResultInputSPK> resultSpk;

    public AdapterDataSpkInput(Context context, List<ResultInputSPK> resultSpk) {
        this.context = context;
        this.resultSpk = resultSpk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hasil_tk, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aktifitass.setText(resultSpk.get(position).getAktifitas());
        holder.nama.setText(resultSpk.get(position).getNamaTk());
        holder.grade.setText(resultSpk.get(position).getGreat());
        holder.hko.setText(resultSpk.get(position).getHKO());
        holder.hasil.setText(resultSpk.get(position).getHasil());
        holder.linierid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ResultInputSPK resultInputSPK = resultSpk.get(position);
                Intent i = new Intent(context, DetailSpkInput.class);
                i.putExtra("spkid",resultInputSPK.getId());
                i.putExtra("namatk",resultInputSPK.getNamaTk());
                i.putExtra("aktifitas",resultInputSPK.getAktifitas());
                i.putExtra("tanggal",resultInputSPK.getTanggalRealisasi());
                i.putExtra("hko",resultInputSPK.getHKO());
                i.putExtra("hasil",resultInputSPK.getHasil());
                i.putExtra("jamkerja",resultInputSPK.getJamKerja());
                i.putExtra("grade",resultInputSPK.getGreat());
                i.putExtra("keterangan",resultInputSPK.getKeterangan());
                i.putExtra("upah",resultInputSPK.getUpah());
                context.startActivity(i);






            }
        });


    }

    @Override
    public int getItemCount() {
        return resultSpk.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView aktifitass, nama, hko, hasil, grade;
        LinearLayout linierid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aktifitass = itemView.findViewById(R.id.AktifitasInputSPK);
            nama = itemView.findViewById(R.id.NamaInputSPK);
            hko = itemView.findViewById(R.id.HkoInputSPK);
            hasil = itemView.findViewById(R.id.HasilInputSPK);
            grade = itemView.findViewById(R.id.GradeInputSPK);
            linierid = itemView.findViewById(R.id.LinierIDKerjaHasil);

        }
    }
}
