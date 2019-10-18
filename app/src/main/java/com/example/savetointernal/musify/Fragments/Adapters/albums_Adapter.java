package com.example.savetointernal.musify.Fragments.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.savetointernal.musify.Fragments.Models.albums_model;
import com.example.savetointernal.musify.R;

import java.util.List;

public class albums_Adapter extends RecyclerView.Adapter<albums_Adapter.viewholder> {

    List<albums_model>list;
    Context context;

    public albums_Adapter(List<albums_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public albums_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        view= LayoutInflater.from(context).inflate(R.layout.list_album_layout,viewGroup,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull albums_Adapter.viewholder viewholder, int i) {

        albums_model model=list.get(i);


        viewholder.imageView.setImageResource(model.getAlbumPic());
        viewholder.textView.setText(model.getAlbumName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.albumPicture);
            textView=itemView.findViewById(R.id.albumName);
        }
    }
}
