package com.adeeva.movieworld.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItems implements Parcelable {
    private int id;
    private String title, release, overview, posterPath;

    public MovieItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release = object.getString("release_date");
            String posterPath = ("https://image.tmdb.org/t/p/w185" + object.getString("poster_path"));

            this.id = id;
            this.title = title;
            this.release = release;
            this.overview = overview;
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

    private MovieItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.release = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
}
