package com.example.lcf.Cart.API;


import com.example.lcf.Cart.Model.ResponseModel2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {

    @GET("dataCart.php")
    Call<ResponseModel2> ardRetrieveData2();

}

