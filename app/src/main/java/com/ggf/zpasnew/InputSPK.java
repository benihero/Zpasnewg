package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Model.ResultJumlahTK;
import com.ggf.zpasnew.Model.ResultMandor;
import com.ggf.zpasnew.Model.ResultSPK;
import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.Model.ResultTargetHasil;
import com.ggf.zpasnew.adapter.AdapterSPK;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InputSPK extends AppCompatActivity {

    EditText spk, mandor, kawil, pj, tanggalreal, Aktifitas, nama, KIT, Grade, hasil, hko, jam, keterangan;
    TextView Tanggalspk, shift;

    private ListPopupWindow statusPopupList;
    private ListPopupWindow statusPopupList1;
    private ListPopupWindow statusPopupList2;
    private ListPopupWindow statusPopupList3;
    private AdapterSPK viewAdapter;
    RecyclerView recyclerView;
    ImageView buttonpick;
    ProgressDialog progressDialog;
    DatePickerDialog picker;
    String URL = "http://192.168.43.38/spk/";
    private List<ResultSPK> results = new ArrayList<>();
    private List<ResultTK> resultstk = new ArrayList<>();
    private List<ResultJumlahTK> resultJumlahTKS = new ArrayList<>();
    private List<ResultTargetHasil> resultTargetHasils = new ArrayList<>();
    private List<ResultTK> resultskit = new ArrayList<>();
    private List<ResultMandor> resultmandor = new ArrayList<>();
    private List<ResultAktifitas> resultsaktifitas = new ArrayList<>();
    private List<ResultAktifitas> resultJenisUpah = new ArrayList<>();
    private List<ResultAktifitas> resultsAllaktifitas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_s_p_k);
        buttonpick = findViewById(R.id.buttonpick);
        spk = findViewById(R.id.SpkName);
        tanggalreal = findViewById(R.id.InputTanggalReal);
        kawil = findViewById(R.id.InputKawil);
        pj = findViewById(R.id.InputPJ);
        Aktifitas = findViewById(R.id.InputAktifitas);
        mandor = findViewById(R.id.InputMandor);
        Tanggalspk = findViewById(R.id.InputTanggal);
        nama = findViewById(R.id.InputNama);
        Grade = findViewById(R.id.InputGrade);
        shift = findViewById(R.id.Shift);
        KIT = findViewById(R.id.InputKIT);
        hasil = findViewById(R.id.InputHasil);
        hko = findViewById(R.id.InputHKO);
        jam = findViewById(R.id.InputJamKerja);
        keterangan = findViewById(R.id.InputKeterangan);
        recyclerView = findViewById(R.id.reyaktifitas);
        //getkey

        Bundle bundle = getIntent().getExtras();
        String idspk = bundle.getString("spkid");
        String namamandor = bundle.getString("mandor");
        Tanggalspk.setText(bundle.getString("tanggal"));
        mandor.setText(bundle.getString("mandor"));
        pj.setText(bundle.getString("pj"));
        shift.setText(bundle.getString("shift"));
        spk.setText(bundle.getString("nama"));
        kawil.setText(bundle.getString("kawil"));

        loadRecycleData(idspk);
        getDataAktifitas(idspk);
        getMandor(namamandor);


        viewAdapter = new AdapterSPK(this, resultsAllaktifitas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);


        buttonpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int moth = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // get Date pick

                picker = new DatePickerDialog(InputSPK.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalreal.setText(year + "-" + (month + 1) + "-" + dayOfMonth);

                    }
                }, year, moth, day);
                picker.show();
            }
        });
        LoadSPK();
        getListDataGrade();
        setListeners();

        Aktifitas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String aktifitas = Aktifitas.getText().toString();
                getTargetHasil(Aktifitas.getText().toString());
                getJenisUpah(aktifitas);
                getTKtemp(aktifitas);
                getJumlahTKtemp(Aktifitas.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getKIT(nama.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public EditText getMandor() {
        return mandor;
    }

    public void getListDataGrade() {

        ArrayList<String> grade = new ArrayList<>();
        grade.add("A");
        grade.add("B");
        grade.add("C");
        grade.add("D");

        statusPopupList3 = new ListPopupWindow(InputSPK.this);
        ArrayAdapter adapter = new ArrayAdapter<>(InputSPK.this, R.layout.list_grade, R.id.tv_elementgrade, grade);
        statusPopupList3.setAnchorView(Grade); //this let as set the popup below the EditText
        statusPopupList3.setAdapter(adapter);
        statusPopupList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grade.setText(grade.get(position));//we set the selected element in the EditText
                statusPopupList3.dismiss();
            }
        });


    }

    public void loadRecycleData(String data) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading..");
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getAllAktifitas(data);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultsAllaktifitas = response.body().getResultAllAktifitas();
                    viewAdapter = new AdapterSPK(InputSPK.this, resultsAllaktifitas);
                    recyclerView.setAdapter(viewAdapter);
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void getMandor(String name) {
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

                if (value.equals("1")) {

                    resultmandor = response.body().getResultmandorID();
                    String mandorid = resultmandor.get(0).getIdMandor();

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }

    public void getDataSPK(String spk) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getspk(spk);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();

                if (value.equals("1")) {
                    results = response.body().getResult();
                    mandor.setText(results.get(0).getNamaMandor());
                    kawil.setText(results.get(0).getKawil());
                    pj.setText(results.get(0).getPJ());
                    shift.setText(results.get(0).getShift());
                    Tanggalspk.setText(results.get(0).getTanggalSPK());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void getTargetHasil(String aktifitas) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getTargetHasil(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultTargetHasils = response.body().getResultTargetHasil();


                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void getDataAktifitas(String aktifitas) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getaktifitas(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")) {

                    resultsaktifitas = response.body().getResultAktifitas();

                    final List<String> status = new ArrayList<>();

                    for (int i = 0; i < resultsaktifitas.size(); i++) {

                        status.add(resultsaktifitas.get(i).getAktifitasName());
                    }

                    statusPopupList2 = new ListPopupWindow(InputSPK.this);
                    ArrayAdapter adapter = new ArrayAdapter<>(InputSPK.this, R.layout.list_aktifitas, R.id.tv_elementaktifitas, status);
                    statusPopupList2.setAnchorView(Aktifitas); //this let as set the popup below the EditText
                    statusPopupList2.setAdapter(adapter);
                    statusPopupList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Aktifitas.setText(resultsaktifitas.get(position).getAktifitasName());//we set the selected element in the EditText
                            statusPopupList2.dismiss();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void getTKtemp(String aktifitas) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getTK(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();


                if (value.equals("1")) {
                    resultstk = response.body().getResultTK();
                    final List<String> status = new ArrayList<>();
                    for (int i = 0; i < resultstk.size(); i++) {
                        status.add(resultstk.get(i).getNama());
                    }

                    statusPopupList1 = new ListPopupWindow(InputSPK.this);
                    ArrayAdapter adapter = new ArrayAdapter<>(InputSPK.this, R.layout.list_tk, R.id.tv_elementtk, status);
                    statusPopupList1.setAnchorView(nama); //this let as set the popup below the EditText
                    statusPopupList1.setAdapter(adapter);
                    statusPopupList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            nama.setText(resultstk.get(position).getNama());//we set the selected element in the EditText
                            statusPopupList1.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void getKIT(String nama) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getKIT(nama);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();

                if (value.equals("1")) {
                    resultskit = response.body().getResultKIT();

                    KIT.setText(resultskit.get(0).getKIT());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void LoadSPK() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.SpkName();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                int position = 0;
                String value = response.body().getValue();
                if (value.equals("1")) {

                    results = response.body().getResult();

                    final List<String> status = new ArrayList<>();

                    statusPopupList = new ListPopupWindow(InputSPK.this);
                    ArrayAdapter adapter = new ArrayAdapter<>(InputSPK.this, R.layout.list_spk, R.id.tv_element, status);
                    statusPopupList.setAnchorView(spk); //this let as set the popup below the EditText
                    statusPopupList.setAdapter(adapter);
                    statusPopupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            spk.setText(status.get(position));//we set the selected element in the EditText
                            statusPopupList.dismiss();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    private void setListeners() {


        Aktifitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusPopupList2 == null) {

                    Toast.makeText(getApplicationContext(), "Data Belum masuk", Toast.LENGTH_SHORT).show();
                } else {
                    statusPopupList2.show();
                }
            }
        });

        nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusPopupList1 == null) {

                    Toast.makeText(getApplicationContext(), "Data Belum masuk", Toast.LENGTH_SHORT).show();
                } else {
                    statusPopupList1.show();
                }

            }
        });

        Grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusPopupList3.show();
            }
        });
    }

    public void InputDataSPK(View view) {

        Bundle bundle = getIntent().getExtras();
        String idspk = bundle.getString("spkid");

        String aktifitasI = Aktifitas.getText().toString();
        String NamaTK = nama.getText().toString();
        String kit = KIT.getText().toString();
        String Spk = idspk;
        String HKO = hko.getText().toString();
        String Hasil = hasil.getText().toString();
        String Tanggal = tanggalreal.getText().toString();
        String Great = Grade.getText().toString();
        String Jam = jam.getText().toString();
        String Keterangan = keterangan.getText().toString();


        if (Tanggal.equals("")) {

            Toast.makeText(getApplicationContext(), "Tanggal Tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (Hasil.equals("")) {

            Toast.makeText(getApplicationContext(), "Hasil Tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (HKO.equals("")) {

            Toast.makeText(getApplicationContext(), "HKO Tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {

            //membuat progres dialog
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading..");
            progressDialog.show();

            getJenisUpah(Aktifitas.getText().toString());
            String jenisuph = resultJenisUpah.get(0).getJenisUpah();

            int TotalUpah;
            if (jenisuph.equals("2")) {
                TotalUpah = 97975;
            } else if (jenisuph.equals("1")) {
                int hasil = Integer.parseInt(Hasil);
                TotalUpah = (hasil * 94);
            } else {
                int hasil = Integer.parseInt(Hasil);
                TotalUpah = (hasil * 600);
                if (TotalUpah < 97975) {
                    TotalUpah = 97975;

                } else {
                    TotalUpah = (hasil * 600);

                }
            }
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiData api = retrofit.create(ApiData.class);
            Call<Value> call = api.Simpan(Spk, aktifitasI, NamaTK, kit, HKO, Hasil, Jam, jenisuph, Keterangan, Great, Tanggal, TotalUpah);
            call.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    String value = response.body().getValue();

                    progressDialog.dismiss();
                    if (value.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Data Berhasil Dimasukan", Toast.LENGTH_SHORT).show();
                        hko.setText("");
                        hasil.setText("");
                        jam.setText("");
                        keterangan.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(), "Data Gagal Dimasukan", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Jaringan error", Toast.LENGTH_SHORT).show();

                }


            });

        }
    }

    public void getJenisUpah(String aktifitas) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiData api = retrofit.create(ApiData.class);
        Call<Value> call = api.getJenisUpah(aktifitas);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultJenisUpah = response.body().getResultJenisUpah();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });


    }

    public void ReadMenu(View view) {
        Intent intent = new Intent(InputSPK.this, DetailSPK.class);
        Bundle bundle = getIntent().getExtras();
        String idspk = bundle.getString("spkid");
        intent.putExtra("spkid", idspk);
        startActivity(intent);

    }

    public void getJumlahTKtemp(String aktifitas) {

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
                int targethasil = Integer.parseInt(resultTargetHasils.get(0).getJumlah());
                int jumlahtk = Integer.parseInt(resultJumlahTKS.get(0).getJumlah());
                if (jumlahtk == 0) {
                    Toast.makeText(getApplicationContext(), "TK Belum Dipilih", Toast.LENGTH_SHORT).show();
                } else {

                    int hasil1 = targethasil / jumlahtk;
                    String hasil2 = String.valueOf(hasil1);
                    hasil.setText(hasil2);
                }


            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }

        });

    }

}
