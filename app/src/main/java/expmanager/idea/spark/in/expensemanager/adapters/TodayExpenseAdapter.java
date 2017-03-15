package expmanager.idea.spark.in.expensemanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.model.ExpanseGroup;
import expmanager.idea.spark.in.expensemanager.model.ExpanseItem;
import expmanager.idea.spark.in.expensemanager.utils.ExpenseChildViewHolder;
import expmanager.idea.spark.in.expensemanager.utils.ExpenseTitleViewHolder;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class TodayExpenseAdapter extends ExpandableRecyclerViewAdapter<ExpenseTitleViewHolder, ExpenseChildViewHolder> {

    public TodayExpenseAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ExpenseTitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_group, parent, false);
        return new ExpenseTitleViewHolder(view);
    }

    @Override
    public ExpenseChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_child, parent, false);
        return new ExpenseChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ExpenseChildViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final ExpanseItem artist = ((ExpanseItem) group.getItems().get(childIndex));
        holder.setDetails(artist.getName(),artist.getQuantity(),artist.getCost());
    }

    @Override
    public void onBindGroupViewHolder(ExpenseTitleViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        ExpanseGroup expanseGroup = (ExpanseGroup) group;

        holder.setDetails(group.getTitle(),expanseGroup.getCount(),expanseGroup.getTotalCost());
    }
}
