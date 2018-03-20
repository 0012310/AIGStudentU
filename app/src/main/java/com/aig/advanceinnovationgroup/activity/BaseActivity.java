package com.aig.advanceinnovationgroup.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by JeannyPrAndroid on 24-Aug-17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected View mParent;
    private boolean mIsActivityVisible;

    public boolean isActivityVisible() {
        return mIsActivityVisible;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityVisible = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutById());


        initUi();
    }

    protected void onMenuClicked() {

    }

    protected abstract void initUi();

    protected abstract int getLayoutById();
}
