package com.example.suvega;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView vehicleNumber;
    TextView location;
    ImageView navigation;
    ImageView profilePic;
    TextView name;
    TextView id;
    ImageView phone;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        vehicleNumber = itemView.findViewById(R.id.textView_vehicle_number);
        location = itemView.findViewById(R.id.textView_current_location);
        navigation = itemView.findViewById(R.id.image_navigation);
        profilePic = itemView.findViewById(R.id.image_profile);
        name = itemView.findViewById(R.id.textView_name);
        id = itemView.findViewById(R.id.textView_id);
        phone = itemView.findViewById(R.id.image_phone);
    }
}
