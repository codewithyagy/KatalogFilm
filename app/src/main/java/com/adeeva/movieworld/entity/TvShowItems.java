package com.adeeva.movieworld.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShowItems implements Parcelable {
    private int id;
    private String title, release, overview, posterPath;

    public TvShowItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("name");
            String overview = object.getString("overview");
            String release = object.getString("first_air_date");
            String posterPath = ("https://image.tmdb.org/t/p/w185" + object.getString("poster_path"));

            this.id = id;
            this.title = title;
            this.overview = overview;
            this.release = release;
            this.posterPath = posterPath;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.release);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
    }

    private TvShowItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.release = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<TvShowItems> CREATOR = new Parcelable.Creator<TvShowItems>() {
        @Override
        public TvShowItems createFromParcel(Parcel source) {
            return new TvShowItems(source);
        }

        @Override
        public TvShowItems[] newArray(int size) {
            return new TvShowItems[size];
        }
    };
}
