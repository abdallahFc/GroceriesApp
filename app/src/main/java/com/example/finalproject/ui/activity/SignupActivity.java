package com.example.finalproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.data.local.sharedPre.SaveData;
import com.example.finalproject.databinding.ActivitySignupBinding;
import com.example.finalproject.viewModel.UserViewModel;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.userName.getText().toString();
                String email = binding.tvEmail.getText().toString();
                String pass = binding.pass.getText().toString();
                userViewModel.getFromRepo(name, email, pass);
                userViewModel.getUserResponse().observe(SignupActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                        new SaveData(SignupActivity.this).UserInfo(name,email);
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }
                });
                userViewModel.getUserResponseError().observe(SignupActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
}