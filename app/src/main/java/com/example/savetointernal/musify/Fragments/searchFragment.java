package com.example.savetointernal.musify.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.savetointernal.musify.R;
import com.squareup.picasso.Picasso;

public class searchFragment extends Fragment {

    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view=LayoutInflater.from(getContext()).inflate(R.layout.search_layout,container,false);




        return view;
    }

}
