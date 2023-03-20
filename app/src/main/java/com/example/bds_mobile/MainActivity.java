package com.example.bds_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bds_mobile.activities.DetailPostActivity;
import com.example.bds_mobile.adapters.BlogAdapter;
import com.example.bds_mobile.api.APIClient;
import com.example.bds_mobile.api.APIInterface;
import com.example.bds_mobile.databinding.ActivityMainBinding;
import com.example.bds_mobile.listeners.PostListener;
import com.example.bds_mobile.model.Post;
import com.example.bds_mobile.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostListener {
    private ActivityMainBinding binding;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        token = getIntent().getStringExtra("access_token");
        getPost(token);
    }

    private void setListeners(){

    }


    private void getPost(String token){
        loading(true);
        APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<List<Post>> call = apiInterface.getListPost("Bearer " + token);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> posts = new ArrayList<>();
                    posts = response.body();
                    if(posts.size() >0){
                        BlogAdapter adapter = new BlogAdapter(posts, MainActivity.this,token);
                        binding.postRecyclerView.setAdapter(adapter);
                        binding.postRecyclerView.setVisibility(View.VISIBLE);

                    }
                }else {
                    showToast("Get lis post unsuccessfully");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void loading(Boolean isLoading){
        if(isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostClicked(Post post) {
        Intent intent = new Intent(getApplicationContext(), DetailPostActivity.class);
        intent.putExtra("access_token", token);
        startActivity(intent);
        finish();
    }
}