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
import expmanager.idea.spark.in.expensemanager.utils.ExpanseChildViewHolder;
import expmanager.idea.spark.in.expensemanager.utils.ExpanseTitleViewHolder;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class ExpanseAdapter   extends ExpandableRecyclerViewAdapter<ExpanseTitleViewHolder, ExpanseChildViewHolder> {

    public ExpanseAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ExpanseTitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expanse_group, parent, false);
        return new ExpanseTitleViewHolder(view);
    }

    @Override
    public ExpanseChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expanse_child, parent, false);
        return new ExpanseChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ExpanseChildViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final ExpanseItem artist = ((ExpanseItem) group.getItems().get(childIndex));
        holder.setDetails(artist.getName(),artist.getQuantity(),artist.getCost());
    }

    @Override
    public void onBindGroupViewHolder(ExpanseTitleViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        ExpanseGroup expanseGroup = (ExpanseGroup) group;

        holder.setDetails(group.getTitle(),expanseGroup.getCount(),expanseGroup.getTotalCost());
    }
}
