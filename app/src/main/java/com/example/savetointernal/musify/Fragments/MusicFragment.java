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
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savetointernal.musify.MusicInterface;
import com.example.savetointernal.musify.R;
import com.example.savetointernal.musify.Fragments.Adapters.MyAdapter;
import com.example.savetointernal.musify.Fragments.Models.SongInfoModel;

import java.io.IOException;
import java.util.ArrayList;


public class MusicFragment extends Fragment {

    ArrayList<SongInfoModel> songs;
    // public RecyclerView.Adapter adapter;
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
    int maximum;
    CardView cardView;
    int songIndex=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_music, container, false);


        SName=view.findViewById(R.id.displayPlayingSongName);
        SName.setSelected(true);
        AName=view.findViewById(R.id.displayPlayingSongArtist);
        cardView=view.findViewById(R.id.miniCard);




        recyclerView = view.findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        seekBar = view.findViewById(R.id.seekbar);
        imb=view.findViewById(R.id.playMusic);
        imb1=view.findViewById(R.id.pauseMusic);
        next=view.findViewById(R.id.nextMusic);






        songs=new ArrayList<>();
        songAdapter = new MyAdapter(getContext(),songs);
        recyclerView.setAdapter(songAdapter);




        songAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final LinearLayout l, View view, final SongInfoModel obj, int position) {


                imb.setVisibility(View.GONE);
                imb1.setVisibility(View.GONE);

                    if(mediaPlayer==null){

                        Toast.makeText(getContext(),"Select a song",Toast.LENGTH_SHORT).show();

                    }else{

                        mediaPlayer.stop();
                        mediaPlayer=null;

                    }


                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mediaPlayer = new MediaPlayer();
                                mediaPlayer.setDataSource(obj.getSongUrl());
                                AName.setText(obj.getArtistname());
                                SName.setText(obj.getSongname());


                                mediaPlayer.prepareAsync();
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(final MediaPlayer mp) {


                                        maximum = mediaPlayer.getDuration();

                                        mp.start();

                                        //imb.setVisibility(View.VISIBLE);
                                        imb1.setVisibility(View.VISIBLE);

                                        if (mp.getCurrentPosition() < 0) {

                                            seekBar.setMax(0);
                                        }
                                        seekBar.setProgress(0);
                                        seekBar.setMax(mediaPlayer.getDuration());

                                        imb.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                mp.pause();
                                                //  mp.setNextMediaPlayer();
                                                mp.getCurrentPosition();
                                                //b.setText("paused");
                                                imb.setVisibility(View.GONE);
                                                imb1.setVisibility(View.VISIBLE);

                                            }
                                        });


                                        imb1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                imb1.setVisibility(View.GONE);
                                                imb.setVisibility(View.VISIBLE);

                                                length = mp.getCurrentPosition();
                                                mp.seekTo(length);
                                                mp.start();


                                            }
                                        });

                                        next.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Toast.makeText(getContext(), "loading ..", Toast.LENGTH_SHORT).show();
                                                mediaPlayer.stop();


                                            }
                                        });

                                        cardView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Intent intent = new Intent(getContext(), MusicInterface.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("songName", obj.getSongname());
                                                bundle.putInt("position", len);
                                                bundle.putInt("Maxposition", maximum);
                                                bundle.putString("artistName", obj.getArtistname());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }
                                        });


                                    }
                                });

                                // b.setText("Stop");
                                imb1.setVisibility(View.VISIBLE);


                            }catch (Exception e){}
                        }

                    };
                    myHandler.postDelayed(runnable,100);

                }
        });


        ///
        checkUserPermission();

        Thread t = new runThread();
        t.start();




 return view;

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
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            int currentPosition=seekBar.getProgress();

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                           int Value= seekBar.getProgress();
                            mediaPlayer.seekTo(Value+len);
                        }
                    });

                    Log.d("Runwa", "run: " + mediaPlayer.getCurrentPosition());
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
        loadSongs();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    loadSongs();
                }else{
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkUserPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }

    }

    private void loadSongs(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor = getContext().getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                   // int pic=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));

                    SongInfoModel s = new SongInfoModel(name,artist,url);
                    songs.add(s);

                }while (cursor.moveToNext());
            }

            cursor.close();


        }
    }







}
