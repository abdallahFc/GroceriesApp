package com.example.finalproject.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.data.local.room.CartDataBase;
import com.example.finalproject.data.local.room.Fav;
import com.example.finalproject.data.local.room.FavDataBase;
import com.example.finalproject.databinding.FragmentFavoritesBinding;
import com.example.finalproject.ui.activity.MainActivity;
import com.example.finalproject.ui.adapter.FavAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    FragmentFavoritesBinding binding;
    FavAdapter adapter;
    MainActivity activity;
    List<Fav> list=new ArrayList<>();
    List<Cart> cartList=new ArrayList<>();

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentFavoritesBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new FavAdapter();
        FavDataBase.getInstance(getContext()).Doa().getAllItems().observe(activity, new Observer<List<Fav>>() {
            @Override
            public void onChanged(List<Fav> favs) {
                list=favs;
                Toast.makeText(activity,favs.size()+"",Toast.LENGTH_LONG).show();
                adapter.setList(favs);
                binding.recyclerView3.setAdapter(adapter);
                binding.recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
            binding.addAllCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<list.size();i++){
                    cartList.add(new Cart(list.get(i).getId(),list.get(i).getItemTitle(),list.get(i).getItemPrice(),list.get(i).getItemRate(),list.get(i).getItemImg(),"1"));
                }
                    try {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i=0;i<cartList.size();i++){
                                    CartDataBase.getInstance(getContext()).Doa().insertItem(cartList.get(i));
                                }
                            }
                        }).start();

                    }catch (Exception e){

                    }

                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FavDataBase.getInstance(getContext()).Doa().deleteItem();
                        }
                    }).start();

                }catch (Exception e){

                }

            }
        });
    }
}