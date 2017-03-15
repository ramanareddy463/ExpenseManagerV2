package expmanager.idea.spark.in.expensemanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/15/2017.
 */

public class AddExpenseRequest {

    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("amount")
    @Expose
    private Integer amount;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("description")
    @Expose
    private String description;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
