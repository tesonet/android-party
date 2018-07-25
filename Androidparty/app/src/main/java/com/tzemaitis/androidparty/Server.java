package com.tzemaitis.androidparty;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tzemaitis on 25/07/18.
 */

public class Server implements Parcelable {
    @SerializedName("name")
    private String name;

    @SerializedName("distance")
    private String distance;

    protected Server(Parcel in) {
        name = in.readString();
        distance = in.readString();
    }

    public static final Creator<Server> CREATOR = new Creator<Server>() {
        @Override
        public Server createFromParcel(Parcel in) {
            return new Server(in);
        }

        @Override
        public Server[] newArray(int size) {
            return new Server[size];
        }
    };

    public String getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(distance);
    }
}
