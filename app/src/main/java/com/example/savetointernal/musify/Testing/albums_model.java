package com.example.savetointernal.musify.Testing;

public class albums_model {
    int albumPic;
    String albumName;

    public albums_model(int albumPic, String albumName) {
        this.albumPic = albumPic;
        this.albumName = albumName;
    }

    public int getAlbumPic() {
        return albumPic;
    }

    public String getAlbumName() {
        return albumName;
    }
}
