package com.example.savetointernal.musify.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.savetointernal.musify.R;
import com.example.savetointernal.musify.Testing.albums_Adapter;
import com.example.savetointernal.musify.Testing.albums_model;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    albums_Adapter adapter;
    List<albums_model>list;
    albums_model model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  view;
        view =inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.albumRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.HORIZONTAL,false));


        list=new ArrayList<>();
        list.add(new albums_model(R.drawable.ciara,"I Bet"));
        list.add(new albums_model(R.drawable.ciara,"I Bet"));
        list.add(new albums_model(R.drawable.alex,"I Bet"));
        list.add(new albums_model(R.drawable.ciara,"I Bet"));
        list.add(new albums_model(R.drawable.alex,"I Bet"));

        list.add(model);

        adapter=new albums_Adapter(list,getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);




        return view;


    }
    public void loadAlbums(){


    }

}
