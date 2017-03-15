package expmanager.idea.spark.in.expensemanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import expmanager.idea.spark.in.expensemanager.R;

/**
 * Created by Ramana.Reddy on 2/28/2017.
 */

public class AddExpenseFragment extends Fragment {

    private ImageView imageRescan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_expense,
                container, false);

        imageRescan = (ImageView) rootView.findViewById(R.id.img_rescan);

        imageRescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }

}
