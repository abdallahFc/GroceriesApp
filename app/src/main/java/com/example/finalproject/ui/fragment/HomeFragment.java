package com.example.finalproject.ui.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.ui.OnItemClick;
import com.example.finalproject.R;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentHomeBinding;
import com.example.finalproject.model.ProducetModel;
import com.example.finalproject.ui.activity.MainActivity;
import com.example.finalproject.ui.adapter.CatagoryHomeAdapter;
import com.example.finalproject.ui.adapter.ProducetAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnItemClick {
    FragmentHomeBinding binding;
    ProducetAdapter adapter;
    CatagoryHomeAdapter cAdapter;
    public MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        return view;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new ProducetAdapter(this);
        cAdapter=new CatagoryHomeAdapter();
        ProducetClient.getInstance().getsomeData().enqueue(new Callback<List<ProducetModel>>() {
            @Override
            public void onResponse(Call<List<ProducetModel>> call, Response<List<ProducetModel>> response) {
                adapter.setItemsList(response.body());
                binding.recyclerView2.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ProducetModel>> call, Throwable t) {
                Log.d("ma","ahmed"+t);
            }
        });
        ProducetClient.getInstance().getCatagory().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                cAdapter.setList(response.body());
                binding.recyclerView.setAdapter(cAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d("ma","ahmed"+t);
            }
        });


        binding.seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_homeFragment_to_catagoreyFragment);
            }
        });
    }

    @Override
    public void onClick(int id) {

    }
}