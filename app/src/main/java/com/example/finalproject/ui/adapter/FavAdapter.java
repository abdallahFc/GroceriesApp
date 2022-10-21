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
import com.example.finalproject.data.local.room.Fav;
import com.example.finalproject.ui.RemoveClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.viewHolder>{
    List<Fav> list;
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getItemTitle());
        holder.rate.setText(list.get(position).getItemRate());
        holder.price.setText(list.get(position).getItemPrice());
        Picasso.get().load(list.get(position).getItemImg()).into(holder.imageView);
    }

    public void setList(List<Fav> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title,price,rate;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.cart_title);
            price=itemView.findViewById(R.id.item_cart_price);
            rate=itemView.findViewById(R.id.cart_rate);
            imageView=itemView.findViewById(R.id.cart_Image);
        }
    }
}
