package com.example.savetointernal.musify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MusicInterface extends AppCompatActivity {

    Button b1,b2,b4;
    FloatingActionButton b3;
    String songName,songArtist;
    int length,maxlength;
    TextView nameSong,Artistsong;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_interface);

      /*  bundle.putString("songName",obj.getSongname());
        bundle.putString("artistName",obj.getArtistname());*/


        Bundle bundle;
        bundle=getIntent().getExtras();
        songName=bundle.getString("songName");
        length=bundle.getInt("position");
        maxlength=bundle.getInt("Maxposition");
        songArtist=bundle.getString("artistName");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        b1= findViewById(R.id.addFavorite);
        b2= findViewById(R.id.addFavoriteFill);
        b3= findViewById(R.id.play);
        b3.setImageResource(R.drawable.ic_pause_black);

        seekBar=findViewById(R.id.seek);
        Toast.makeText(this,""+length,Toast.LENGTH_SHORT).show();


        seekBar.setMax(0);
        seekBar.setProgress(length);
        seekBar.refreshDrawableState();

        nameSong=findViewById(R.id.songNameHere);
        Artistsong=findViewById(R.id.songTitle);

        nameSong.setText(songName);
        Artistsong.setText(songArtist);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               hide();
                Toast.makeText(getApplicationContext(),"Added to likes",Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide1();
                Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b3.setImageResource(R.drawable.ic_play);
                Toast.makeText(getApplicationContext(),"Playing",Toast.LENGTH_SHORT).show();

                changeIcon();

            }
        });




    }
    public void changeIcon(){

        b3.setImageResource(R.drawable.ic_play);
            Toast.makeText(getApplicationContext(),"Paused",Toast.LENGTH_SHORT).show();
        }




    public void hide(){
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.VISIBLE);

    }

    public void hide1(){
        b2.setVisibility(View.GONE);
        b1.setVisibility(View.VISIBLE);

    }


    public void pause(View view) {


    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(getApplicationContext(),MainWindow.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }
}
