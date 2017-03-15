package expmanager.idea.spark.in.expensemanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramana.Reddy on 3/15/2017.
 */

public class AddStaffRequest {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("shift_day_from")
    @Expose
    private String shiftDayFrom;

    @SerializedName("shift_day_to")
    @Expose
    private String shiftDayTo;

    @SerializedName("shift_time_from")
    @Expose
    private String shiftTimeFrom;

    @SerializedName("shift_time_to")
    @Expose
    private String shiftTimeTo;

    @SerializedName("started")
    @Expose
    private String started;

    @SerializedName("salary_type")
    @Expose
    private String salaryType;
    @SerializedName("salary")
    @Expose
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShiftDayFrom() {
        return shiftDayFrom;
    }

    public void setShiftDayFrom(String shiftDayFrom) {
        this.shiftDayFrom = shiftDayFrom;
    }

    public String getShiftDayTo() {
        return shiftDayTo;
    }

    public void setShiftDayTo(String shiftDayTo) {
        this.shiftDayTo = shiftDayTo;
    }

    public String getShiftTimeFrom() {
        return shiftTimeFrom;
    }

    public void setShiftTimeFrom(String shiftTimeFrom) {
        this.shiftTimeFrom = shiftTimeFrom;
    }

    public String getShiftTimeTo() {
        return shiftTimeTo;
    }

    public void setShiftTimeTo(String shiftTimeTo) {
        this.shiftTimeTo = shiftTimeTo;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
