package com.example.finalproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.ProfileModel;

import java.util.ArrayList;

public class ProfileAdaptar extends RecyclerView.Adapter<ProfileAdaptar.viewHolder> {
    ArrayList<ProfileModel> list;
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.imageView.setImageResource(list.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<ProfileModel> list) {
        this.list = list;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_title);
            imageView=itemView.findViewById(R.id.iv_pro);
        }
    }
}
