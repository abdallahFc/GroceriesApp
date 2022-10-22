package com.example.finalproject.ui.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.finalproject.ui.activity.MainActivity;
import com.example.finalproject.viewModel.CartViewModel;
import com.example.finalproject.viewModel.FavViewModel;
import com.example.finalproject.viewModel.ProduectViewModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrefrenceFragment extends Fragment {
    FragmentPrefrenceBinding binding;
    ProducetModel producetModel;
    String title,price,img,rate;
    ProduectViewModel produectViewModel;
    CartViewModel cartViewModel;
    FavViewModel favViewModel;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentPrefrenceBinding.inflate(inflater, container, false);
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
        PrefrenceFragmentArgs args=PrefrenceFragmentArgs.fromBundle(getArguments());
        produectViewModel = new ViewModelProvider(this).get(ProduectViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        favViewModel = new ViewModelProvider(this).get(FavViewModel.class);
        int id=args.getId();
        produectViewModel.getSomeProduect(id);
        produectViewModel.getSomeProduect().observe(activity, new Observer<ProducetModel>() {
            @Override
            public void onChanged(ProducetModel producet) {;
                producetModel =producet;
                title = producetModel.getTitle();
                rate = producetModel.getRating().getRate() + " (" + producetModel.getRating().getCount() + " reviews)";
                price = "$" + " " + producetModel.getPrice();
                img = producetModel.getImage();
                binding.txTitle.setText(producetModel.getTitle());
                binding.txPrice.setText("$ " + producetModel.getPrice());
                binding.dicrption.setText(producetModel.getDescription());
                binding.txRate.setText(producetModel.getRating().getRate() + " (" + producetModel.getRating().getCount() + "reviews)");
                Picasso.get().load(producetModel.getImage()).into(binding.imageView);

            }
        });
        produectViewModel.getSomeProduectError().observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        });
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(binding.txCount.getText().toString());
                binding.txCount.setText((count+1)+"");
                String[] res=binding.txPrice.getText().toString().split(" ");
                double p= Double.parseDouble(res[1]);
                binding.txPrice.setText("$ "+(count+1)*p);

            }
        });
        binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(binding.txCount.getText().toString());
                if(count>=1)
                binding.txCount.setText((count-1)+"");
                String[] res=binding.txPrice.getText().toString().split(" ");
                double p= Double.parseDouble(res[1]);
                binding.txPrice.setText("$ "+(count-1)*p);
            }
        });
        binding.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.scrollView2.setVisibility(View.VISIBLE);
            }
        });
        binding.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart=new Cart(id,title,binding.txPrice.getText().toString(),rate,img,binding.txCount.getText().toString());
                cartViewModel.insert(cart,getContext());
                cartViewModel.getmsg().observe(activity, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
                    }
                });
                cartViewModel.getInsertErorr().observe(activity, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
                    }
                });
//                try {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            CartDataBase.getInstance(getContext()).Doa().insertItem(cart);
//                        }
//                    }).start();
//                    Toast.makeText(getContext(),"inserted",Toast.LENGTH_SHORT).show();
//                }catch (Exception e){
//                    Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
//                }
            }
        });
        binding.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fav fav=new Fav(id,title,price,rate,img);
                binding.ivFav.setColorFilter(R.color.red);
                favViewModel.insert(fav,getContext());
                favViewModel.getmsg().observe(activity, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
                    }
                });
//                try {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            FavDataBase.getInstance(getContext()).Doa().insertItem(fav);
//                        }
//                    }).start();
//                    Toast.makeText(getContext(),"inserted",Toast.LENGTH_SHORT).show();
//                }catch (Exception e){
//                    Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}