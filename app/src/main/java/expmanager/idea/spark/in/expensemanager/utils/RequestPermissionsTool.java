package expmanager.idea.spark.in.expensemanager.utils;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Ramana.Reddy on 3/16/2017.
 */

public interface RequestPermissionsTool {

    void requestPermissions(Activity context, String[] permissions);

    boolean isPermissionsGranted(Context context, String[] permissions);

    void onPermissionDenied();
}
