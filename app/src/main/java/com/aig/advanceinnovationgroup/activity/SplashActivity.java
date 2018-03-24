package com.aig.advanceinnovationgroup.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();
        //setname();
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{Manifest.permission
                            .READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    10);

            return;
        }
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{Manifest.permission
                            .WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    10);

            return;
        }
    }
}
