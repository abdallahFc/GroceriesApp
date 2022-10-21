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
import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.data.local.room.CartDataBase;
import com.example.finalproject.data.local.room.CartDoa;
import com.example.finalproject.data.local.room.Fav;
import com.example.finalproject.data.local.room.FavDataBase;
import com.example.finalproject.data.network.produect.ProducetClient;
import com.example.finalproject.databinding.FragmentPrefrenceBinding;
import com.example.finalproject.model.ProducetModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrefrenceFragment extends Fragment {
    FragmentPrefrenceBinding binding;
    ProducetModel producetModel;
    String title,price,img,rate;
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
                title=producetModel.getTitle();
                rate=producetModel.getRating().getRate()+" ("+producetModel.getRating().getCount()+"reviews)";
                price="$"+" "+producetModel.getPrice();
                img=producetModel.getImage();
                binding.txTitle.setText(producetModel.getTitle());
                binding.txPrice.setText("$ "+producetModel.getPrice());
                binding.dicrption.setText(producetModel.getDescription());
                binding.txRate.setText(producetModel.getRating().getRate()+" ("+producetModel.getRating().getCount()+"reviews)");
                Picasso.get().load(producetModel.getImage()).into(binding.imageView);
            }

            @Override
            public void onFailure(Call<ProducetModel> call, Throwable t) {

            }
        });
        binding.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart=new Cart(id,title,price,rate,img);
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            CartDataBase.getInstance(getContext()).Doa().insertItem(cart);
                        }
                    }).start();
                    Toast.makeText(getContext(),"inserted",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fav fav=new Fav(id,title,price,rate,img);
                binding.ivFav.setColorFilter(R.color.red);
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FavDataBase.getInstance(getContext()).Doa().insertItem(fav);
                        }
                    }).start();
                    Toast.makeText(getContext(),"inserted",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}