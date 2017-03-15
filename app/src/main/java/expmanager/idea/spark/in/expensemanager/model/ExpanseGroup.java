package expmanager.idea.spark.in.expensemanager.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class ExpanseGroup extends ExpandableGroup<ExpanseItem> {

    private String name;
    private String count;
    private String totalCost;

    public ExpanseGroup(String title, List<ExpanseItem> items, String count,String totalCost) {
        super(title, items);
        this.count = count;
        this.totalCost = totalCost;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ExpanseGroup)) return false;
//
//        ExpanseGroup genre = (ExpanseGroup) o;
//
//        return getIconResId() == genre.getIconResId();
//
//    }
//
//    @Override
//    public int hashCode() {
//        return getIconResId();
//    }
}
