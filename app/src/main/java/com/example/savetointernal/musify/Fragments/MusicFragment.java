package com.example.savetointernal.musify.Fragments;

import android.Manifest;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Objects;


public class MusicFragment extends Fragment {

    ArrayList<SongInfoModel> songs;
    RecyclerView recyclerView; //recy
    private Handler myHandler = new Handler();;
    SeekBar seekBar; //seekbar
    MediaPlayer mediaPlayer;
    MyAdapter songAdapter;
    TextView SName;
    TextView AName;
    ImageButton imb,imb1,next;
    int length,len;
    int playlist;
    ConstraintLayout constraintLayout;
    int maximum;
    CardView cardView;
    int songIndex=0;
    Runnable runnable;
    boolean isIt;

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
        final ArrayList<SongInfoModel> songsList = intent.getParcelableArrayListExtra("songs");

        Toast.makeText(getContext(),"SIZE IS :\t"+songsList.size(),Toast.LENGTH_SHORT).show();


        songs=new ArrayList<>();
        songAdapter = new MyAdapter(getContext(),songsList);
        recyclerView.setAdapter(songAdapter);


        songAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LinearLayout l, View view, final SongInfoModel obj, final int position) {

                constraintLayout.setVisibility(View.VISIBLE);
                imb.setVisibility(View.GONE);
                imb1.setVisibility(View.GONE);

                if(mediaPlayer==null){

                    Toast.makeText(getContext(),"Select a song",Toast.LENGTH_SHORT).show();

                }else{

                    mediaPlayer.stop();
                    mediaPlayer=null;

                }


                        try {
                            mediaPlayer = new MediaPlayer();
                            mediaPlayer.setDataSource(obj.getSongUrl());

                            AName.setText(obj.getArtistname());
                            SName.setText(obj.getSongname());

                            mediaPlayer.prepareAsync();
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                                @Override
                                public void onPrepared(final MediaPlayer mediaPlayer) {

                                    mediaPlayer.start();
                                    //imb.setVisibility(View.VISIBLE);
                                    imb1.setVisibility(View.VISIBLE);

                                    if (mediaPlayer.getCurrentPosition() < 0) {

                                        seekBar.setMax(0);
                                    }
                                    seekBar.setProgress(0);
                                    seekBar.setMax(mediaPlayer.getDuration());

                                    imb1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            imb1.setVisibility(View.GONE);
                                            imb.setVisibility(View.VISIBLE);

                                            mediaPlayer.pause();
                                            //  mp.setNextMediaPlayer();
                                            mediaPlayer.getCurrentPosition();
                                            //b.setText("paused");



                                        }
                                    });


                                    imb.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            imb.setVisibility(View.GONE);
                                            imb1.setVisibility(View.VISIBLE);

                                            length = mediaPlayer.getCurrentPosition();
                                            mediaPlayer.seekTo(length);
                                            mediaPlayer.start();


                                        }
                                    });

                                    next.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Toast.makeText(getContext(), "loading ..", Toast.LENGTH_SHORT).show();
                                            mediaPlayer.stop();

                                        }
                                    });

                                }
                            });


                        }
                        catch (Exception e){
                        }

            }
        });





        Thread thread = new showProgress();
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

                if (mediaPlayer !=null){

                   
                    seekBar.post(new Runnable() {
                        @Override
                        public void run() {

                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                    mediaPlayer.seekTo(seekBar.getProgress());
                                    mediaPlayer.start();
                                }
                            });

                        }
                    });
                }


            }





        }
    }
    public void handle(){



        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if (mediaPlayer !=null){
                        seekBar.post(new Runnable() {
                            @Override
                            public void run() {
                                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                Log.d("Number is:",""+mediaPlayer.getCurrentPosition());
                            }
                        });
                    }




            }
        };

        new Thread(runnable).start();





    }

    public class runThread extends Thread {


        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("Runwa", "run: " + 1);
                if (mediaPlayer != null) {
                    seekBar.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            len=mediaPlayer.getCurrentPosition();

                        }
                    });



                }
            }
        }

    }


    private void checkUserPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }


    }









}
