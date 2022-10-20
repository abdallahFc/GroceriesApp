package com.example.finalproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentPrefrenceBinding;
import com.example.finalproject.databinding.FragmentProduectBinding;
import com.example.finalproject.model.ProducetModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrefrenceFragment extends Fragment {
    FragmentPrefrenceBinding binding;
    ProducetModel producetModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentPrefrenceBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PrefrenceFragmentArgs args=PrefrenceFragmentArgs.fromBundle(getArguments());
        int id=args.getId();

        ProducetClient.getInstance().getProduect(id).enqueue(new Callback<ProducetModel>() {
            @Override
            public void onResponse(Call<ProducetModel> call, Response<ProducetModel> response) {
                producetModel=new ProducetModel();
                producetModel=response.body();
                binding.txTitle.setText(producetModel.getTitle());
                binding.txPrice.setText("$"+producetModel.getPrice());
                binding.dicrption.setText(producetModel.getDescription());
                Picasso.get().load(producetModel.getImage()).into(binding.imageView);
            }

            @Override
            public void onFailure(Call<ProducetModel> call, Throwable t) {

            }
        });

    }

    public void action(View view) {
    }
}