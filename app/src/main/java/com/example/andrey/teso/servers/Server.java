package com.example.andrey.teso.servers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Server implements Parcelable {
    protected int identifier, distance;
    protected String location;

    public Server(int id, String location, int dist) {
        this.identifier = id;
        this.location = location;
        this.distance = dist;
    }

    public String toString() {
        return this.location + " #" + this.identifier + " : " + this.distance;
    }

    private Server (Parcel parcel) {
        this.identifier = parcel.readInt();
        this.location = parcel.readString();
        this.distance = parcel.readInt();
    }

    protected int getIdentifier() {
        return identifier;
    }

    protected int getDistance() {
        return distance;
    }

    protected String getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(identifier);
        dest.writeString(location);
        dest.writeInt(distance);
    }

    public static Creator<Server> CREATOR = new Creator<Server>() {
        @Override
        public Server createFromParcel(Parcel source) {
            return new Server(source);
        }

        @Override
        public Server[] newArray(int size) {
            return new Server[size];
        }

    };
}

class SortById implements Comparator<Server> {
    public int compare(Server a, Server b) {
        return a.identifier - b.identifier;
    }
}

class SortByDistance implements Comparator<Server> {
    public int compare(Server a, Server b)
    {
        return a.distance - b.distance;
    }
}

class SortByLocation implements Comparator<Server> {
    public int compare(Server a, Server b) {
        int res = String.CASE_INSENSITIVE_ORDER.compare(a.location, b.location);
        if (res == 0) {
            res = a.location.compareTo(b.location);
        }
            return res;
        }
}
