package com.ggf.zpasnew.adapter;

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

public class AdapterAddTK extends RecyclerView.Adapter<AdapterAddTK.ViewHolder> {
    private Context context;
    private List<ResultTK> resultTK;
    String URL = "http://192.168.43.38/spk/";

    public AdapterAddTK(Context context, List<ResultTK> resultTK) {
        this.context = context;
        this.resultTK = resultTK;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listtkpilihan, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultTK resulttk = resultTK.get(position);
        holder.namatk.setText(resulttk.getNama());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aktifitas = ((PilihTk) context).getAktifitas();
                String spkID = ((PilihTk) context).getSpkID();
                String jenisupah = ((PilihTk) context).getJenisupah();
                addTkTemp(aktifitas, jenisupah, spkID, resulttk.getNama());
                ((PilihTk) context).recreate();

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultTK.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namatk;
        ImageView add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namatk = itemView.findViewById(R.id.NamaTKlist);
            add = itemView.findViewById(R.id.addTKlist);


        }
    }

    public void addTkTemp(String aktifitas, String JenisUpah, String spkid, String namatk) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.savetktemp(aktifitas, JenisUpah, spkid, namatk);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();

                if (value.equals("2")) {
                    Toast.makeText(context, "Data sudah ada", Toast.LENGTH_SHORT).show();

                } else if (value.equals("3")) {
                    Toast.makeText(context, " TK sudah ditambah,Jenis Upah Harus Sama", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(context, "TK berhasil ditambah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }
}