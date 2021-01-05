package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Model.ResultJumlahTK;
import com.ggf.zpasnew.Model.ResultMandor;
import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.Model.ResultTKtemp;
import com.ggf.zpasnew.adapter.AdapterAddTK;
import com.ggf.zpasnew.adapter.AdapterTK;
import com.ggf.zpasnew.adapter.AdapterTKPilihan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PilihTk extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    AdapterAddTK adapterAddTK;
    AdapterTKPilihan adapterTKPilihan;
    ArrayList<ResultTK> resultTKS = new ArrayList<>();
    private List<ResultJumlahTK> resultJumlahTKS = new ArrayList<>();
    private List<ResultAktifitas> resultJumlahTK = new ArrayList<>();
    ArrayList<ResultTKtemp> resultTKtemps = new ArrayList<>();
    String URL = "http://192.168.43.38/spk/";
    private List<ResultMandor> resultmandor = new ArrayList<>();
    String spkID,nama,aktifitas,jenisupah;
    Button selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tk);
        recyclerView = findViewById(R.id.reyTkPilih);
        recyclerView1 = findViewById(R.id.reyTkPilihan);
        selesai = findViewById(R.id.selesaicheck);

        adapterAddTK = new AdapterAddTK(this, resultTKS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterAddTK);

        adapterTKPilihan = new AdapterTKPilihan(this, resultTKtemps);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(adapterTKPilihan);

        Bundle bundle = getIntent().getExtras();
        String namamandor = bundle.getString("namamandor");
        getMandor(namamandor);

        spkID = bundle.getString("spkid");
        nama = bundle.getString("namamandor");
        aktifitas = bundle.getString("aktifitas");
        jenisupah = bundle.getString("jenisupah");
        getSPKPilihan(aktifitas);
        String jumlahtk = bundle.getString("jumlahtk");
        getJumlahTKtemp(aktifitas);

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jumlah = Integer.parseInt(jumlahtk);
                int jumlahtktemp = Integer.parseInt(resultJumlahTKS.get(0).getJumlah());
                if(jumlahtktemp<jumlah){

                    Toast.makeText(getApplicationContext(),"Jumlah TK belum mencukupi",Toast.LENGTH_SHORT).show();
                } else if(jumlahtktemp>jumlah){

                    Toast.makeText(getApplicationContext(),"Jumlah TK Melebihi target",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(getApplicationContext(),"Jumlah TK Mencukupi",Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Bundle bundle = getIntent().getExtras();
        String namamandor = bundle.getString("namamandor");
        getMandor(namamandor);

        spkID = bundle.getString("spkid");
        nama = bundle.getString("namamandor");
        aktifitas = bundle.getString("aktifitas");
        getSPKPilihan(aktifitas);
    }

    public String getJenisupah() {
        return jenisupah;
    }

    public String getSpkID() {
        return spkID;
    }

    public String getNama() {
        return nama;
    }

    public String getAktifitas() {
        return aktifitas;
    }

    public void getSPKPilihan(String aktifitas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getDataTKtemp(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultTKtemps = (ArrayList<ResultTKtemp>) response.body().getResultTKtemp();
                    adapterTKPilihan = new AdapterTKPilihan(PilihTk.this, resultTKtemps);
                    recyclerView1.setAdapter(adapterTKPilihan);

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }

        });


    }

    public void getSPKbyMandor(String mandorid){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getTKmandor(mandorid);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultTKS = (ArrayList<ResultTK>) response.body().getResultTKadd();
                    adapterAddTK = new AdapterAddTK(PilihTk.this, resultTKS);
                    recyclerView.setAdapter(adapterAddTK);

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }

        });
    }

    public void getJumlahTKtarget(String aktifitas){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getJumlahTKtarget(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultJumlahTK = response.body().getResultJumlahTKtarget();
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void getMandor(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getmandorid(name);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();

                if(value.equals("1")){

                    resultmandor = response.body().getResultmandorID();
                    String mandorid = resultmandor.get(0).getIdMandor();

                    getSPKbyMandor(mandorid);

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }

    public void getJumlahTKtemp(String aktifitas){

        final Retrofit[] retrofit = {new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()};
        ApiData api = retrofit[0].create(ApiData.class);
        Call<Value> call = api.getJumlahtktemp(aktifitas);
        call.enqueue(new Callback<Value>() {

            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultJumlahTKS = response.body().getResultJumlahTKtemp();
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }

        });

    }


    }
