package expmanager.idea.spark.in.expensemanager.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/13/2017.
 */

public class ForgotPassword {

    @SerializedName("email")
    @Expose
    private String email;



    public ForgotPassword(String email){

        this.email = email;

    }

}
