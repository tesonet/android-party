package com.azzdorfrobotics.android.testio.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class LoginRequest {

    @SerializedName("username") @Expose private String userName;
    @SerializedName("password") @Expose private String password;

    private LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static LoginRequest getInstance(String userName, String password) {
        return new LoginRequest(userName, password);
    }
}
