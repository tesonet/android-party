package lt.zaltauskas.android_party.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Server implements Serializable {

    @SerializedName("name")
    private String name;


    public String getName() {
        return name;
    }

    @SerializedName("distance")
    private int distance;


    public int getDistance() {
        return distance;
    }

}
