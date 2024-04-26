package com.gbvp.androidrestrictedinfo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "AndroidRestrictedInfo", permissions = {@Permission(alias = AndroidRestrictedInfoPlugin.PHONE_STATE, strings = {Manifest.permission.READ_PHONE_STATE})})
public class AndroidRestrictedInfoPlugin extends Plugin {

    static final String PHONE_STATE = "phoneState";
    private AndroidRestrictedInfo implementation;

    @Override
    public void load() {
        implementation = new AndroidRestrictedInfo(getContext());
    }

    @PluginMethod
    public void getInfo(final PluginCall call) {
        String alias = getAlias(call);
        if (getPermissionState(alias) != PermissionState.GRANTED) {
            requestPermissionForAlias(alias, call, "requestPermissionsCallback");
        } else {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
                getDeviceInfo(call);
            } else {
                call.unavailable("Not available on Android API 26 or earlier.");
            }
        }
    }

    @PluginMethod
    public void checkPermissions(PluginCall call) {
        if (implementation.isPhoneStateEnabled() != PackageManager.PERMISSION_GRANTED) {
            super.checkPermissions(call);
        } else {
            call.reject("Phone state permission is not granted.");
        }
    }

    @PluginMethod
    public void requestPermissions(PluginCall call) {
        if (implementation.isPhoneStateEnabled() == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
                getDeviceInfo(call);
            } else {
                call.unavailable("Not available on Android API 26 or earlier.");
            }
        } else {
            super.requestPermissions(call);
        }
    }

    @PermissionCallback
    private void requestPermissionsCallback(PluginCall call) {
        if (getPermissionState(AndroidRestrictedInfoPlugin.PHONE_STATE) == PermissionState.GRANTED) {
            getDeviceInfo(call);
        } else {
            call.reject("Phone state permission was denied");
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getDeviceInfo(PluginCall call) {
        TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        call.resolve(getJSObjectForDeviceInfo(tm));
    }

    @SuppressLint({"MissingPermission"})
    private JSObject getJSObjectForDeviceInfo(TelephonyManager tm) {
        JSObject ret = new JSObject();

        ret.put("imei", tm.getDeviceId());
         ret.put("serial", Build.SERIAL);

        return ret;
    }

    private String getAlias(PluginCall call) {
        return AndroidRestrictedInfoPlugin.PHONE_STATE;
    }
}
