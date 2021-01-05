package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.ggf.zpasnew.Model.ResultSPK;
import com.ggf.zpasnew.adapter.AdapterActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpkWelcome extends AppCompatActivity {

    TextView tanggal;
    RecyclerView recyclerView;
    AdapterActivity adapterActivity;
    List<ResultSPK> resultSPKS = new ArrayList<>();
    String URL = "http://192.168.43.38/spk/";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spk_welcome);
        recyclerView = findViewById(R.id.reyspk);

        tanggal = findViewById(R.id.tanggalcurrent);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        tanggal.setText(date);

        adapterActivity = new AdapterActivity(this, resultSPKS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterActivity);
        getDataSPK();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void getDataSPK(){

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading..");
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.Spklist();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if(value.equals("1")){
                    resultSPKS = response.body().getResult();
                    adapterActivity = new AdapterActivity(SpkWelcome.this, resultSPKS);
                    recyclerView.setAdapter(adapterActivity);
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }
}