package com.azzdorfrobotics.android.testio.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class LoginResponse {

    @SerializedName("token") @Expose private String token;

    public String getToken() {
        return token;
    }
}
