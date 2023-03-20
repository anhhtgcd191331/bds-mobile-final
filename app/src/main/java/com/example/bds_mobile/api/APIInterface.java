package com.example.bds_mobile.api;

import com.example.bds_mobile.model.Post;
import com.example.bds_mobile.model.Token;
import com.example.bds_mobile.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @FormUrlEncoded
    @POST("/api/login")
    Call<Token> loginUser(@Field("username") String username,
                          @Field("password") String password);

    @GET("/api/v1/post/list")
    Call<List<Post>> getListPost(@Header("Authorization") String authHeader);

    @GET("/api/v1/user/id/{userId}")
    Call<User> getUser(@Header("Authorization") String authHeader, @Path("userId") Long id);
}
