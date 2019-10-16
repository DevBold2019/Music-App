package com.example.savetointernal.musify;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.savetointernal.musify.Fragments.HomeFragment;
import com.example.savetointernal.musify.Fragments.MusicFragment;
import com.example.savetointernal.musify.Fragments.searchFragment;

public class MainWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bootomNav);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment=null;

                switch (menuItem.getItemId()){
                    case R.id.Home:
                        selectedFragment=new HomeFragment();

                        break;

                    case R.id.playlist:

                        selectedFragment=new MusicFragment();
                        break;

                    case R.id.search:

                        selectedFragment=new searchFragment();

                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                return true;
            }
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.Home); // change to whichever id should be default
        }
    }
}
