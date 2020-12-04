package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailSPK extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView hasile;
    List<ResultInputSPK> resultInputSPKS = new ArrayList<>();
    private List<ResultInputSPK> Hasil = new ArrayList<>();
    AdapterDataSpkInput adapterDataSpkInput;
    String URL = "http://192.168.43.38/spk/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_s_p_k);
        recyclerView = findViewById(R.id.ReyHasilSpk);
        hasile = findViewById(R.id.HasilEfektif);
        getDataSPK();
        adapterDataSpkInput = new AdapterDataSpkInput(this,resultInputSPKS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(adapterDataSpkInput);
        getCountAktifitas("Panen Nanas");


    }

    public void getDataSPK(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getSpkInput();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                 String value = response.body().getValue();

                if (value.equals("1")) {
                    resultInputSPKS = response.body().getResultSPK();
                    adapterDataSpkInput = new AdapterDataSpkInput(DetailSPK.this, resultInputSPKS);
                    recyclerView.setAdapter(adapterDataSpkInput);

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });



    }
    public void getCountAktifitas(String aktifitas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getCountaktifitas(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();

                if(value.equals("1")){

                    Hasil = response.body().getResultAktifitass();
                    int hasil =0;
                    for (int i =0; i<Hasil.size(); i++){
                        int nilai = Integer.parseInt(Hasil.get(i).getHasil());
                        hasil+=nilai;


                    }

                    String hasill = String.valueOf(hasil);
                    hasile.setText(hasill);


                }


            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });



    }
}