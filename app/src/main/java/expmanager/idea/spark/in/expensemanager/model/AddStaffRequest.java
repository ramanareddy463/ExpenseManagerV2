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

    @SerializedName("email")
    @Expose
    String staff_email;

    @SerializedName("address")
    @Expose
    String staff_address;

    @SerializedName("designation")
    @Expose
    String staff_designation;

    @SerializedName("phone_number")
    @Expose
    String staff_phonenumber;

    @SerializedName("profile_image")
    @Expose
    String staff_photo;




    public String getStaff_email() {
        return staff_email;
    }

    public void setStaff_email(String staff_email) {
        this.staff_email = staff_email;
    }

    public String getStaff_address() {
        return staff_address;
    }

    public void setStaff_address(String staff_address) {
        this.staff_address = staff_address;
    }

    public String getStaff_designation() {
        return staff_designation;
    }

    public void setStaff_designation(String staff_designation) {
        this.staff_designation = staff_designation;
    }

    public String getStaff_phonenumber() {
        return staff_phonenumber;
    }

    public void setStaff_phonenumber(String staff_phonenumber) {
        this.staff_phonenumber = staff_phonenumber;
    }

    public String getStaff_photo() {
        return staff_photo;
    }

    public void setStaff_photo(String staff_photo) {
        this.staff_photo = staff_photo;
    }




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
