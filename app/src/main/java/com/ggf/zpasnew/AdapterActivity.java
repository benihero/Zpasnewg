package com.ggf.zpasnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.ViewHolder> {
private Context context;
private List<ResultAktifitas> resultAktifitas;

    public AdapterActivity(Context context, List<ResultAktifitas> resultAktifitas) {
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
        holder.jmlhtk.setText(resultAktifitas.get(position).getJumlahTK());
        holder.satuan.setText(resultAktifitas.get(position).getSatuanHasil());

    }

    @Override
    public int getItemCount() {
        return resultAktifitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aktifitas,satuan,lokasi,thasil,lhasil,jmlhtk,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aktifitas = itemView.findViewById(R.id.ListAktifitas);
            satuan = itemView.findViewById(R.id.ListSatuan);
            lokasi = itemView.findViewById(R.id.ListLokasi);
            thasil = itemView.findViewById(R.id.ListThasil);
            lhasil = itemView.findViewById(R.id.ListLhasil);
            jmlhtk = itemView.findViewById(R.id.ListJmlhTK);
            status = itemView.findViewById(R.id.ListStatus);
        }
    }
}
