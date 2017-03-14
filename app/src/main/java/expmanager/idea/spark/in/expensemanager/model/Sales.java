package expmanager.idea.spark.in.expensemanager.model;

/**
 * Created by Haresh.Veldurty on 3/14/2017.
 */

public class Sales {

    public Sales(String s, String s1, String s2) {
        this.saletype = s;
        this.date = s1;
        this.sameamount = s2;
    }

    public Sales() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }

    public String getSameamount() {
        return sameamount;
    }

    public void setSameamount(String sameamount) {
        this.sameamount = sameamount;
    }

    String date;
    String saletype;
    String sameamount;

}
