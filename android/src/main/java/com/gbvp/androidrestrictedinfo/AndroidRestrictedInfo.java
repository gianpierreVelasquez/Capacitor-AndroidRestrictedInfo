package com.gbvp.androidrestrictedinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

public class AndroidRestrictedInfo {
    private Context context;

    public AndroidRestrictedInfo(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public Boolean isPhoneStateEnabled() {
        Boolean phoneStateEnabled = false;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            phoneStateEnabled = tm.isDataEnabled();
        }

        return phoneStateEnabled;
    }

    @SuppressWarnings("MissingPermission")
    public TelephonyManager getDeviceInfo() {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm;
    }
}
