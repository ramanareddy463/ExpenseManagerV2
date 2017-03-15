package expmanager.idea.spark.in.expensemanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/14/2017.
 */

public class UpdateTangibleExpense {

    @SerializedName("tangible_expense")
    @Expose
    private AddTangibleExpenseRequest addTangibleExpenseRequest;
    @SerializedName("id")
    @Expose
    private int id;


    public UpdateTangibleExpense(AddTangibleExpenseRequest addTangibleExpenseRequest, int id){

        this.addTangibleExpenseRequest = addTangibleExpenseRequest;
        this.id = id;


    }

}
