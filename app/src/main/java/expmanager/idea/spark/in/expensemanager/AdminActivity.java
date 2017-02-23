package expmanager.idea.spark.in.expensemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;

import expmanager.idea.spark.in.expensemanager.fragments.AdminProfileFragment;


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

            case R.id.btnprofile:
                AdminProfileFragment fragprofile = new AdminProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.admin_content_frame, fragprofile).commit();

                break;

        }

    }
}
