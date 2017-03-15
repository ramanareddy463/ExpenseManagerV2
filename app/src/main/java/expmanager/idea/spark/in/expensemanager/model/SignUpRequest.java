package expmanager.idea.spark.in.expensemanager.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/13/2017.
 */

public class SignUpRequest {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("device_id")
    @Expose
    private String deviceId;


    public SignUpRequest(String email,String username, String password, String deviceId){

        this.email = email;
        this.username = username;
        this.password = password;
        this.deviceId = deviceId;
    }

}
