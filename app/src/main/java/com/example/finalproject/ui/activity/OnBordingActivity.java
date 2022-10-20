package com.example.finalproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.databinding.ActivityOnBordingBinding;

public class OnBordingActivity extends AppCompatActivity {
    ActivityOnBordingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnBordingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBordingActivity.this, LoginActivity.class));
            }
        });
    }
}