package expmanager.idea.spark.in.expensemanager.utils;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import expmanager.idea.spark.in.expensemanager.R;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class ExpenseChildViewHolder extends ChildViewHolder {

    private CustomFonts childName;
    private TextView childQuantity;
    private TextView childCost;

    public ExpenseChildViewHolder(View itemView) {
        super(itemView);
        childName = (CustomFonts) itemView.findViewById(R.id.name);
        childQuantity = (TextView) itemView.findViewById(R.id.quantity);
        childCost = (TextView) itemView.findViewById(R.id.cost);
    }

    public void setDetails(String name,String quantity,String cost) {
        childName.setText(name);
        childQuantity.setText(quantity);
        childQuantity.setText(cost);
    }
}