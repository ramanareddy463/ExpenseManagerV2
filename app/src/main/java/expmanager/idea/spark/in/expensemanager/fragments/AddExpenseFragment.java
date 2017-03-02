package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import expmanager.idea.spark.in.expensemanager.R;

/**
 * Created by Ramana.Reddy on 2/28/2017.
 */

public class AddExpenseFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_expense,
                container, false);




        return rootView;
    }

}
