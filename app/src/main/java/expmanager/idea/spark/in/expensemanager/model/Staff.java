package expmanager.idea.spark.in.expensemanager.model;

/**
 * Created by Haresh.Veldurty on 2/24/2017.
 */

public class Staff {

    String staff_name;
    String shift_days1;
    String shift_days2;
    String shift_time1;
    String shift_time2;
    String staff_startdate;
    String price_perhr;

    public Staff(String s) {
        this.staff_name = s;
    }
    public Staff() {

    }
    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getShift_days1() {
        return shift_days1;
    }

    public void setShift_days1(String shift_days1) {
        this.shift_days1 = shift_days1;
    }

    public String getShift_days2() {
        return shift_days2;
    }

    public void setShift_days2(String shift_days2) {
        this.shift_days2 = shift_days2;
    }

    public String getShift_time1() {
        return shift_time1;
    }

    public void setShift_time1(String shift_time1) {
        this.shift_time1 = shift_time1;
    }

    public String getShift_time2() {
        return shift_time2;
    }

    public void setShift_time2(String shift_time2) {
        this.shift_time2 = shift_time2;
    }

    public String getStaff_startdate() {
        return staff_startdate;
    }

    public void setStaff_startdate(String staff_startdate) {
        this.staff_startdate = staff_startdate;
    }

    public String getPrice_perhr() {
        return price_perhr;
    }

    public void setPrice_perhr(String price_perhr) {
        this.price_perhr = price_perhr;
    }



}
