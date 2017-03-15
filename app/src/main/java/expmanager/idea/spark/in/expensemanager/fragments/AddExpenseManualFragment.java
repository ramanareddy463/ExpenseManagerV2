package expmanager.idea.spark.in.expensemanager.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.utils.Utils;

/**
 * Created by Haresh.Veldurty on 3/14/2017.
 */

public class AddExpenseManualFragment extends Fragment {
 EditText exp_date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public AddExpenseManualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.addmanulaexpense_layout,
                container, false);

        exp_date = (EditText) rootView.findViewById(R.id.exp_date);
        exp_date.setText(Utils.getDateTime());

        return rootView;
    }


}
