package com.ggf.zpasnew.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.ggf.zpasnew.ApiData;
import com.ggf.zpasnew.DetailSPK;
import com.ggf.zpasnew.DetailSpkInput;
import com.ggf.zpasnew.Model.ResultSummary;
import com.ggf.zpasnew.Model.ResultTotalUpah;
import com.ggf.zpasnew.R;
import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Summary;
import com.ggf.zpasnew.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterSummary extends RecyclerView.Adapter<AdapterSummary.ViewHolder> {
    private Context context;
    private List<ResultSummary> resultSummaries;
    private List<ResultTotalUpah> resultTotalUpahs = new ArrayList<>();

//    String URL = "https://ggp-pis.com/spk_app/";

    String URL = "http://192.168.43.38/spk/";
    public AdapterSummary(Context context, List<ResultSummary> resultSummaries) {
        this.context = context;
        this.resultSummaries = resultSummaries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_summary, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultSummary resultsum = resultSummaries.get(position);
        holder.aktifitas.setText(resultsum.getAktifitasName());
        holder.satuanhasil.setText(resultsum.getSatuanHasil());
        holder.lokasi.setText(resultsum.getLokasi());
        holder.status.setText(resultsum.getStatus());
        holder.lhasil.setText(resultsum.getLhasil());
        holder.thasil.setText(resultsum.getThasil());
        holder.jumlahtk.setText(resultsum.getJumlahTK());
        holder.jenisupah.setText(resultsum.getJenisUpah());
        holder.reallhasil.setText(resultsum.getRealLhasil());
        holder.realhasileff.setText(resultsum.getRealHasilEf());
        holder.realjmlhtk.setText(resultsum.getRealJmlhTK());
        holder.kodenote.setText(resultsum.getKodeNote());
        holder.hakokarom.setText(resultsum.getHKOKarom());
        holder.totalupah.setText(resultsum.getUpah());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final AlertDialog alertbox = new AlertDialog.Builder(v.getRootView().getContext()).create();
                LayoutInflater inflater = LayoutInflater.from(v.getContext());
                View dialogView = inflater.inflate(R.layout.layout_edithko, null);
                final EditText hkoo = dialogView.findViewById(R.id.edithkokarom);
                hkoo.setText(resultsum.getHKOKarom());
                final EditText kodenotes = dialogView.findViewById(R.id.editkodenote);
                kodenotes.setText(resultsum.getKodeNote());
                Button buttonsave = dialogView.findViewById(R.id.buttonSavehko);
                Button buttoncancel = dialogView.findViewById(R.id.buttonCancelhko);


                buttonsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = resultsum.getId();
                        String hko = hkoo.getText().toString();
                        String kodenote = kodenotes.getText().toString();
                        updatehko(id,hko,kodenote);

                        resultSummaries.clear();

                        resultSummaries.add(new ResultSummary());

                        ((Summary)context).recreate();

                        alertbox.dismiss();

                    }
                });
                buttoncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertbox.dismiss();

                    }

                });

                alertbox.setView(dialogView);
                alertbox.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultSummaries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aktifitas,
                satuanhasil,
                lokasi,
                status,
                lhasil,
                thasil,
                jumlahtk,
                jenisupah,
                reallhasil,
                realhasileff,
                realjmlhtk,
                totalupah,
                kodenote,
                hakokarom;
        ImageView edit;
        EditText edithko, editkodenote;
        Button save, cancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edit = itemView.findViewById(R.id.edithko);
            aktifitas = itemView.findViewById(R.id.sumAktifitas);
            satuanhasil = itemView.findViewById(R.id.sumSatuan);
            lokasi = itemView.findViewById(R.id.sumLokasi);
            status = itemView.findViewById(R.id.sumStatus);
            lhasil = itemView.findViewById(R.id.sumlhasil);
            thasil = itemView.findViewById(R.id.sumthasil);
            jumlahtk = itemView.findViewById(R.id.sumJmlhTk);
            jenisupah = itemView.findViewById(R.id.sumJenisUpah);
            reallhasil = itemView.findViewById(R.id.sumRlhasil);
            realhasileff = itemView.findViewById(R.id.sumHasilEff);
            realjmlhtk = itemView.findViewById(R.id.sumRjmlhTk);
            kodenote = itemView.findViewById(R.id.sumKodeNote);
            hakokarom = itemView.findViewById(R.id.sumHKO);
            edithko = itemView.findViewById(R.id.edithkokarom);
            editkodenote = itemView.findViewById(R.id.editkodenote);
            save = itemView.findViewById(R.id.buttonSavehko);
            cancel = itemView.findViewById(R.id.buttonCancelhko);
            totalupah = itemView.findViewById(R.id.sumTotalUpah);
        }
    }

    public void updatehko(String id,String hko,String kodenote){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiData apiData = retrofit.create(ApiData.class);
        Call<Value> call = apiData.updatehko(id,kodenote,hko);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();
                if (value.equals("1")){

                    Toast.makeText(context,"Data Berhasil di Update",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}