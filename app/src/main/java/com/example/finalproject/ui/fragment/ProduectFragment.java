package com.example.finalproject.ui.fragment;

import android.app.Activity;
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

import com.example.finalproject.ui.OnItemClick;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentProduectBinding;
import com.example.finalproject.model.ProducetModel;
import com.example.finalproject.ui.activity.MainActivity;
import com.example.finalproject.ui.adapter.ProducetAdapter;
import com.example.finalproject.viewModel.ProduectViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProduectFragment extends Fragment implements OnItemClick {
    FragmentProduectBinding binding;
    ProducetAdapter adapter;
    NavController navController;
    ProduectViewModel produectViewModel;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProduectBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
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
        adapter = new ProducetAdapter(this);
        navController = Navigation.findNavController(view);
        produectViewModel = new ViewModelProvider(this).get(ProduectViewModel.class);
        produectViewModel.getAllProduect();
        produectViewModel.getProduect().observe(activity, new Observer<List<ProducetModel>>() {
            @Override
            public void onChanged(List<ProducetModel> producetModels) {
                adapter.setItemsList(producetModels);
                binding.recyclerView.setAdapter(adapter);
            }
        });
        produectViewModel.getProduectError().observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(int id) {
        ProduectFragmentDirections.ActionProduectFragment2ToPrefrenceFragment action = ProduectFragmentDirections.actionProduectFragment2ToPrefrenceFragment(id);
        action.setId(id);
        navController.navigate(action);
    }
}