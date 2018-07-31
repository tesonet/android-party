package lt.tesonet.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
