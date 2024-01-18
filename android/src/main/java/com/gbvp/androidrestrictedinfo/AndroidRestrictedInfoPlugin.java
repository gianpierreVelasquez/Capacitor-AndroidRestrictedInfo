package com.gbvp.androidrestrictedinfo;

import android.Manifest;
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

    static final String PHONE_STATE = "phone_state";
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
            getDeviceInfo(call);
        }
    }

    @Override
    @PluginMethod
    public void checkPermissions(PluginCall call) {
        if (implementation.isPhoneStateEnabled()) {
            super.checkPermissions(call);
        } else {
            call.reject("Phone state permission is not granted.");
        }
    }

    @Override
    @PluginMethod
    public void requestPermissions(PluginCall call) {
        if (implementation.isPhoneStateEnabled()) {
            super.requestPermissions(call);
        } else {
            call.reject("Phone state permission is not granted.");
        }
    }

    @PermissionCallback
    private void requestPermissionsCallback(PluginCall call) {
        if (getPermissionState(AndroidRestrictedInfoPlugin.PHONE_STATE) == PermissionState.GRANTED) {
            TelephonyManager tm = implementation.getDeviceInfo();
            call.resolve(getJSObjectForDeviceInfo(tm));
        } else {
            call.reject("Phone state permission was denied");
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getDeviceInfo(PluginCall call) {
        TelephonyManager tm = implementation.getDeviceInfo();
        call.resolve(getJSObjectForDeviceInfo(tm));
    }

    private JSObject getJSObjectForDeviceInfo(TelephonyManager tm) {
        JSObject ret = new JSObject();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ret.put("imei", tm.getImei());
            ret.put("serial", Build.getSerial());
        }
        return ret;
    }

    private String getAlias(PluginCall call) {
        String alias = AndroidRestrictedInfoPlugin.PHONE_STATE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alias = AndroidRestrictedInfoPlugin.PHONE_STATE;
        }
        return alias;
    }
}
