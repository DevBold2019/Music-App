package com.example.savetointernal.musify;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyMusic extends AppCompatActivity {

    public List<MusicInfo> songs;
  // public RecyclerView.Adapter adapter;
    RecyclerView recyclerView; //recy
    SeekBar seekBar; //seekbar
    MediaPlayer mediaPlayer;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);

        recyclerView = (RecyclerView) findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //seekBar=(SeekBar) findViewById(R.id.seekbar);


        songs = new ArrayList<>();

        for (int i=0;i<=100;i++){

        loadSongs();
    }

          /* MusicInfo musicInfo=new MusicInfo("Edsheran","shape Of You","Shazam.com");
           songs.add(musicInfo);
*/
      // }

        songAdapter=new SongAdapter(songs,this);
        recyclerView.setAdapter(songAdapter);



       /* songAdapter.setOnitemClickListener(new SongAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, MusicInfo obj, int position) {

                mediaPlayer=new MediaPlayer();

                try {

                    mediaPlayer.setDataSource(obj.getSongUrl());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                        @Override
                        public void onPrepared(MediaPlayer mp) {

                            mp.start();
                            b.setText("Stop");

                        }
                    });






                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

*/

    }



    public void loadSongs(){

        Uri musicUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String pick=MediaStore.Audio.Media.IS_MUSIC;
        Cursor cursor= getContentResolver().query(musicUri,null,pick,null,null);

        if (cursor !=null){

            if (cursor.moveToFirst()){

                do {

                String songname=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String artistame=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                MusicInfo  musicInfo=new MusicInfo(songname,artistame,url);
                songs.add(musicInfo);

                }while (cursor.moveToNext());

            }
            cursor.close();
            songAdapter=new SongAdapter(songs,this);
        }
    }

    public void showMusicScreen(View view) {


        Intent intent=new Intent(MyMusic.this,MusicInterface.class);
        startActivity(intent);
        finish();
    }
}
