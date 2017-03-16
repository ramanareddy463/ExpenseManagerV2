package expmanager.idea.spark.in.expensemanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.utils.PagerContainer;

/**
 * Created by Ramana.Reddy on 3/9/2017.
 */

public class ExpenseHistoryFragment extends Fragment {

    PagerContainer mContainer;
    private Spinner monthExpenseHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.expense_history,
                container, false);


        mContainer = (PagerContainer) rootView.findViewById(R.id.pager_container);
        monthExpenseHistory = (Spinner) rootView.findViewById(R.id.month_expense_history);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.months, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        monthExpenseHistory.setAdapter(adapter);

        ViewPager pager = (ViewPager) rootView.findViewById(R.id.view_pager);//mContainer.getViewPager();
        MyPagerAdapter monthAdapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(monthAdapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
        pager.setPageMargin(15);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);




        return rootView;
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        int maxWeeknumber = 6,currentWeekNo,count = 0;

        String input = "20170316";
        String format = "yyyyMMdd";

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);


        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return maxWeeknumber;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            SimpleDateFormat df = new SimpleDateFormat(format);
            Calendar cal = Calendar.getInstance();

            try {
                Date date = df.parse(input);
                cal.setTime(date);
                // maxWeeknumber = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
                currentWeekNo = cal.get(Calendar.WEEK_OF_YEAR);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("LOG","For Month :: "+ currentWeekNo);

           int weekNo = currentWeekNo-5+position;
            Calendar cal1 = Calendar.getInstance();
            cal1.clear();
            cal1.set(Calendar.WEEK_OF_YEAR, weekNo);
            cal1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            cal1.set(Calendar.YEAR, 2017);
            Date d = cal1.getTime();
            cal1.clear();
            cal1.set(Calendar.WEEK_OF_YEAR, weekNo);
            cal1.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            cal1.set(Calendar.YEAR, 2017);
            Date d1 = cal1.getTime();

            Toast.makeText(getActivity(),"For Month ::"+position+" "+d+" "+d1.getDate(),Toast.LENGTH_SHORT).show();

            Log.d("LOG","For Month :: "+ weekNo + " Num Week :: " + d.getDate());

            int startMonth = d.getMonth()+1;
            int endMonth = d1.getMonth()+1;

            return ExpenseHistoryViewPagerFragment.newInstance(d.getDate()+"   "+startMonth,d1.getDate()+"  "+endMonth);
        }

    }


}
