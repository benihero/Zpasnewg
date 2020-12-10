package com.ggf.zpasnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Model.ResultDataRealAktifitas;
import com.ggf.zpasnew.Model.ResultInputSPK;
import com.ggf.zpasnew.Model.ResultJumlahTK;
import com.ggf.zpasnew.adapter.AdapterDataSpkInput;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailSPK extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView hasile,luashasil,jmlhtk;
    EditText DetailAktifitas;
    ImageView editluashasil;

    ListPopupWindow listPopupWindow;
    List<ResultInputSPK> resultInputSPKS = new ArrayList<>();
    private List<ResultInputSPK> Hasil = new ArrayList<>();
    private List<ResultAktifitas> spkName = new ArrayList<>();
    private List<ResultAktifitas> resultsAllaktifitas = new ArrayList<>();
    private List<ResultJumlahTK> tk = new ArrayList<>();
    private List<ResultDataRealAktifitas> realaktifitas = new ArrayList<>();
    AdapterDataSpkInput adapterDataSpkInput;
    String URL = "http://192.168.43.38/spk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_s_p_k);
        recyclerView = findViewById(R.id.ReyHasilSpk);
        hasile = findViewById(R.id.HasilEfektif);
        luashasil = findViewById(R.id.LuasHasil);
        editluashasil = findViewById(R.id.EditLuasHasil);
        jmlhtk = findViewById(R.id.InputJumlahTK);


        getDataSPK();
        adapterDataSpkInput = new AdapterDataSpkInput(this, resultInputSPKS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        DetailAktifitas = findViewById(R.id.InputDetailAktifitas);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterDataSpkInput);

        editluashasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpMenuEditLuasHasil();
            }
        });


        DetailAktifitas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FilterAktifitas(DetailAktifitas.getText().toString());
                getCountAktifitas(DetailAktifitas.getText().toString());
                getLuasHasil(DetailAktifitas.getText().toString());
                getJumlahTK(DetailAktifitas.getText().toString());
                getIdAktifitas(DetailAktifitas.getText().toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getNamaSpk();

        DetailAktifitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });

    }


    public void popUpMenuEditLuasHasil(){

        final EditText taskEditText = new EditText(DetailSPK.this);
        taskEditText.setTextColor(getColor(R.color.black));
        taskEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        if(resultsAllaktifitas.isEmpty()){
            Toast.makeText(DetailSPK.this,"Data Sedang kosong",Toast.LENGTH_SHORT).show();
        } else {
            taskEditText.setText(resultsAllaktifitas.get(0).getLhasil());
            AlertDialog dialog = new AlertDialog.Builder(DetailSPK.this, R.style.Theme_MaterialComponents_Light_Dialog_MinWidth)
                    .setTitle("Luas Hasil")
                    .setMessage("Edit Luas Hasil")
                    .setView(taskEditText)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
        }
    }



    public void getDataSPK() {

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

    public void updateAktifitas(String id,String hasil,String hse,String jmlhtk){



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        int idt = Integer.parseInt(id);
        Call<Value> call = apiData.UpdateAktifitas(idt,hasil,hse,jmlhtk);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                       }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void getIdAktifitas(String aktifitas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getDataReal(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();

                if(value.equals("1")){

                    realaktifitas = response.body().getResultRealAktifitas();
                    String idd = realaktifitas.get(0).getId();

                    updateAktifitas(idd,luashasil.getText().toString(),hasile.getText().toString(),jmlhtk.getText().toString());






                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }

    public void getCountAktifitas(String aktifitas) {

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

                if (value.equals("1")) {

                    Hasil = response.body().getResultAktifitass();
                    int hasil = 0;
                    for (int i = 0; i < Hasil.size(); i++) {
                        int nilai = Integer.parseInt(Hasil.get(i).getHasil());
                        hasil += nilai;


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

    public void getNamaSpk() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getNamaSpk();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {
                    spkName = response.body().getResultAktifitas();

                    ArrayList<String> Aktifitas = new ArrayList<>();
                    for (int i = 0; i < spkName.size(); i++) {

                        Aktifitas.add(spkName.get(i).getAktifitasName());

                    }

                    listPopupWindow = new ListPopupWindow(DetailSPK.this);
                    ArrayAdapter adapter = new ArrayAdapter<>(DetailSPK.this, R.layout.list_grade, R.id.tv_elementgrade, Aktifitas);
                    listPopupWindow.setAnchorView(DetailAktifitas); //this let as set the popup below the EditText
                    listPopupWindow.setAdapter(adapter);
                    listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DetailAktifitas.setText(spkName.get(position).getAktifitasName());//we set the selected element in the EditText
                            listPopupWindow.dismiss();
                        }
                    });


                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void FilterAktifitas(String aktifitas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.filterAktifitas(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();

                if(value.equals("1")){

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

    public void getLuasHasil(String aktifitas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getluasHasil(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();

                if(value.equals("1")){

                    resultsAllaktifitas = response.body().getResultAktifitas();
                    luashasil.setText(resultsAllaktifitas.get(0).getLhasil());



                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void getJumlahTK(String aktifitas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.getJumlahTK(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if(value.equals("1")){
                tk = response.body().getJmlhTK();
                jmlhtk.setText(tk.get(0).getJumlah());



                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }
}