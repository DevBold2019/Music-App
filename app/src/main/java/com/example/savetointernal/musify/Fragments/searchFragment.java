package com.example.savetointernal.musify.Fragments;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
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

import java.io.IOException;

import retrofit2.Retrofit;

public class searchFragment extends Fragment {

    ImageView imageView;
    Retrofit retrofit;

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

               searchAsong();

           }
       }

       //Lynnea.m

        );




        return view;
    }

    public void searchAsong(){



        String audioUrl = "https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3";

        MediaPlayer mediaPlayer = new MediaPlayer();


        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            mediaPlayer.setDataSource(getContext(), Uri.parse(audioUrl));
            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(getContext(),"Playing",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
