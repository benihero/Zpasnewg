package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.ggf.zpasnew.Model.ResultKegiatan;
import com.ggf.zpasnew.adapter.AdapterDataSpkInput;
import com.ggf.zpasnew.adapter.AdapterKegiatanTK;
import com.ggf.zpasnew.adapter.AdapterTK;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailKegiatanTk extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ResultKegiatan> resultKegiatans = new ArrayList<>();
    AdapterKegiatanTK adapterTKkegiatan;
    String URL = "http://192.168.43.38/spk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan_tk);
        recyclerView = findViewById(R.id.reyTkKegiatan);


        adapterTKkegiatan = new AdapterKegiatanTK(this, resultKegiatans);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterTKkegiatan);

        Bundle bundle = getIntent().getExtras();
        String namatk = bundle.getString("namatk");
        getDataKegiatan(namatk);

    }

    public void getDataKegiatan(String nama){
        ProgressDialog progressDialog ;
        progressDialog = ProgressDialog.show(this, "Waiting...", "Loading..");
        progressDialog.setCancelable(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getKegiatantk(nama);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                 if(value.equals("1")){

                    resultKegiatans = response.body().getResultKegiatan();
                    adapterTKkegiatan = new AdapterKegiatanTK(DetailKegiatanTk.this, resultKegiatans);
                    recyclerView.setAdapter(adapterTKkegiatan);
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }
}