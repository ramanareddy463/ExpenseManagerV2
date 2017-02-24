package expmanager.idea.spark.in.expensemanager.utils;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import expmanager.idea.spark.in.expensemanager.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class ExpanseTitleViewHolder extends GroupViewHolder {

    private TextView childTitle;
    private TextView childItems;
    private TextView childCost;
    private ImageView arrow;

    public ExpanseTitleViewHolder(View itemView) {
        super(itemView);
        childTitle = (TextView) itemView.findViewById(R.id.title);
        childItems = (TextView) itemView.findViewById(R.id.items);
        childCost = (TextView) itemView.findViewById(R.id.cost);
        arrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
    }

    public void setDetails(String name,String quantity,String cost) {
        childTitle.setText(name);
        childItems.setText(quantity);
        childCost.setText(cost);
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}

