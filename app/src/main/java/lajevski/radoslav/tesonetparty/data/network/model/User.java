package lajevski.radoslav.tesonetparty.data.network.model;

/**
 * Created by Radoslav on 1/16/2018.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     *
     * @param email
     * @param password
     */
    public User(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}