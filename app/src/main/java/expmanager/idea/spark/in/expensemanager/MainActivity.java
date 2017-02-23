package expmanager.idea.spark.in.expensemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import expmanager.idea.spark.in.expensemanager.fragments.OrganizationFragment;
import expmanager.idea.spark.in.expensemanager.fragments.StaffFragment;
import expmanager.idea.spark.in.expensemanager.fragments.TangibleExpenseFragment;

public class MainActivity extends AppCompatActivity {
ListView navigation_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        String[] values = new String[] { "ORGANIZATION",
                "TANGIBLE EXPENSES",
                "STAFF"

        };

        OrganizationFragment fragmentorg = new OrganizationFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentorg).commit();

        navigation_list = (ListView)findViewById(R.id.navigation_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.simple_list_item, R.id.listtxtitem, values);


        // Assign adapter to ListView
        navigation_list.setAdapter(adapter);

        // ListView Item Click Listener
        navigation_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    case 0:
                        OrganizationFragment fragmentorg = new OrganizationFragment();
                        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentorg).commit();
                        break;
                    case 1:
                        TangibleExpenseFragment fragmenttangibleexp = new TangibleExpenseFragment();
                        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmenttangibleexp).commit();

                        break;
                    case 2:
                        StaffFragment fragmentstaff = new StaffFragment();
                        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentstaff).commit();
                        break;

                }
            }
        });
    }
}
