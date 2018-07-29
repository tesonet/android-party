package lt.tesonet.model;

import com.google.gson.annotations.SerializedName;

public class Server {

    @SerializedName("name")
    private String name;

    @SerializedName("distance")
    private int distance;

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }
}
