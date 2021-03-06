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
import expmanager.idea.spark.in.expensemanager.utils.CustomFonts;

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
        TextView textView1 = (CustomFonts) v.findViewById(R.id.staffname);
        TextView textView2 = (CustomFonts) v.findViewById(R.id.shiftdays);
        TextView textView3 = (CustomFonts) v.findViewById(R.id.shifttime);
        TextView textView4 = (CustomFonts) v.findViewById(R.id.perhourdetails);
        TextView textView5 = (CustomFonts) v.findViewById(R.id.designation);
        TextView textView6 = (CustomFonts) v.findViewById(R.id.phonenumber);
        TextView textView7 = (CustomFonts) v.findViewById(R.id.staffemail);
        TextView textView8 = (CustomFonts) v.findViewById(R.id.staffadress);
        TextView textView9 = (CustomFonts) v.findViewById(R.id.startdate);

        String s1 = qList.get(position).getShift_days1();
        String s2 = qList.get(position).getShift_days2();
        String s3 = " to ";
        String s = s1+s3+s2;

        String s5 = qList.get(position).getShift_time1();
        String s6 = qList.get(position).getShift_time2();
        String s7 = " to ";
        String s8 = s5+s7+s6;

        String s9 = qList.get(position).getPrice_perhr();
        String s10 = qList.get(position).getPriceType();
        String s12 = " $ ";
        String s11 = s12 +s9+ s10;


        textView1.setText(qList.get(position).getStaff_name());
        textView2.setText(s);
        textView3.setText(s8);
        textView4.setText(s11);
        textView5.setText(qList.get(position).getStaff_designation());
        textView6.setText(qList.get(position).getStaff_phonenumber());
        textView7.setText(qList.get(position).getStaff_email());
        textView8.setText(qList.get(position).getStaff_address());
        textView9.setText(qList.get(position).getStaff_startdate());


        return v;

    }
}