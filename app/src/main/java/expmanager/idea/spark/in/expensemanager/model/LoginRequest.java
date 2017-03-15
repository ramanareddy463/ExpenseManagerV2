package expmanager.idea.spark.in.expensemanager.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/13/2017.
 */

public class LoginRequest {


    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("username")
    @Expose
    private String username;


    public LoginRequest(String username,String password,String deviceId){

        this.deviceId = deviceId;
        this.password = password;
        this.username = username;

    }

}
