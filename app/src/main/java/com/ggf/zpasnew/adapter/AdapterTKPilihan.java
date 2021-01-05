package com.ggf.zpasnew.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggf.zpasnew.ApiData;
import com.ggf.zpasnew.DetailKegiatanTk;
import com.ggf.zpasnew.InputSPK;
import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.Model.ResultTKtemp;
import com.ggf.zpasnew.PilihTk;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterTKPilihan extends RecyclerView.Adapter<AdapterTKPilihan.ViewHolder> {
    private Context context;
    private List<ResultTKtemp> resultTKtemps;
    String URL = "http://192.168.43.38/spk/";

    public AdapterTKPilihan(Context context, List<ResultTKtemp> resultTKtemps) {
        this.context = context;
        this.resultTKtemps = resultTKtemps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listtkadd, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultTKtemp resulttk = resultTKtemps.get(position);
        holder.namatk.setText(resulttk.getNamaTK());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(resulttk.getId());
                ((PilihTk)context).recreate();
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultTKtemps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namatk;
        ImageView hapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namatk = itemView.findViewById(R.id.namatkpilih);
            hapus = itemView.findViewById(R.id.hapustk);


        }
    }

    public void delete(String id){
        ProgressDialog progressDialog ;
        progressDialog = ProgressDialog.show(context ,null, "Memuat..");
        progressDialog.setCancelable(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.deletetk(id);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                progressDialog.dismiss();
                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }

//    public void addTkTemp(String aktifitas, String spkid, String namatk) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiData api = retrofit.create(ApiData.class);
//        Call<Value> call = api.savetktemp(aktifitas, spkid, namatk);
//        call.enqueue(new Callback<Value>() {
//            @Override
//            public void onResponse(Call<Value> call, Response<Value> response) {
//                Toast.makeText(context, "TK berhasil dipilih", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Value> call, Throwable t) {
//
//            }
//        });
//
//    }
}
