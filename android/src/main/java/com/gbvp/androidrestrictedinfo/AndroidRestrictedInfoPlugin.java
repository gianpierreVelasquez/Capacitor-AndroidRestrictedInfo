package com.gbvp.androidrestrictedinfo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import org.json.JSONException;

import java.util.List;

@CapacitorPlugin(name = "AndroidRestrictedInfo", permissions = {@Permission(alias = AndroidRestrictedInfoPlugin.PHONE_STATE, strings = {Manifest.permission.READ_PHONE_STATE})})
public class AndroidRestrictedInfoPlugin extends Plugin {

    static final String PHONE_STATE = "phoneState";

    @PluginMethod
    public void getInfo(final PluginCall call) {
        String alias = getAlias(call);
        if (getPermissionState(alias) != PermissionState.GRANTED) {
            requestPermissionForAlias(alias, call, "requestPermissionsCallback");
        } else {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                getDeviceInfo(call);
            } else {
                call.unavailable("Not available on Android API 29 or earlier.");
            }
        }
    }

    @Override
    @PluginMethod
    public void checkPermissions(PluginCall call) {
        if (isPermissionDeclared(PHONE_STATE)) {
            super.checkPermissions(call);
        } else {
            call.reject("Phone state permission is not granted.");
        }
    }

    @Override
    @PluginMethod
    public void requestPermissions(PluginCall call) {
        if (isPermissionDeclared(PHONE_STATE)) {
            super.requestPermissions(call);
        } else {
            JSArray providedPerms = call.getArray("permissions");
            List<String> permsList = null;

            if (providedPerms != null) {
                try {
                    permsList = providedPerms.toList();
                } catch (JSONException e) {}
            }

            if (permsList != null && permsList.contains(PHONE_STATE)) {
                checkPermissions(call);
            } else {
                requestPermissionForAlias(PHONE_STATE, call, "checkPermissions");
            }
        }
    }

    @PermissionCallback
    private void requestPermissionsCallback(PluginCall call) {
        if (getPermissionState(AndroidRestrictedInfoPlugin.PHONE_STATE) == PermissionState.GRANTED) {
            getDeviceInfo(call);
        } else {
            requestPermissionForAlias(PHONE_STATE, call, "requestPermissions");
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getDeviceInfo(PluginCall call) {
        TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);

        if (tm != null) {
            call.resolve(getJSObjectForDeviceInfo(tm));
        } else {
            call.reject("Plugin not available");
        }
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
