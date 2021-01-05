package com.ggf.zpasnew.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggf.zpasnew.ApiData;
import com.ggf.zpasnew.DetailSPK;
import com.ggf.zpasnew.DetailSpkInput;
import com.ggf.zpasnew.Model.ResultSumTk;
import com.ggf.zpasnew.Model.ResultSummary;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Summary;
import com.ggf.zpasnew.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterSumTK extends RecyclerView.Adapter<AdapterSumTK.ViewHolder> {
    private Context context;
    private List<ResultSumTk> resultSumTks;


    public AdapterSumTK(Context context, List<ResultSumTk> resultSumTks) {
        this.context = context;
        this.resultSumTks = resultSumTks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_summarytk, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultSumTk resultsumtk = resultSumTks.get(position);
        holder.nama.setText(resultsumtk.getNamaTK());
        holder.hko.setText(resultsumtk.getTotalHko());
        holder.hasil.setText(resultsumtk.getTotaljam());


    }

    @Override
    public int getItemCount() {
        return resultSumTks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, hko, hasil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.Sumnama);
            hko = itemView.findViewById(R.id.sumhko);
            hasil = itemView.findViewById(R.id.sumhasil);

        }
    }


}
