package com.example.suvega;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class SingleVehiclePopUp extends BottomSheetDialogFragment {
    private OnNavigationClickListener navigationClickListener;

    private ImageView imageViewNavigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.single_vehicle_popup, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewNavigation = view.findViewById(R.id.image_navigation_single_vehicle_popup);

    }

    // Method to get a reference to the ImageView
    public ImageView getImageViewNavigation() {
        return imageViewNavigation;
    }
    public interface OnNavigationClickListener {
        void onNavigationClick();

    }

}
