package expmanager.idea.spark.in.expensemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;

import expmanager.idea.spark.in.expensemanager.fragments.ExpenseFragment;
import expmanager.idea.spark.in.expensemanager.fragments.StaffProfileFragment;


/**
 * Created by kveldurty on 2/21/17.
 */


public class StaffActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton staffexpense,stafftanexpense,staffhistory,staffprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_layout);
        initializeControls();


        ExpenseFragment fragmentorg = new ExpenseFragment();
        getFragmentManager().beginTransaction().replace(R.id.staff_content_frame, fragmentorg).commit();


    }

    private void initializeControls() {
        staffexpense = (ImageButton) findViewById(R.id.staffexpense);
        staffexpense.setOnClickListener(this);

        stafftanexpense = (ImageButton) findViewById(R.id.stafftanexpense);
        stafftanexpense.setOnClickListener(this);

        staffhistory = (ImageButton) findViewById(R.id.staffhistory);
        staffhistory.setOnClickListener(this);

        staffprofile = (ImageButton) findViewById(R.id.staffprofile);
        staffprofile.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.staffprofile:
                StaffProfileFragment fragprofile = new StaffProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.staff_content_frame, fragprofile).commit();

                break;

        }

    }
}
