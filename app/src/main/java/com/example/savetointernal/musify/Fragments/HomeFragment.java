package com.example.savetointernal.musify.Fragments;


import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.savetointernal.musify.Fragments.Models.SongInfoModel;
import com.example.savetointernal.musify.R;
import com.example.savetointernal.musify.Fragments.Adapters.albums_Adapter;
import com.example.savetointernal.musify.Fragments.Models.albums_model;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    albums_Adapter adapter;
    List<albums_model>list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  view;
        view =inflater.inflate(R.layout.fragment_home, container, false);

        checkUserPermission();


        recyclerView=view.findViewById(R.id.albumRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.HORIZONTAL,false));

        adapter=new albums_Adapter(list,getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);




        return view;


    }
    private void checkUserPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
        loadAlbums();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   loadAlbums();
                }else{
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkUserPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }

    }

    private void loadAlbums(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor = getContext().getContentResolver().query(uri,null,selection,null,null);

        list=new ArrayList<>();

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                   /* final String _id = MediaStore.Audio.Albums._ID;
                    final String album_name = MediaStore.Audio.Albums.ALBUM;
                    final String artist = MediaStore.Audio.Albums.ARTIST;
                    final String albumart = MediaStore.Audio.Albums.ALBUM_ART;
                    final String tracks = MediaStore.Audio.Albums.NUMBER_OF_SONGS;*/


                    String album_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
                    String artist_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST));
                    long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);


                    albums_model model=new albums_model(albumArtUri,album_name);
                    list.add(model);



                }while (cursor.moveToNext());
            }

            cursor.close();


        }
    }

}
