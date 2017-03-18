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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
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
    private String input;
    MyPagerAdapter monthAdapter;
    ViewPager pager;

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
        //String[] array = getResources().getStringArray(R.array.months);
        monthExpenseHistory.setSelection(2);

         pager = (ViewPager) rootView.findViewById(R.id.view_pager);//mContainer.getViewPager();
         monthAdapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(monthAdapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(6);
        //A little space between pages
        pager.setPageMargin(15);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);

        monthExpenseHistory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                DateFormat dateFormat = new SimpleDateFormat("dd");
                Date date = new Date();
                System.out.println(); //2016/11/16 12:08:43

                switch (position){

                    case 0:
                        input = "201701"+dateFormat.format(date);
                        //monthAdapter.notifyDataSetChanged();
                        break;
                    case 1:  input = "201702"+dateFormat.format(date);
                        break;
                    case 2:  input = "201703"+dateFormat.format(date);
                            // monthAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                             input = "201704"+dateFormat.format(date);
                             //monthAdapter.notifyDataSetChanged();
                        break;
                    case 4:  input = "201705"+dateFormat.format(date);
                             //monthAdapter.notifyDataSetChanged();
                        break;
                    case 5:  input = "201706"+dateFormat.format(date);
                            //monthAdapter.notifyDataSetChanged();
                        break;
                    case 6:  input = "201707"+dateFormat.format(date);
                            // monthAdapter.notifyDataSetChanged();
                        break;
                    case 7:  input = "201708"+dateFormat.format(date);
                             //monthAdapter.notifyDataSetChanged();
                        break;
                    case 8:  input = "201709"+dateFormat.format(date);
                            // monthAdapter.notifyDataSetChanged();
                        break;
                    case 9:  input = "201710"+dateFormat.format(date);
                            // monthAdapter.notifyDataSetChanged();
                        break;
                    case 10: input = "201711"+dateFormat.format(date);
                            // monthAdapter.notifyDataSetChanged();
                        break;
                    case 11: input = "201712"+dateFormat.format(date);
                            // monthAdapter.notifyDataSetChanged();
                        break;

                }

                monthAdapter = new MyPagerAdapter(getFragmentManager());
                pager.setAdapter(monthAdapter);
                //Necessary or the pager will only have one extra page to show
                // make this at least however many pages you can see
                pager.setOffscreenPageLimit(6);
                //A little space between pages
                pager.setPageMargin(15);

                //If hardware acceleration is enabled, you should also remove
                // clipping on the pager for its children.
                pager.setClipChildren(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return rootView;
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        int maxWeeknumber = 6,currentWeekNo,count = 0;

        String format = "yyyyMMdd";

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
            input =  mdformat.format(calendar.getTime());


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

//            Toast.makeText(getActivity(),"For Month ::"+position+" "+d+" "+d1.getDate(),Toast.LENGTH_SHORT).show();

            Log.d("LOG","For Month :: "+ weekNo + " Num Week :: " + d.getDate());

            int startMonth = d.getMonth()+1;
            int endMonth = d1.getMonth()+1;

            //return ExpenseHistoryViewPagerFragment.newInstance(d.getDate()+"   "+startMonth,d1.getDate()+"  "+endMonth);
            return ExpenseHistoryViewPagerFragment.newInstance(d.getDate()+" ",d1.getDate()+" ");
        }

    }


}
