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
import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultAktifitas;

import java.util.List;

public class AdapterTK extends RecyclerView.Adapter<AdapterTK.ViewHolder> {
    private Context context;
    private List<ResultTK> resultTK;

    public AdapterTK(Context context, List<ResultTK> resultTK) {
        this.context = context;
        this.resultTK = resultTK;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tenagakerja, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultTK resulttk = resultTK.get(position);
        holder.nama.setText(resulttk.getNama());
        holder.kit.setText(resulttk.getKIT());
        holder.liniertk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namatk = resulttk.getNama();
                Intent i = new Intent(context, DetailKegiatanTk.class);
                i.putExtra("namatk",namatk);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return resultTK.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,kit;
        LinearLayout liniertk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          nama = itemView.findViewById(R.id.TKnama);
          kit = itemView.findViewById(R.id.TKkit);
          liniertk = itemView.findViewById(R.id.Liniertk);

        }
    }
}
