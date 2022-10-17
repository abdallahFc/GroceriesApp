package com.example.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.databinding.FragmentSingupBinding;
import com.example.finalproject.databinding.FragmentSplashBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingupFragment extends Fragment {
    FragmentSingupBinding binding;
    UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSingupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.userName.getText().toString();
                String email = binding.email.getText().toString();
                String pass = binding.pass.getText().toString();
                userViewModel.getFromRepo(name, email, pass);
                userViewModel.getUserResponse().observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_singupFragment_to_loginFragment);
                    }
                });
                userViewModel.getUserResponseError().observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_singupFragment_to_loginFragment);
            }
        });
    }
}