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
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultSPK;

import java.util.List;

public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.ViewHolder> {
private Context context;
private List<ResultSPK> resultspk;




    public AdapterActivity(Context context, List<ResultSPK> resultspk) {
        this.context = context;
        this.resultspk = resultspk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listwelcomespk, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultSPK resultSPK = resultspk.get(position);
        holder.spkname.setText(resultSPK.getSPKName());
        holder.shift.setText(resultSPK.getShift());
        holder.mandor.setText(resultSPK.getNamaMandor());
        holder.tanggal.setText(resultSPK.getTanggalSPK());
        holder.linierspk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, InputSPK.class);
                i.putExtra("spkid",resultSPK.getSpkID());
                i.putExtra("mandor",resultSPK.getNamaMandor());
                i.putExtra("tanggal",resultSPK.getTanggalSPK());
                i.putExtra("shift",resultSPK.getShift());
                i.putExtra("kawil",resultSPK.getKawil());
                i.putExtra("pj",resultSPK.getPJ());
                i.putExtra("nama",resultSPK.getSPKName());


                context.startActivity(i);



            }
        });

    }

    @Override
    public int getItemCount() {
        return resultspk.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView spkname,tanggal,mandor,shift;
        LinearLayout linierspk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            spkname = itemView.findViewById(R.id.Wspkid);
            tanggal = itemView.findViewById(R.id.wtanggal);
            mandor = itemView.findViewById(R.id.wmandor);
            shift = itemView.findViewById(R.id.wshift);
            linierspk = itemView.findViewById(R.id.LinierSpk);
        }
    }
}
