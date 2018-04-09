package com.aig.advanceinnovationgroup.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by admin on 4/9/2018.
 */

public class RobotoRegularTextView extends AppCompatTextView {
    public RobotoRegularTextView(Context context) {
            this(context, null);
        }

    public RobotoRegularTextView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

    public RobotoRegularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

    private void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf"));
    }

}
