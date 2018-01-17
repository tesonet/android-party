package lajevski.radoslav.tesonetparty.data.network.model;

/**
 * Created by Radoslav on 1/16/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Server {

    private static String KM_POSTFIX = " km";

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("distance")
    @Expose
    private Integer distance;

    /**
     * No args constructor for use in serialization
     */
    public Server() {
    }

    /**
     * @param distance
     * @param name
     */
    public Server(String name, Integer distance) {
        super();
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getStringDistance(){
        if (getDistance()!=null){
            return getDistance() + KM_POSTFIX;
        } else return null;
    }



    public void setDistance(Integer distance) {
        this.distance = distance;
    }

}