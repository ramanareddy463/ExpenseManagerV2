package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import expmanager.idea.spark.in.expensemanager.R;

/**
 * Created by Haresh.Veldurty on 2/21/2017.
 */

public class OrganizationFragment extends Fragment {
    ImageView setupexp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public OrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.organization_layout,
                container, false);
        setupexp = (ImageView) rootView.findViewById(R.id.setupexp);

        setupexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangibleExpenseFragment fragmenttangibleexp = new TangibleExpenseFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmenttangibleexp).commit();
            }
        });


        return rootView;
    }


}
