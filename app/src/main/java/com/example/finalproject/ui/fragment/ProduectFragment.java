package com.example.finalproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.OnItemClick;
import com.example.finalproject.R;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentProduectBinding;
import com.example.finalproject.model.ProducetModel;
import com.example.finalproject.ui.adapter.ProducetAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProduectFragment extends Fragment implements OnItemClick {
    FragmentProduectBinding binding;
    ProducetAdapter adapter;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProduectBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new ProducetAdapter(this);
         navController = Navigation.findNavController(view);
        ProducetClient.getInstance().getAllData().enqueue(new Callback<List<ProducetModel>>() {
            @Override
            public void onResponse(Call<List<ProducetModel>> call, Response<List<ProducetModel>> response) {
                adapter.setItemsList(response.body());
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ProducetModel>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(int id) {
        ProduectFragmentDirections.ActionProduectFragment2ToPrefrenceFragment action=ProduectFragmentDirections.actionProduectFragment2ToPrefrenceFragment(id);
        action.setId(id);
        navController.navigate(action);
    }
}