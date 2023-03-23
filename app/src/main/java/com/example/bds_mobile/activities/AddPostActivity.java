package com.example.bds_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bds_mobile.R;
import com.example.bds_mobile.databinding.ActivityAddPostBinding;
import com.example.bds_mobile.model.Post;

public class AddPostActivity extends AppCompatActivity {
    private ActivityAddPostBinding binding;
    private Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadPostDetails();
    }

    private  void setListeners(){

    }

    private void loadPostDetails(){
        post = (Post) getIntent().getSerializableExtra("post");

   }
}