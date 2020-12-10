package com.ggf.zpasnew;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiData {

    @GET("getSpk.php")
    Call<Value> SpkName();
    @GET("getSPKList.php")
    Call<Value> Spklist();

    @GET("getTKData.php")
    Call<Value> getDataSpk();

    @GET("getSPKInput.php")
    Call<Value> getSpkInput();

    @FormUrlEncoded
    @POST("selectSPK.php")
    Call<Value> getspk(@Field("getspk") String getspk);

    @FormUrlEncoded
    @POST("getAktifitas.php")
    Call<Value> getaktifitas(@Field("getAktifitas") String getAktifitas);

    @FormUrlEncoded
    @POST("getCountAktifitas.php")
    Call<Value> getCountaktifitas(@Field("Aktifitas") String Aktifitas);

    @FormUrlEncoded
    @POST("getTK.php")
    Call<Value> getTK(@Field("getTK") String getTK);

    @FormUrlEncoded
    @POST("getKIT.php")
    Call<Value> getKIT(@Field("getKIT") String getKIT);

    @FormUrlEncoded
    @POST("getAllAktifitas.php")
    Call<Value> getAllAktifitas(@Field("getAllAktifitas") String getAllAktifitas);

    @FormUrlEncoded
    @POST("countJumlahTK.php")
    Call<Value> getJumlahTK(@Field("AktifitasTK") String AktifitasTK);

    @FormUrlEncoded
    @POST("getMandorid.php")
    Call<Value> getmandorid(@Field("getmandor") String getmandor);


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
                       @Field("Keterangan") String Keterangan,
                       @Field("great") String great,
                       @Field("tanggalReal") String tanggalReal);

    @FormUrlEncoded
    @POST("FilterAktifitas.php")
    Call<Value> filterAktifitas(@Field("cari") String cari);

    @FormUrlEncoded
    @POST("getLuasHasil.php")
    Call<Value> getluasHasil(@Field("AktifitasLuas") String AktifitasLuas);

    @FormUrlEncoded
    @POST("updateDataAktifitas.php")
    Call<Value> UpdateAktifitas(@Field("ID") int ID,
                                @Field("RealLhasil") String RealLhasil,
                                @Field("RealHasilEf") String RealHasilEf,
                                @Field("RealJmlhTK") String RealJmlhTK);


    @FormUrlEncoded
    @POST("getDataRealAktifitas.php")
    Call<Value> getDataReal(@Field("aktifitasReal") String aktifitasReal);






}
