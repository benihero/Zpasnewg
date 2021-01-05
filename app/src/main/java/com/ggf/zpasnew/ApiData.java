package com.ggf.zpasnew;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiData {

    @GET("getSpk.php")
    Call<Value> SpkName();

    @GET("getHasilEfektif.php")
    Call<Value> gethasil();

    @GET("getSPKList.php")
    Call<Value> Spklist();

    @GET("getTKData.php")
    Call<Value> getDataSpk();

    @GET("getSPKInput.php")
    Call<Value>   getSpkInput();

    @FormUrlEncoded
    @POST("updateLuasHasil.php")
    Call<Value> updateLuasHasil(@Field("spkID") String spkID,
                                @Field("aktifitas") String aktifitas,
                                @Field("LuasHasil") String LuasHasil
                                );

    @FormUrlEncoded
    @POST("selectSPK.php")
    Call<Value> getspk(@Field("getspk") String getspk);

    @FormUrlEncoded
    @POST("getJumlahTKtarget.php")
    Call<Value> getJumlahTKtarget(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("countTotalUpahAktifitas.php")
    Call<Value> getUpahTotal(@Field("Aktifitas") String Aktifitas);

    @FormUrlEncoded
    @POST("getJumlahtktemp.php")
    Call<Value> getJumlahtktemp(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("getTKbyMandor.php")
    Call<Value> getTKmandor(@Field("idmandor") String idmandor);

    @FormUrlEncoded
    @POST("edithko.php")
    Call<Value> updatehko(@Field("id") String id,
                          @Field("kodenote") String kodenote,
                          @Field("HKOKarom") String HKOKarom);

    @FormUrlEncoded
    @POST("updateDataInput.php")
    Call<Value> updatedatainput(@Field("id") String id,
                                @Field("HKO") String HKO,
                                @Field("Hasil") String Hasil,
                                @Field("JamKerja") String JamKerja);

    @FormUrlEncoded
    @POST("getKegiatanTk.php")
    Call<Value> getKegiatantk(@Field("nama") String nama);

    @FormUrlEncoded
    @POST("getTargetHasil.php")
    Call<Value> getTargetHasil(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("getAktifitas.php")
    Call<Value> getaktifitas(@Field("getAktifitas") String getAktifitas);

    @FormUrlEncoded
    @POST("summarytk.php")
    Call<Value> getSumTK(@Field("spkyo") String spkyo);

    @FormUrlEncoded
    @POST("getCountAktifitas.php")
    Call<Value> getCountaktifitas(@Field("Aktifitas") String Aktifitas);

    @FormUrlEncoded
    @POST("getTK.php")
    Call<Value> getTK(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("getJenisUpah.php")
    Call<Value> getUpah(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("getKIT.php")
    Call<Value> getKIT(@Field("getKIT") String getKIT);

    @FormUrlEncoded
    @POST("getAllAktifitas.php")
    Call<Value> getAllAktifitas(@Field("getAllAktifitas") String getAllAktifitas);

    @FormUrlEncoded
    @POST("getAllSummary.php")
    Call<Value> getAllSummary(@Field("getAllAktifitas") String getAllAktifitas);

    @FormUrlEncoded
    @POST("countJumlahTK.php")
    Call<Value> getJumlahTK(@Field("AktifitasTK") String AktifitasTK);

    @FormUrlEncoded
    @POST("getMandorid.php")
    Call<Value> getmandorid(@Field("getmandor") String getmandor);

    @FormUrlEncoded
    @POST("Deletetktmp.php")
    Call<Value> deletetk(@Field("id") String id);

    @FormUrlEncoded
    @POST("getDataTKtemp.php")
    Call<Value> getDataTKtemp(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("inputTkTemp.php")
    Call<Value> savetktemp(@Field("aktifitas") String aktifitas,
                           @Field("JenisUpah") String JenisUpah,
                           @Field("spkID") String spkID,
                           @Field("namaTK") String namaTK);


    @GET("getNamaAktifitas.php")
    Call<Value> getNamaSpk();

    @FormUrlEncoded
    @POST("InputSPK.php")
    Call<Value> Simpan(@Field("spkID") String spkID,
                       @Field("aktifitas") String Aktifitas,
                       @Field("namaTK") String namaTK,
                       @Field("KIT") String KIT,
                       @Field("HKO") String HKO,
                       @Field("hasil") String hasil,
                       @Field("JamKerja") String JamKerja,
                       @Field("JenisUpah") String JenisUpah,
                       @Field("Keterangan") String Keterangan,
                       @Field("great") String great,
                       @Field("tanggalReal") String tanggalReal,
                       @Field("upah") Integer upah);

    @FormUrlEncoded
    @POST("FilterAktifitas.php")
    Call<Value> filterAktifitas(@Field("cari") String cari);

    @FormUrlEncoded
    @POST("getJenisUpah.php")
    Call<Value> getJenisUpah(@Field("aktifitas") String aktifitas);

    @FormUrlEncoded
    @POST("getLuasHasil.php")
    Call<Value> getluasHasil(@Field("AktifitasLuas") String AktifitasLuas);

    @FormUrlEncoded
    @POST("updateAutoAktifitas.php")
    Call<Value> UpdateAktifitas(@Field("ID") String ID,
                                @Field("aktifitas") String aktifitas,
                                @Field("RealHasilEf") String RealHasilEf,
                                @Field("RealJmlhTK") String RealJmlhTK,
                                @Field("LuasHasil") String LuasHasil,
                                @Field("totalupah") String totalupah
                                );


    @FormUrlEncoded
    @POST("getDataRealAktifitas.php")
    Call<Value> getDataReal(@Field("aktifitasReal") String aktifitasReal);

}