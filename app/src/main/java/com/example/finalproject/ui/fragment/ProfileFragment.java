package com.example.finalproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.data.local.ProfileData;
import com.example.finalproject.data.local.sharedPre.SaveData;
import com.example.finalproject.databinding.FragmentProfileBinding;
import com.example.finalproject.ui.activity.LoginActivity;
import com.example.finalproject.ui.adapter.ProfileAdaptar;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    ProfileAdaptar adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProfileData data=new ProfileData();
        adapter=new ProfileAdaptar();
        Toast.makeText(getContext(),data.getProData().get(0).getTitle(),Toast.LENGTH_LONG).show();
        adapter.setList(data.getProData());
        binding.recyclerView4.setAdapter(adapter);
        binding.recyclerView4.setHasFixedSize(true);
        binding.recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.tvUserName.setText(new SaveData(getContext()).getUserName());
        binding.tvEmail.setText(new SaveData(getContext()).getEmail());
        binding.logoutBtn.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
}