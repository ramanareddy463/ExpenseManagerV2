package expmanager.idea.spark.in.expensemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import expmanager.idea.spark.in.expensemanager.fragments.AdminAddStaffFragment;
import expmanager.idea.spark.in.expensemanager.fragments.AdminProfileFragment;
import expmanager.idea.spark.in.expensemanager.fragments.AdminTangibleExpenses;
import expmanager.idea.spark.in.expensemanager.fragments.DashBoardFragment;
import expmanager.idea.spark.in.expensemanager.fragments.ExpenseFragment;
import expmanager.idea.spark.in.expensemanager.fragments.ExpenseHistoryFragment;


/**
 * Created by kveldurty on 2/21/17.
 */





public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnexpense,btntanexpense,btnhistory,btndashboard,btnstaff,btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        initializeControls();
        ExpenseFragment fragmentorg = new ExpenseFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragmentorg).commit();

    }

    private void initializeControls() {
        btnexpense = (ImageButton) findViewById(R.id.btnexpense);
        btnexpense.setOnClickListener(this);

        btntanexpense = (ImageButton) findViewById(R.id.btntanexpense);
        btntanexpense.setOnClickListener(this);

        btnhistory = (ImageButton) findViewById(R.id.btnhistory);
        btnhistory.setOnClickListener(this);

        btndashboard = (ImageButton) findViewById(R.id.btndashboard);
        btndashboard.setOnClickListener(this);

        btnstaff = (ImageButton) findViewById(R.id.btnstaff);
        btnstaff.setOnClickListener(this);

        btnprofile = (ImageButton) findViewById(R.id.btnprofile);
        btnprofile.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnexpense:
                ExpenseFragment fragmentorg = new ExpenseFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragmentorg).commit();

                break;
            case R.id.btnprofile:
                AdminProfileFragment fragprofile = new AdminProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragprofile).commit();
                break;

            case R.id.btndashboard:
                DashBoardFragment fragdashboard = new DashBoardFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragdashboard).commit();
                break;
            case R.id.btntanexpense:
                AdminTangibleExpenses fragtanexp = new AdminTangibleExpenses();
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragtanexp).commit();
                break;
            case R.id.btnstaff:
                AdminAddStaffFragment fragstaff= new AdminAddStaffFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragstaff).commit();
                break;

            case R.id.btnhistory:
                ExpenseHistoryFragment fragExpenseHistory = new ExpenseHistoryFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragExpenseHistory).commit();

                break;

        }

    }
}
