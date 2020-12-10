package com.ggf.zpasnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailSpkInput extends AppCompatActivity {

    TextView spk, aktifitas, nama, tanggal, hko, hasil, jamkerja, grade, keterangan;
    ImageView buttonupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spk_input);

        buttonupdate = findViewById(R.id.updatehasil);

        spk = findViewById(R.id.DetailSPKID);
        aktifitas = findViewById(R.id.DetailAktifitas);
        nama = findViewById(R.id.DetailNamaTK);
        tanggal = findViewById(R.id.DetailTanggalRealisasi);

        hko = findViewById(R.id.DetailHko);
        hasil = findViewById(R.id.DetailHasil);
        jamkerja = findViewById(R.id.DetailJamKerja);
        grade = findViewById(R.id.DetailGrade);
        keterangan = findViewById(R.id.DetailKeterangan);

        Bundle bundle = getIntent().getExtras();
        spk.setText(bundle.getString("spkid"));
        aktifitas.setText(bundle.getString("aktifitas"));
        nama.setText(bundle.getString("namatk"));
        tanggal.setText(bundle.getString("tanggal"));

        hko.setText(bundle.getString("hko"));
        hasil.setText(bundle.getString("hasil"));
        jamkerja.setText(bundle.getString("jamkerja"));
        grade.setText(bundle.getString("grade"));
        keterangan.setText(bundle.getString("keterangan"));

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialogBuilder = new AlertDialog.Builder(DetailSpkInput.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.editlayoutspk, null);

//                final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
//                Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
//                Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

//                button2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialogBuilder.dismiss();
//                    }
//                });
//                button1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // DO SOMETHINGS
//                        dialogBuilder.dismiss();
//                    }
//                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();

            }
        });


    }
}

