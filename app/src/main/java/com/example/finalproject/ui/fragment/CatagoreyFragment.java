package com.example.finalproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.ui.adapter.CatagoryAdaptar;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentCatagoreyBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatagoreyFragment extends Fragment {
    FragmentCatagoreyBinding binding;
    CatagoryAdaptar adaptar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentCatagoreyBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adaptar=new CatagoryAdaptar();
        ProducetClient.getInstance().getCatagory().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if(response.isSuccessful()){
                    adaptar.setList(response.body());
                    binding.recycleView.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });

    }
}