package com.example.bds_mobile.api;

import com.example.bds_mobile.model.Like;
import com.example.bds_mobile.model.Post;
import com.example.bds_mobile.model.Token;
import com.example.bds_mobile.model.User;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("/api/v1/user/find/{username}")
    Call<User> getUserByUsername(@Header("Authorization") String authHeader, @Path("username") String username);

    @GET("/api/v1/post/{postId}")
    Call<Post> getPost(@Header("Authorization") String authHeader, @Path("postId") Long id);

    @POST("/api/v1/post/new")
    Call<ResponseBody> addNewPost(@Header("Authorization") String authHeader,
                                  @Part("postDTO") Post post,
                                  @Part MultipartBody.Part[] images,
                                  @Part MultipartBody.Part video);

    @POST("/api/v1/like/new")
    Call<JsonObject> likePost(@Header("Authorization") String authHeader, @Body JsonObject like);
    @PUT("/api/v1/like/update")
    Call<JsonObject> unLikePost(@Header("Authorization") String authHeader, @Body JsonObject like);

    @GET("/api/v1/like/list/{postId}")
    Call<List<Like>> getLike(@Header("Authorization") String authHeader, @Path("postId") Long id);
}
