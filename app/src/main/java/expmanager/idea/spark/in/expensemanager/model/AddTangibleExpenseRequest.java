package expmanager.idea.spark.in.expensemanager.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/13/2017.
 */

public class AddTangibleExpenseRequest {


    @SerializedName("expense_name")
    @Expose
    private String expenseName;
    @SerializedName("expense_type")
    @Expose
    private String expenseType;
    @SerializedName("amount")
    @Expose
    private String amount;



    public AddTangibleExpenseRequest(String expenseName, String expenseType, String amount){

        this.expenseName = expenseName;
        this.expenseType = expenseType;
        this.amount = amount;

    }

}
