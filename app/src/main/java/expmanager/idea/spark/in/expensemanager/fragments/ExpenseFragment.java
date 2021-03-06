package expmanager.idea.spark.in.expensemanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.TodayExpenseAdapter;
import expmanager.idea.spark.in.expensemanager.model.ExpanseGroup;
import expmanager.idea.spark.in.expensemanager.model.ExpanseItem;

/**
 * Created by Ramana.Reddy on 2/24/2017.
 */

public class ExpenseFragment extends Fragment implements View.OnClickListener {

    public TodayExpenseAdapter adapter;
    public TodayExpenseAdapter weekAdapter;
    private Button imgAddocrExpense, addexpbtn;
    private ImageView imgArrow;
    RelativeLayout main_layout;

    int flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.expense,
                container, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();

        int width = display.getWidth();

        final int convertWidth = (int) (width*0.65);

        imgAddocrExpense = (Button) rootView.findViewById(R.id.img_ocr_expense);
        imgAddocrExpense.setOnClickListener(this);

        //drawableInitialasation(rootView);

        addexpbtn = (Button) rootView.findViewById(R.id.img_add_expense);
        addexpbtn.setOnClickListener(this);

        imgArrow = (ImageView) rootView.findViewById(R.id.img_arrow);

        main_layout = (RelativeLayout) rootView.findViewById(R.id.parent_main_layout);

        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == 0) {
                    main_layout.getLayoutParams().width = convertWidth;
                    main_layout.requestLayout();
                    flag = 1;
                } else {
                    main_layout.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                    main_layout.requestLayout();
                    flag = 0;
                }

            }
        });

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView recyclerViewWeek = (RecyclerView) rootView.findViewById(R.id.recycler_view_week);
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

//    private void drawableInitialasation(View rootView) {
//
//
//        TextView textView = (TextView) rootView.findViewById(R.id.doller_img);
//
//       textView.setTypeface(FontManager.getTypeface(getActivity(),FontManager.FONTAWESOME));
//    }

    public static List<ExpanseItem> makeExpanseItems() {
        ExpanseItem item1 = new ExpanseItem("Olive oil", "2 lt", "$10.00");
        ExpanseItem item2 = new ExpanseItem("Sugar", "5 kg", "$20.00");
        ExpanseItem item3 = new ExpanseItem("Carrots", "5 kg", "$10.00");

        return Arrays.asList(item1, item2, item3);
    }

    public static List<ExpanseGroup> makeExpansesList() {
        return Arrays.asList(makeExpanseGroup(),
                makeExpanseGroup());
    }

    public static ExpanseGroup makeExpanseGroup() {
        return new ExpanseGroup("Grocery Today", makeExpanseItems(), "3 items", "$40.00");
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.img_ocr_expense) {

            AddExpenseFragment fragmentorg = new AddExpenseFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragmentorg).commit();

        } else if(view.getId() == R.id.img_add_expense){

           // AddExpenseManualFragment fragmentorg = new AddExpenseManualFragment();
            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragmentorg).commit();
            InvoiceEntryFragment fragmentorg = new InvoiceEntryFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragmentorg).commit();
        }
    }

}
