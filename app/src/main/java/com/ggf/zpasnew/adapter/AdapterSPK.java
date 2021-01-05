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

import com.ggf.zpasnew.InputSPK;
import com.ggf.zpasnew.PilihTk;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultAktifitas;

import java.util.List;

public class AdapterSPK extends RecyclerView.Adapter<AdapterSPK.ViewHolder> {
    private Context context;
    private List<ResultAktifitas> resultAktifitas;

    public AdapterSPK(Context context, List<ResultAktifitas> resultAktifitas) {
        this.context = context;
        this.resultAktifitas = resultAktifitas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_spk, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultAktifitas resultAktifit = resultAktifitas.get(position);
        holder.aktifitas.setText(resultAktifitas.get(position).getAktifitasName());
        holder.lokasi.setText(resultAktifit.getLokasi());
        holder.status.setText(resultAktifit.getStatus());
        holder.lhasil.setText(resultAktifit.getLhasil());
        holder.thasil.setText(resultAktifit.getThasil());
        holder.jenisupah.setText(resultAktifit.getJenisUpah());
        holder.jmlhtk.setText(resultAktifitas.get(position).getJumlahTK());
        holder.satuan.setText(resultAktifitas.get(position).getSatuanHasil());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mandor = ((InputSPK)context).getMandor().getText().toString();
                Intent intent = new Intent(context, PilihTk.class);
                intent.putExtra("namamandor",mandor);
                intent.putExtra("spkid",resultAktifit.getId());
                intent.putExtra("aktifitas",resultAktifit.getAktifitasName());
                intent.putExtra("jenisupah",resultAktifit.getJenisUpah());
                intent.putExtra("jumlahtk",resultAktifit.getJumlahTK());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return resultAktifitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aktifitas,satuan,lokasi,thasil,lhasil,jmlhtk,status,jenisupah;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aktifitas = itemView.findViewById(R.id.ListAktifitas);
            jenisupah = itemView.findViewById(R.id.ListJenisUpah);
            satuan = itemView.findViewById(R.id.ListSatuan);
            lokasi = itemView.findViewById(R.id.ListLokasi);
            thasil = itemView.findViewById(R.id.ListThasil);
            lhasil = itemView.findViewById(R.id.ListLhasil);
            jmlhtk = itemView.findViewById(R.id.ListJmlhTK);
            status = itemView.findViewById(R.id.ListStatus);
            linearLayout = itemView.findViewById(R.id.listaktifitaslinier);

        }
    }
}
