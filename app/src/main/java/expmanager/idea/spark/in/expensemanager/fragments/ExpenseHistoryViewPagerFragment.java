package expmanager.idea.spark.in.expensemanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.TodayExpenseAdapter;
import expmanager.idea.spark.in.expensemanager.model.ExpanseGroup;
import expmanager.idea.spark.in.expensemanager.model.ExpanseItem;

/**
 * Created by Ramana.Reddy on 3/9/2017.
 */

public class ExpenseHistoryViewPagerFragment extends Fragment {

    public TodayExpenseAdapter adapter;
    public TodayExpenseAdapter weekAdapter;
    private ImageView imgAddExpense;
    private TextView txtTitleWeek;

    public ExpenseHistoryViewPagerFragment(){


    }

    public static ExpenseHistoryViewPagerFragment newInstance(String startDate,String endDate) {
        ExpenseHistoryViewPagerFragment f = new ExpenseHistoryViewPagerFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("startDate", startDate);
        args.putString("endDate", endDate);
        f.setArguments(args);

        return f;
    }

    public String getStartDate() {
        return getArguments().getString("startDate", "");
    }

    public String getEndDate() {
        return getArguments().getString("endDate", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.expense_history_viewpager,
                container, false);

        imgAddExpense = (ImageView) rootView.findViewById(R.id.img_add_expense);
        txtTitleWeek = (TextView) rootView.findViewById(R.id.title_week);


        txtTitleWeek.setText(getStartDate()+" "+getEndDate());

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView recyclerViewWeek = (RecyclerView)rootView.findViewById(R.id.recycler_view_week);
        LinearLayoutManager layoutManagerWeek = new LinearLayoutManager(getActivity());

        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        adapter = new TodayExpenseAdapter(makeExpansesList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        weekAdapter = new TodayExpenseAdapter(makeExpansesList());
        recyclerViewWeek.setLayoutManager(layoutManagerWeek);
        recyclerViewWeek.setAdapter(weekAdapter);


        return rootView;
    }

    public static List<ExpanseItem> makeExpanseItems() {
        ExpanseItem item1 = new ExpanseItem("Olive oil", "2 lt","$10.00");
        ExpanseItem item2 = new ExpanseItem("Sugar", "5 kg","$20.00");
        ExpanseItem item3 = new ExpanseItem("Carrots", "5 kg","$10.00");


        return Arrays.asList(item1, item2, item3);
    }

    public static List<ExpanseGroup> makeExpansesList() {
        return Arrays.asList(makeExpanseGroup(),
                makeExpanseGroup());
    }
    public static ExpanseGroup makeExpanseGroup() {
        return new ExpanseGroup("Grocery Today",makeExpanseItems(),"3 items","$40.00");
    }
}
