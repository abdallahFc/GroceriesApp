package com.example.finalproject.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.data.local.room.Cart;
import com.example.finalproject.ui.RemoveClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder>{
    List<Cart> list;
    RemoveClick itemClick;

    public CartAdapter(RemoveClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getItemTitle());
        holder.rate.setText(list.get(position).getItemRate());
        holder.price.setText(list.get(position).getItemPrice());
        holder.count.setText(list.get(position).getCount());
        Picasso.get().load(list.get(position).getItemImg()).into(holder.imageView);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(list.get(position).getId());
            }
        });
    }

    public void setList(List<Cart> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title,price,rate,count;
        ImageView imageView,remove;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.cart_title);
            price=itemView.findViewById(R.id.cart_price);
            rate=itemView.findViewById(R.id.cart_rate);
            imageView=itemView.findViewById(R.id.cart_Image);
            remove=itemView.findViewById(R.id.remove_item);
            count=itemView.findViewById(R.id.count);
        }
    }
}
