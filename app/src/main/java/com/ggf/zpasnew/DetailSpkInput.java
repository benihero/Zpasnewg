package com.ggf.zpasnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailSpkInput extends AppCompatActivity  {

    TextView spk, aktifitas, nama, tanggal, hko, hasil, jamkerja, grade, keterangan,totalupah;
    ImageView buttonupdate;
    String URL = "http://192.168.43.38/spk/";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spk_input);

        buttonupdate = findViewById(R.id.updatehasil);

        aktifitas = findViewById(R.id.DetailAktifitas);
        nama = findViewById(R.id.DetailNamaTK);
        tanggal = findViewById(R.id.DetailTanggalRealisasi);

        hko = findViewById(R.id.DetailHko);
        hasil = findViewById(R.id.DetailHasil);
        jamkerja = findViewById(R.id.DetailJamKerja);
        grade = findViewById(R.id.DetailGrade);
        keterangan = findViewById(R.id.DetailKeterangan);
        totalupah = findViewById(R.id.totalupah);

        Bundle bundle = getIntent().getExtras();
        aktifitas.setText(bundle.getString("aktifitas"));
        nama.setText(bundle.getString("namatk"));
        tanggal.setText(bundle.getString("tanggal"));

        hko.setText(bundle.getString("hko"));
        hasil.setText(bundle.getString("hasil"));
        jamkerja.setText(bundle.getString("jamkerja"));
        grade.setText(bundle.getString("grade"));
        keterangan.setText(bundle.getString("keterangan"));
        totalupah.setText(bundle.getString("upah"));

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialogBuilder = new AlertDialog.Builder(DetailSpkInput.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.editlayoutspk, null);

                final EditText editHKO = (EditText) dialogView.findViewById(R.id.edithko);
                editHKO.setText(bundle.getString("hko"));
                final EditText editHasil = (EditText) dialogView.findViewById(R.id.edithasil);
                editHasil.setText(bundle.getString("hasil"));
                final EditText editjam = (EditText) dialogView.findViewById(R.id.editJamKerja);
                editjam.setText(bundle.getString("jamkerja"));
                Button button1 = (Button) dialogView.findViewById(R.id.buttonCancel);
                Button button2 = (Button) dialogView.findViewById(R.id.buttonSave);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // DO SOMETHINGS
                        String id = bundle.getString("spkid");
                        updateDataInput(id,editHKO.getText().toString(),editHasil.getText().toString(),editjam.getText().toString());
                        dialogBuilder.dismiss();
                        finish();


                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }
        });

    }

    public void updateDataInput(String id,String hko,String hasil, String jamkerja){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.updatedatainput(id,hko,hasil,jamkerja);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if(value.equals("1")){
                    Toast.makeText(getApplicationContext(),"Data Berhasil di Update",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Data Gagal di Update",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
            }
        });
    }
}