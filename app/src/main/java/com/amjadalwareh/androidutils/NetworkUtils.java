package com.amjadalwareh.androidutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

public final class NetworkUtils {

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    private static NetworkInfo getNetworkState(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getService(context, Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return null;

        return connectivityManager.getActiveNetworkInfo();
    }

    /***
     * A method to check if device connected to network or not
     * @param context The app context
     * @return true if connected, otherwise false
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNetworkConnected(@NonNull Context context) {
        NetworkInfo info = getNetworkState(context);
        return info != null && info.isConnected();
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected(@NonNull Context context) {
        NetworkInfo info = getNetworkState(context);

        if (info == null) return false;

        return info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMobileDataConnected(@NonNull Context context) {
        NetworkInfo info = getNetworkState(context);

        if (info == null) return false;

        return info.getType() == ConnectivityManager.TYPE_MOBILE;
    }

}