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
import expmanager.idea.spark.in.expensemanager.model.Staff;

/**
 * Created by Haresh.Veldurty on 2/24/2017.
 */

public class MyStaffDetailsAdapter extends ArrayAdapter<Staff> {

    List<Staff> qList = new ArrayList<>();

    public MyStaffDetailsAdapter(Context context, int textViewResourceId, List<Staff> objects) {
        super(context, textViewResourceId, objects);
        qList = objects;
    }



    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_staff_item, null);
        TextView textView1 = (TextView) v.findViewById(R.id.staffname);
       // TextView textView2 = (TextView) v.findViewById(R.id.when);
       // TextView textView3 = (TextView) v.findViewById(R.id.price);

        textView1.setText(qList.get(position).getStaff_name());
       // textView2.setText(qList.get(position).getWhen());
       // textView3.setText(qList.get(position).getPrice());

        return v;

    }
}