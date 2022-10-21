package com.example.finalproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.ui.adapter.CatagoryAdaptar;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentCatagoreyBinding;
import com.example.finalproject.viewModel.ProduectViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatagoreyFragment extends Fragment {
    FragmentCatagoreyBinding binding;
    CatagoryAdaptar adaptar;
    ProduectViewModel produectViewModel;
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
        produectViewModel = new ViewModelProvider(this).get(ProduectViewModel.class);
        produectViewModel.getCatagory();
        produectViewModel.getCatagorys().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                adaptar.setList(strings);
                binding.recycleView.setAdapter(adaptar);
            }
        });
    }
}