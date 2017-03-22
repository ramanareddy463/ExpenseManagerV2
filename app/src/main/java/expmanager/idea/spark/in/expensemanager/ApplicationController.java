package expmanager.idea.spark.in.expensemanager;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

/**
 * Created by RamanaRedddy on 3/19/17.
 */

public class ApplicationController extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        new Instabug.Builder(this, "92ede9aee8185d312ffa7162e75fdeaf")
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .build();
    }
}
