package com.example.savetointernal.musify;

public class MusicInfo {

    String artistName,songName,songUrl;

    public MusicInfo(String artistName, String songName, String songUrl) {
        this.artistName = artistName;
        this.songName = songName;
        this.songUrl = songUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
