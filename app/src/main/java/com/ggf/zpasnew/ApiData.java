package com.ggf.zpasnew;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiData {

    @GET("getSpk.php")
    Call<Value> SpkName();

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
    @POST("InputSPK.php")
    Call<Value> Simpan(@Field("SPKName") String SPKName,
                       @Field("aktifitas") String Aktifitas,
                       @Field("namaTK") String namaTK,
                       @Field("KIT") String KIT,
                       @Field("HKO") String HKO,
                       @Field("hasil") String hasil,
                       @Field("great") String great,
                       @Field("tanggalReal") String tanggalReal);
}
