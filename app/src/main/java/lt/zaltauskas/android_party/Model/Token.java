package lt.zaltauskas.android_party.Model;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
