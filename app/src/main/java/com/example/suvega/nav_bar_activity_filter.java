package com.example.suvega;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class nav_bar_activity_filter extends BottomSheetDialogFragment {

private static OnFilterClick onFilterClick;

    public static nav_bar_activity_filter newInstance(OnFilterClick navigationClickListener ) {
        nav_bar_activity_filter fragment = new nav_bar_activity_filter();
        fragment.onFilterClick = onFilterClick;
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_bar_activity_filter, container, false);
        return view;
    }
    public interface OnFilterClick {
        void onNavigationClick();
    }
}
