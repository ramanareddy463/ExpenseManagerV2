package expmanager.idea.spark.in.expensemanager.model;

/**
 * Created by Haresh.Veldurty on 2/23/2017.
 */

public class TanExpenses {

    String  category;
    String when;
    String price;

    public TanExpenses(String s, String s1, String s2) {
        this.category = s;
        this.when = s1;
        this.price=s2;
    }
    public TanExpenses() {

    }
    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
