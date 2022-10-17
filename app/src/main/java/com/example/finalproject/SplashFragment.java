package com.example.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.databinding.FragmentSplashBinding;
import com.google.android.material.navigation.NavigationView;


public class SplashFragment extends Fragment {
    FragmentSplashBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSplashBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController= Navigation.findNavController(view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              navController.navigate(R.id.action_splashFragment_to_onBordingFragment);
            }
        },3000);
    }
}