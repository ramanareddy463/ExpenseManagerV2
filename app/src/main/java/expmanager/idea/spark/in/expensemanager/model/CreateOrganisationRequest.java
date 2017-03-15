package expmanager.idea.spark.in.expensemanager.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/13/2017.
 */

public class CreateOrganisationRequest {


    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("company_name")
    @Expose
    private String companyName;



    public CreateOrganisationRequest(String companyName, String deviceId){

        this.deviceId = deviceId;
        this.companyName = companyName;

    }

}
