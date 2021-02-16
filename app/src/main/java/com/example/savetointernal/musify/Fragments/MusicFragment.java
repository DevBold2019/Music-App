package com.example.savetointernal.musify.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savetointernal.musify.MainActivity;
import com.example.savetointernal.musify.MusicInterface;
import com.example.savetointernal.musify.R;
import com.example.savetointernal.musify.Fragments.Adapters.MyAdapter;
import com.example.savetointernal.musify.Fragments.Models.SongInfoModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class MusicFragment extends Fragment {

    ArrayList<SongInfoModel> songs;
    RecyclerView recyclerView;
    SeekBar seekBar;
    MediaPlayer mediaPlay;
    MyAdapter songAdapter;
    TextView SName;
    TextView AName;
    ImageButton imb,imb1,next;
    int length;
    ConstraintLayout constraintLayout;
    CardView cardView;
    boolean isItPaused;
    ArrayList<SongInfoModel> songsList;
    int posit,times=1;
    boolean isLoopActive=false;
    Random random=new Random();
    Thread thread = new showProgress();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_music, container, false);


        SName=view.findViewById(R.id.displayPlayingSongName);
        SName.setSelected(true);
        AName=view.findViewById(R.id.displayPlayingSongArtist);
        cardView=view.findViewById(R.id.miniCard);
        constraintLayout= view.findViewById(R.id.rela);


        recyclerView = view.findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        seekBar = view.findViewById(R.id.seekbar);
        imb=view.findViewById(R.id.playMusic);
        imb1=view.findViewById(R.id.pauseMusic);
        next=view.findViewById(R.id.nextMusic);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        songsList = intent.getParcelableArrayListExtra("songs");

        Toast.makeText(getContext(),"SIZE IS :\t"+songsList.size(),Toast.LENGTH_SHORT).show();


        songs=new ArrayList<>();
        songAdapter = new MyAdapter(getContext(),songsList);
        recyclerView.setAdapter(songAdapter);

        Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(songAdapter.getItemCount()-1);


        songAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LinearLayout l, View view, final SongInfoModel obj, final int position) {

                constraintLayout.setVisibility(View.VISIBLE);
                imb.setVisibility(View.GONE);
                imb1.setVisibility(View.GONE);
                posit=position;


                if(mediaPlay==null){
                    seekBar.setProgress(0);
                }else{

                    mediaPlay.stop();
                    mediaPlay=null;


                }
                        try {
                            mediaPlay = new MediaPlayer();
                            mediaPlay.setDataSource(obj.getSongUrl());

                            AName.setText(obj.getArtistname());
                            SName.setText(obj.getSongname());

                            mediaPlay.prepareAsync();

                            mediaPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                                @Override
                                public void onPrepared(final MediaPlayer mediaPlayer) {

                                    mediaPlayer.start();
                                    imb1.setVisibility(View.VISIBLE);

                                    Log.d("DURATION is:",""+mediaPlay.getDuration()/1000/60);

                                    seekBar.setMax(mediaPlay.getDuration());
                                    seekBar.setProgress(mediaPlayer.getCurrentPosition());

                                }
                            });


                        }
                        catch (Exception e){
                        }

            }
        });


        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imb1.setVisibility(View.GONE);
                imb.setVisibility(View.VISIBLE);
                isItPaused=true;

                mediaPlay.pause();




            }
        });


        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imb.setVisibility(View.GONE);
                imb1.setVisibility(View.VISIBLE);
                isItPaused=false;

                length=seekBar.getProgress();

                mediaPlay.seekTo(length);
                mediaPlay.start();



            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                seekBar.setProgress(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (!isItPaused){
                    mediaPlay.seekTo(seekBar.getProgress());
                    mediaPlay.start();
                    return;
                }
                int posy=seekBar.getProgress();
                seekBar.setProgress(posy);



            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlay.stop();
                mediaPlay.reset();
                mediaPlay=null;

                int nextPos= posit + times++;
                int size=songsList.size();


                if (isLoopActive){

                   int randInt=random.nextInt(50);
                    int nextPosLoop= posit + randInt;

                    if (nextPosLoop > songsList.size()){

                        nextPosLoop=randInt;

                    }

                    Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(nextPosLoop);
                    //playNextMusic(nextPos);
                    return;
                }

                if (nextPos >= size){

                    posit=0;
                    nextPos=posit;
                    Log.d("TIMES IS :", ""+nextPos);
                    Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(nextPos);
                    playNextMusic(nextPos);
                    seekBar.setProgress(0);
                    times=1;


                }else{

                    Log.d("SIZE IS :", ""+nextPos);
                    playNextMusic(nextPos);

                }




            }
        });



        thread.start();



        return view;

    }

    public class showProgress extends Thread {


        @Override
        public void run() {

            while (true){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mediaPlay !=null){


                    seekBar.post(new Runnable() {
                        @Override
                        public void run() {

                            if (!isItPaused){
                                seekBar.setProgress(mediaPlay.getCurrentPosition());
                                return;
                            }
                            int pos=seekBar.getProgress();
                            seekBar.setProgress(pos);



                        }
                    });
                }else{
                    seekBar.setProgress(0);
                }


            }
            
        }
    }

    public void playNextMusic( int position){

        imb.setVisibility(View.GONE);
        imb1.setVisibility(View.GONE);

        mediaPlay=new MediaPlayer();

        try {

            AName.setText(songsList.get(position).getArtistname());
            SName.setText(songsList.get(position).getSongname());

            mediaPlay.setDataSource(songsList.get(position).getSongUrl());
            mediaPlay.prepareAsync();
            mediaPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    mediaPlayer.start();
                    imb1.setVisibility(View.VISIBLE);
                }
            });



        }catch (Exception e){

        }


    }









}
