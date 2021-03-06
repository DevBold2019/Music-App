package com.example.savetointernal.musify.Fragments.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.savetointernal.musify.Fragments.Models.SongInfoModel;
import com.example.savetointernal.musify.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SongHolder> {

        private ArrayList<SongInfoModel> _songs = new ArrayList<SongInfoModel>();
        private Context context;
        private OnItemClickListener mOnItemClickListener;

        public MyAdapter(Context context, ArrayList<SongInfoModel> songs) {
            this.context = context;
            this._songs = songs;
        }

        public interface OnItemClickListener {
            void onItemClick(LinearLayout l , View view, SongInfoModel obj, int position);
        }

        public  void setOnItemClickListener(final OnItemClickListener mItemClickListener) {

            this.mOnItemClickListener = mItemClickListener;
        }


        @Override
        public SongHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View myView = LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
            return new SongHolder(myView);
        }

        @Override
        public void onBindViewHolder(final SongHolder songHolder, final int i) {
            final SongInfoModel s = _songs.get(i);

            songHolder.tvSongName.setText(s.getSongname());
            songHolder.tvSongArtist.setText(s.getArtistname());

            songHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(songHolder.linearLayout,v, s, i);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return _songs.size();
        }

        public class SongHolder extends RecyclerView.ViewHolder {

            TextView tvSongName,tvSongArtist;
            Button btnAction;
            LinearLayout linearLayout;

            public SongHolder(View itemView) {
                super(itemView);
                tvSongName = (TextView) itemView.findViewById(R.id.SongName);
                tvSongArtist = (TextView) itemView.findViewById(R.id.ArtistName);
                btnAction = (Button) itemView.findViewById(R.id.playMusic);
                linearLayout=itemView.findViewById(R.id.hory);
            }
        }
}
