package com.example.savetointernal.musify.Old;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savetointernal.musify.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    public List<MusicInfo> songs;
    public Context context;

    OnitemClickListener onitemClickListener;

    public SongAdapter(List<MusicInfo> songs,Context context) {
        this.songs = songs;
        this.context = context;
    }


    public interface  OnitemClickListener{

        void onItemClick(Button b,View v,MusicInfo obj,int position);
    }

    public void setOnitemClickListener(OnitemClickListener onitemClickListener){

        this.onitemClickListener=onitemClickListener;

    }


    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);

        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongHolder songHolder, final int i) {

        final MusicInfo mf=songs.get(i);

        songHolder.tv1.setText(mf.artistName);
        songHolder.tv2.setText(mf.songName);
        songHolder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onitemClickListener!=null){

                    onitemClickListener.onItemClick(songHolder.b1,v,mf,i);

                    Toast.makeText(context, "playing this song in a while \t"+songHolder.tv2.getText()+"", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public int getItemCount() {

        return songs.size();
    }

    class SongHolder extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3;
        Button b1;

        SongHolder(@NonNull View itemView) {
            super(itemView);

            tv1=itemView.findViewById(R.id.ArtistName);
            tv2=itemView.findViewById(R.id.SongName);
            b1=itemView.findViewById(R.id.playMusic);

        }
    }
}
