package com.example.finalproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.data.local.sharedPre.SaveData;
import com.example.finalproject.databinding.ActivityLoginBinding;
import com.example.finalproject.viewModel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if (new SaveData(LoginActivity.this).getUserStatus()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.tvEmail.getText().toString();
                String pass=binding.pass.getText().toString();
                userViewModel.getLoginFromRepo(email,pass);
                userViewModel.getLoginResponse().observe(LoginActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                        new SaveData(LoginActivity.this).updateUserStatus(true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                });
                userViewModel.getLoginResponseError().observe(LoginActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}