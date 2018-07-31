package lt.tesonet.model;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
