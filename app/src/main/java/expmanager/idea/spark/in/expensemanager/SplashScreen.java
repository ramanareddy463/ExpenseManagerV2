package expmanager.idea.spark.in.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;

/**
 * Created by kveldurty on 2/20/17.
 */

public class SplashScreen extends AppCompatActivity {
DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spash_activity);
      db = new DatabaseHandler(this);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SessionManager sessionManager = new SessionManager(SplashScreen.this);

                if(sessionManager.getAuthToken().isEmpty()) {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }else {

                    if(sessionManager.getCompanyId().isEmpty()||sessionManager.getCompanyId()==null) {

                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);

                    }else {

                        Intent intent = new Intent(SplashScreen.this, AdminActivity.class);
                        startActivity(intent);
                    }

                    // close this activity
                    finish();
                }
            }
        }, 3000);
    }



}
