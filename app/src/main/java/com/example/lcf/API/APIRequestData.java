package com.example.lcf.API;


import com.example.lcf.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {

    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @GET("fulldatamakanan.php")
    Call<ResponseModel> search ( @Query("search") String search);



    /*@GET("searchmakanan.php")
    Call<ResponseModel> search ( @Query("search") String search);*/

}

