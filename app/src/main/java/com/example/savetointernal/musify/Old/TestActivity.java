package com.example.savetointernal.musify.Old;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.savetointernal.musify.R;
import com.squareup.picasso.Picasso;

public class TestActivity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imageView=findViewById(R.id.imageView);
        Picasso.get()
                .load(R.drawable.concert1)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
