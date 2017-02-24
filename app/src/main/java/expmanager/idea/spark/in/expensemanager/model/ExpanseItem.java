package expmanager.idea.spark.in.expensemanager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class ExpanseItem implements Parcelable {

    private String name;
    private String quantity;
    private String cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public ExpanseItem(String name,String quantity,String cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    protected ExpanseItem(Parcel in) {
        name = in.readString();
        quantity = in.readString();
        cost = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(quantity);
        dest.writeString(cost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExpanseItem> CREATOR = new Creator<ExpanseItem>() {
        @Override
        public ExpanseItem createFromParcel(Parcel in) {
            return new ExpanseItem(in);
        }

        @Override
        public ExpanseItem[] newArray(int size) {
            return new ExpanseItem[size];
        }
    };
}
