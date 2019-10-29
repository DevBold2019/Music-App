package com.example.savetointernal.musify.Fragments.Models;

import android.net.Uri;

public class albums_model {
    Uri albumPic;
    String albumName;


    public albums_model(Uri albumPic, String albumName) {
        this.albumPic = albumPic;
        this.albumName = albumName;
    }


    public Uri getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(Uri albumPic) {
        this.albumPic = albumPic;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
