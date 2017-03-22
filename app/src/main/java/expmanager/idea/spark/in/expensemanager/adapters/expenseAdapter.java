package expmanager.idea.spark.in.expensemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.model.Expense;

public class expenseAdapter extends BaseAdapter {

    ArrayList<Expense> listItem;

    Context mContext;

    //constructor
    public expenseAdapter(Context mContext, ArrayList<Expense> listItem) {
        this.mContext = mContext;
        this.listItem = listItem;
    }

    public int getCount() {
        return listItem.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View arg1, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.expense_list, viewGroup, false);

        TextView expTitle = (TextView) row.findViewById(R.id.lblexpitemname);
        TextView expAmt = (TextView) row.findViewById(R.id.lblexpitemprice);

        expTitle.setText(listItem.get(position).getExpDescription());
        expAmt.setText("$" + String.format("%.2f", listItem.get(position).getExpAmt()));


        return row;
    }
}