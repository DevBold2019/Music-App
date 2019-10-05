package com.example.savetointernal.musify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MusicInterface extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_interface);


        b1=(Button) findViewById(R.id.fav);
        b2=(Button) findViewById(R.id.play);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                b1.setBackgroundResource(R.drawable.ic_favorite);

                    }
        });







    }


    public void pause(View view) {

              if (b2.getBackground().equals(R.drawable.ic_play)){

                  b2.setBackgroundResource(R.drawable.ic_pause_black);
              }
              else {
                      b2.setBackgroundResource(R.drawable.ic_play);


                  return;

        }









    }
}
