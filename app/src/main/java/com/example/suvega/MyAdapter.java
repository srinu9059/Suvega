package com.example.suvega;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Items> items;

    public MyAdapter(Context context, List<Items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_card_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.vehicleNumber.setText(items.get(position).getVehicleNumber());
        holder.location.setText(items.get(position).getLocation());
        holder.navigation.setImageResource(items.get(position).getNavigation());
        holder.profilePic.setImageResource(items.get(position).getProfilePic());
        holder.name.setText(items.get(position).getName());
        holder.id.setText(items.get(position).getId());
        holder.phone.setImageResource(items.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
