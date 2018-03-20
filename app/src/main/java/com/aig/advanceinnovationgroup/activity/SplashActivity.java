package com.aig.advanceinnovationgroup.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.ProgressBar;
import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.util.AppPreferences;


/**
 * Created by admin on 3/12/2018.
 */

public class SplashActivity extends BaseActivity {
    private ProgressBar mProgress;
    private int SPLASH_TIME_OUT = 2500;
    private Handler mHandler;

    @Override
    protected void initUi() {
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        // Start lengthy operation in a background thread

                doWork();



    }

    @Override
    protected int getLayoutById() {
        return R.layout.activity_splash;
    }

    private void doWork() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(AppPreferences.getBoolean(SplashActivity.this, AppPreferences.PREF_KEY.IS_LOGGED_IN)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();

            }
        }, SPLASH_TIME_OUT);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  mHandler.removeCallbacksAndMessages(null);
    }
}
