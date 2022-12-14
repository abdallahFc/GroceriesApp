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
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.data.local.room.CartDataBase;
import com.example.finalproject.databinding.FragmentCartBinding;
import com.example.finalproject.ui.RemoveClick;
import com.example.finalproject.ui.activity.MainActivity;
import com.example.finalproject.ui.adapter.CartAdapter;
import com.example.finalproject.viewModel.CartViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements RemoveClick {
    FragmentCartBinding binding;
    CartAdapter adapter;
    MainActivity activity;
    CartViewModel cartViewModel;
    double price;
    List<Cart> list=new ArrayList<>();
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentCartBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new CartAdapter(this);
        NavController navController = Navigation.findNavController(view);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getAll(getContext());
        cartViewModel.getItems().observe(activity, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                if (!carts.isEmpty()) {
                    binding.imageView5.setVisibility(View.GONE);
                    binding.textView9.setVisibility(View.GONE);
                } else {
                    binding.imageView5.setVisibility(View.VISIBLE);
                    binding.textView9.setVisibility(View.VISIBLE);
                }
                list = carts;
                adapter.setList(carts);
                binding.recyclerView3.setAdapter(adapter);
                binding.recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        binding.btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(activity,R.style.bottomSheet);
                View sheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet, null);
                price=0;
                for (int i=0;i<list.size();i++){
                    String[] res=list.get(i).getItemPrice().split(" ");
                    double p= Double.parseDouble(res[1]);
                    price+=p;
                }
                sheetView.findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navController.navigate(R.id.action_cartFragment_to_orderFragment);
                        bottomSheetDialog.cancel();
                    }
                });
                sheetView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });
              TextView textView=sheetView.findViewById(R.id.cart_checkout);
              textView.setText(price+" $");
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

    }

    @Override
    public void onClick(int id) {
        cartViewModel.delete(id,getContext());
        Toast.makeText(getContext(),"Item Remove",Toast.LENGTH_SHORT).show();
    }
}