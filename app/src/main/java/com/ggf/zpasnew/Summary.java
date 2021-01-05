package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.ggf.zpasnew.Model.ResultSumTk;
import com.ggf.zpasnew.Model.ResultSummary;
import com.ggf.zpasnew.adapter.AdapterDataSpkInput;
import com.ggf.zpasnew.adapter.AdapterSPK;
import com.ggf.zpasnew.adapter.AdapterSumTK;
import com.ggf.zpasnew.adapter.AdapterSummary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Summary extends AppCompatActivity {
    String URL = "http://192.168.43.38/spk/";
    AdapterSummary adapterSummary;
    AdapterSumTK adapterSumTK ;
    List<ResultSumTk> resultSum  = new ArrayList<>();
    List<ResultSummary> resultSummaries = new ArrayList<>();
    RecyclerView recyclerView,recyclerView2;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary2);
        recyclerView = findViewById(R.id.reySummary);
        recyclerView2 = findViewById(R.id.reySummaryTk);

        adapterSummary = new AdapterSummary(this, resultSummaries);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSummary);
        Bundle bundle = getIntent().getExtras();
        String idspk =bundle.getString("spkid");
        loadAktifitasSummary(idspk);
        loadSumTK(idspk);


        adapterSumTK = new AdapterSumTK(this, resultSum);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapterSumTK);





    }


    public void loadAktifitasSummary(String data) {

        ProgressDialog progressDialog ;
        progressDialog = ProgressDialog.show(this, "Waiting...", "Loading..");
        progressDialog.setCancelable(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getAllSummary(data);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultSummaries = response.body().getResultAllSummary();
                    adapterSummary = new AdapterSummary(Summary.this, resultSummaries);
                    recyclerView.setAdapter(adapterSummary);
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void loadSumTK(String id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getSumTK(id);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultSum = response.body().getResultSumTk();
                    adapterSumTK = new AdapterSumTK(Summary.this, resultSum);
                    recyclerView2.setAdapter(adapterSumTK);

                }


        }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void scrollRefresh(){

        progressDialog.setMessage("Mengambil Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Bundle bundle = getIntent().getExtras();
        String idspk =bundle.getString("spkid");
        loadAktifitasSummary(idspk);

    }
}