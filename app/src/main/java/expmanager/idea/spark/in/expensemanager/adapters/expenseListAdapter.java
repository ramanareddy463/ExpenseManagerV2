package expmanager.idea.spark.in.expensemanager.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.model.Expense;


public class expenseListAdapter extends BaseAdapter {

    ArrayList<Expense> listItem;
    DatabaseHandler mDbhelper;
    Context mContext;
    ListView parent;

    //constructor
    public expenseListAdapter(Context mContext, ArrayList<Expense> listItem,ListView lv) {
        this.mContext = mContext;
        this.listItem = listItem;
        mDbhelper = new DatabaseHandler(mContext);
        this.parent = lv;
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

    public View getView(final int position, View arg1, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.lstexpitems, viewGroup, false);

        TextView lblcat = (TextView) row.findViewById(R.id.lblcat);
        TextView lbldesc = (TextView) row.findViewById(R.id.lbldesc);
        TextView lblunit = (TextView) row.findViewById(R.id.lblunit);
        TextView lblamt = (TextView) row.findViewById(R.id.lblamt);
        TextView btnexpedit = (TextView)row.findViewById(R.id.btnexpedit);
        btnexpedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, listItem.get(position).getExpid());
            }
        });
        TextView btnexpdel = (TextView) row.findViewById(R.id.btnexpdelete);
        btnexpdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, listItem.get(position).getExpid());
            }
        });

        mDbhelper.openConnection();
        lblcat.setText(mDbhelper.getCatName(listItem.get(position).getExpCatId()));
        mDbhelper.closeConnection();
        lbldesc.setText(listItem.get(position).getExpDescription());
        lblunit.setText("" + listItem.get(position).getExpUnit());
        lblamt.setText("$" + String.format("%.2f", listItem.get(position).getExpAmt()));

        row.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.parseColor("#b2beb5"));

        return row;
    }
}

