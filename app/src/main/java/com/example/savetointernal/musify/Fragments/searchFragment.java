package com.example.savetointernal.musify.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.savetointernal.musify.R;
import com.squareup.picasso.Picasso;

public class searchFragment extends Fragment {

    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view=LayoutInflater.from(getContext()).inflate(R.layout.search_layout,container,false);

        ImageView btn=view.findViewById(R.id.searchbtn);
        final EditText edit=view.findViewById(R.id.searcheditText);

        btn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               Toast.makeText(getContext(),"searching",Toast.LENGTH_SHORT).show();
               edit.setVisibility(View.VISIBLE);

           }
       }

        );




        return view;
    }

}
