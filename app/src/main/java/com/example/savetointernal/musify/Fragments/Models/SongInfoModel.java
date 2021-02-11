package com.example.savetointernal.musify.Fragments.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SongInfoModel implements Parcelable {
        private String Songname;
        private String Artistname;
        private String SongUrl;

        public SongInfoModel(String songname, String artistname, String songUrl) {
            Songname = songname;
            Artistname = artistname;
            SongUrl = songUrl;
        }

    protected SongInfoModel(Parcel in) {
        Songname = in.readString();
        Artistname = in.readString();
        SongUrl = in.readString();
    }

    public static final Creator<SongInfoModel> CREATOR = new Creator<SongInfoModel>() {
        @Override
        public SongInfoModel createFromParcel(Parcel in) {
            return new SongInfoModel(in);
        }

        @Override
        public SongInfoModel[] newArray(int size) {
            return new SongInfoModel[size];
        }
    };

    public String getSongname() {
            return Songname;
        }

        public void setSongname(String songname) {
            Songname = songname;
        }

        public String getArtistname() {

            return Artistname;
        }

        public void setArtistname(String artistname) {

            Artistname = artistname;
        }

        public String getSongUrl() {

            return SongUrl;
        }

        public void setSongUrl(String songUrl) {

            SongUrl = songUrl;
        }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Songname);
        parcel.writeString(Artistname);
        parcel.writeString(SongUrl);
    }
}
