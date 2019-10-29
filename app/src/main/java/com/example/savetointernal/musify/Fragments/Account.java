package com.example.savetointernal.musify.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savetointernal.musify.R;

public class Account extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;


          view= inflater.inflate(R.layout.fragment_account, container, false);

          return view;
    }

}
