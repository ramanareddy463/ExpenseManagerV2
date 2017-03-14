package expmanager.idea.spark.in.expensemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.model.Sales;

/**
 * Created by Haresh.Veldurty on 3/14/2017.
 */


public class SalesListAdapter extends ArrayAdapter<Sales> {

    Context context;
    List<Sales> taskList = new ArrayList<Sales>();
    int layoutResourceId;

    public SalesListAdapter(Context context, int layoutResourceId,
                               List<Sales> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.taskList = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.sales_list_item, null);
        } else {
            itemView = convertView;
        }

        Sales current = taskList.get(position);
        TextView textView1 = (TextView) itemView.findViewById(R.id.saletype);
        TextView textView2 = (TextView) itemView.findViewById(R.id.saledate);
        TextView textView3 = (TextView) itemView.findViewById(R.id.saleampunt);


        textView1.setText(current.getSaletype());
        textView2.setText(current.getSaletype());
        textView3.setText(current.getDate());

        return itemView;
    }

}