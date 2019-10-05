package com.example.savetointernal.musify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bootomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.search:
                        Toast.makeText(MainWindow.this,"Searching",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.musicmp3:

                        Intent intent=new Intent(MainWindow.this,MyMusic.class);
                        startActivity(intent);
                        finish();
                        break;

                }

                return true;
            }
        });
    }

    public void showMusicScreen(View view) {

        Intent intent=new Intent(MainWindow.this,MusicInterface.class);
        startActivity(intent);
        finish();
    }
}
