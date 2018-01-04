package com.azzdorfrobotics.android.testio.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@RealmClass public class Server implements RealmModel {

    @SerializedName("name") @Expose private String name;
    @SerializedName("distance") @Expose private String distance;

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }
}
