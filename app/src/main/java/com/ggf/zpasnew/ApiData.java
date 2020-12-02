package com.ggf.zpasnew;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiData {

    @GET("getSpk.php")
    Call<Value> SpkName();

    @FormUrlEncoded
    @POST("selectSPK.php")
    Call<Value> getspk(@Field("getspk") String getspk);

    @FormUrlEncoded
    @POST("getAktifitas.php")
    Call<Value> getaktifitas(@Field("getAktifitas") String getAktifitas);

    @FormUrlEncoded
    @POST("getTK.php")
    Call<Value> getTK(@Field("getTK") String getTK);

    @FormUrlEncoded
    @POST("getKIT.php")
    Call<Value> getKIT(@Field("getKIT") String getKIT);

    @FormUrlEncoded
    @POST("getAllAktifitas.php")
    Call<Value> getAllAktifitas(@Field("getAllAktifitas") String getAllAktifitas);

}
