package expmanager.idea.spark.in.expensemanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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


        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return 10;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            return new ExpenseHistoryViewPagerFragment();
        }
//        @Override
//        public int getItemPosition(Object object) {
//            return POSITION_NONE;
//        }
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((ViewPager)container).removeView((View)object);
//        }
    }


}
