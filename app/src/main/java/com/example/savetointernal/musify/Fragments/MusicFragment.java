package com.example.savetointernal.musify.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.savetointernal.musify.MusicInfo;
import com.example.savetointernal.musify.R;
import com.example.savetointernal.musify.SongAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MusicFragment extends Fragment {


    public List<MusicInfo> songs;
    // public RecyclerView.Adapter adapter;
    RecyclerView recyclerView; //recy
    SeekBar seekBar; //seekbar
    MediaPlayer mediaPlayer;
    SongAdapter songAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_music, container, false);


        recyclerView = view.findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        seekBar = view.findViewById(R.id.seekbar);


        songs = new ArrayList<>();

          /* MusicInfo musicInfo=new MusicInfo("Edsheran","shape Of You","Shazam.com");
           songs.add(musicInfo);
*/
        // }


        final Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String pick = MediaStore.Audio.Media.IS_MUSIC;

        Cursor cursor = getActivity().getContentResolver().query(musicUri, null, pick, null, null);

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                do {

                    String songname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artistame = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    MusicInfo musicInfo = new MusicInfo(songname, artistame, url);
                    songs.add(musicInfo);

                } while (cursor.moveToNext());

            }
            cursor.close();
            songAdapter = new SongAdapter(songs, getActivity());
        }

        songAdapter = new SongAdapter(songs, getContext());
        recyclerView.setAdapter(songAdapter);


        songAdapter.setOnitemClickListener(new SongAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(Button b, View v, MusicInfo obj, int position) {

                position = mediaPlayer.getCurrentPosition();


                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mediaPlayer = MediaPlayer.create(getActivity(), musicUri);
                        mediaPlayer.start();

                        Toast.makeText(getActivity(), "loading music", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


        return view;
    }


    public void loadSongs() {

 /*   public void showMusicScreen(View view) {

        Intent intent=new Intent(getActivity(),MusicInterface.class);
        startActivity(intent);
        getActivity().finish();

    }
*/


    }

}
