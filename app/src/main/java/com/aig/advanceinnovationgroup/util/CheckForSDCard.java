package com.aig.advanceinnovationgroup.util;

import android.os.Environment;

/**
 * Created by admin on 3/19/2018.
 */

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
