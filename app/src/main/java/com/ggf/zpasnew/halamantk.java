package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.adapter.AdapterDataSpkInput;
import com.ggf.zpasnew.adapter.AdapterTK;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class halamantk extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterTK adapterTK;
    ArrayList<ResultTK> resultTKS = new ArrayList<>();
    String URL = "http://192.168.43.38/spk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamantk);
        recyclerView = findViewById(R.id.reyTK);


        adapterTK = new AdapterTK(this, resultTKS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterTK);
        getDataSPk();
    }

    public void getDataSPk(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getDataSpk();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if(value.equals("1")){
                    resultTKS = (ArrayList<ResultTK>) response.body().getResultTKData();
                    adapterTK = new AdapterTK(halamantk.this, resultTKS);
                    recyclerView.setAdapter(adapterTK);


                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}