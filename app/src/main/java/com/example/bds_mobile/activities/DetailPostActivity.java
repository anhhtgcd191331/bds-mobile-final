package com.example.bds_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bds_mobile.MainActivity;
import com.example.bds_mobile.R;
import com.example.bds_mobile.api.APIClient;
import com.example.bds_mobile.api.APIInterface;
import com.example.bds_mobile.databinding.ActivityDetailPostBinding;
import com.example.bds_mobile.model.Post;
import com.example.bds_mobile.model.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPostActivity extends AppCompatActivity {
    private ActivityDetailPostBinding binding;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        token = getIntent().getStringExtra("access_token");
        setListeners();
        loadPostDetails();
    }

    private void setListeners(){
        binding.imageBack.setOnClickListener(v-> {
            String token = getIntent().getStringExtra("access_token");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("access_token", token);
            startActivity(intent);
        });
    }

    private void loadPostDetails(){
        Post post = (Post) getIntent().getSerializableExtra("post");
        binding.title.setText(post.getPostTitle());
        binding.description.setText("Description: " +post.getDescription());
        binding.room.setText("Room: " +post.getNumberOfRooms());
        binding.area.setText("Area: " + post.getSquareArea()+"m2");
        binding.price.setText("Price: " +post.getPrice()+"VND");
        binding.address.setText("Address: " +post.getDetailsAddress());
        binding.typeOfApartment.setText("Department: " +post.getTypeOfApartment());
        APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<User> call = apiInterface.getUser("Bearer " + token, post.getAuthorId());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    binding.author.setText("Post by: " +response.body().getFullName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        if(post.isSold()){
            binding.solidTrue.setText("Empty");
            binding.solidFalse.setText("");
        }else {
            binding.solidFalse.setText("Rented out");
            binding.solidTrue.setText("");
        }
    }
}