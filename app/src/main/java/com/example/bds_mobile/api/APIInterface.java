package com.example.bds_mobile.api;

import com.example.bds_mobile.model.Token;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    @FormUrlEncoded
    @POST("/api/login")
    Call<Token> loginUser(@Field("username") String username,
                          @Field("password") String password);
}
