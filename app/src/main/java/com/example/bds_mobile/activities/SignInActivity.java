package com.example.bds_mobile.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bds_mobile.MainActivity;
import com.example.bds_mobile.api.APIClient;
import com.example.bds_mobile.api.APIInterface;
import com.example.bds_mobile.databinding.ActivitySignInBinding;
import com.example.bds_mobile.model.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
        binding.buttonSignIn.setOnClickListener(v -> {
            if (isValidSignInDetails()){
                signIn();
            }
        });
    }

    private void signIn(){
        loading(true);
        APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<Token> call = apiInterface.loginUser(binding.inputEmail.getText().toString().trim(),binding.inputPassword.getText().toString().trim());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    showToast("Login successfully");
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("access_token", response.body().getAccessToken());
                    startActivity(intent);
                }else {
                    loading(false);
                    showToast("Unable to sign in");
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    private void loading(Boolean isLoading){
        if(isLoading){
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignIn.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private Boolean isValidSignInDetails(){
        if(binding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Enter username");
            return false;
        }else if(binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        }
        return true;
    }
}