package com.example.finalproject.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ui.OnItemClick;
import com.example.finalproject.R;
import com.example.finalproject.model.ProducetModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProducetAdapter extends RecyclerView.Adapter<ProducetAdapter.viewHolder> {
    List<ProducetModel> itemsList;
    OnItemClick itemClick;

    public ProducetAdapter(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(itemsList.get(position).getTitle());
        holder.price.setText(itemsList.get(position).getPrice().toString());
        holder.catagory.setText(itemsList.get(position).getCategory());
        Picasso.get().load(itemsList.get(position).getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(itemsList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void setItemsList(List<ProducetModel> itemsList) {
        this.itemsList = itemsList;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image,add;
        TextView title,price,catagory;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tx_title);
            price=itemView.findViewById(R.id.tx_price);
            catagory=itemView.findViewById(R.id.tx_catagory);
            image=itemView.findViewById(R.id.img_product);
            add=itemView.findViewById(R.id.iv_fav);
        }
    }
}
