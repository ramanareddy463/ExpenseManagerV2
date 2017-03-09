package expmanager.idea.spark.in.expensemanager.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import expmanager.idea.spark.in.expensemanager.R;

/**
 * Created by Haresh.Veldurty on 2/22/2017.
 */

public class AdminProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public AdminProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.adminprofile_layout,
                container, false);



        return rootView;
    }


}
