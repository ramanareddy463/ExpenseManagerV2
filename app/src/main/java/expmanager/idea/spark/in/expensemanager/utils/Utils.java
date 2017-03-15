package expmanager.idea.spark.in.expensemanager.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Haresh.Veldurty on 2/21/2017.
 */

public class Utils {

    public static DisplayMetrics getDeviceMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }

    public static String getDeviceId(Context context){

        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static int[] getWeeksOfMonth(int month, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        int ndays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks[] = new int[ndays];
        for (int i = 0; i < ndays; i++)
        {
            weeks[i] = cal.get(Calendar.WEEK_OF_YEAR);
            cal.add(Calendar.DATE, 1);
        }
        return weeks;
    }
}
