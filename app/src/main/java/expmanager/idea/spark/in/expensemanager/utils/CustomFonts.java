package expmanager.idea.spark.in.expensemanager.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by RamanaRedddy on 3/18/17.
 */


    @SuppressLint("AppCompatCustomView")
    public class CustomFonts extends TextView {

    public CustomFonts(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFonts(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFonts(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        setTypeface(font,1);

    }

    }

