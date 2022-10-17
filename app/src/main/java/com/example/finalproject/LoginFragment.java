package com.example.finalproject;

import android.content.Intent;
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

import com.example.finalproject.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    UserViewModel userViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController= Navigation.findNavController(view);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.email.getText().toString();
                String pass=binding.pass.getText().toString();
                userViewModel.getLoginFromRepo(email,pass);
                userViewModel.getLoginResponse().observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_loginFragment_to_homeFragment);
                    }
                });
                userViewModel.getLoginResponseError().observe(getActivity(), new Observer<String>() {
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
                navController.navigate(R.id.action_loginFragment_to_singupFragment);
            }
        });
    }
}