package com.amjadalwareh.androidutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

public final class NetworkUtils {

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) Utils.getService(context, Context.CONNECTIVITY_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    private static NetworkCapabilities getNetworkCapabilities(@NonNull Context context) {
        ConnectivityManager manager = getConnectivityManager(context);

        return manager.getNetworkCapabilities(manager.getActiveNetwork());
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    private static NetworkInfo getActiveNetwork(@NonNull Context context) {
        ConnectivityManager manager = getConnectivityManager(context);

        if (manager == null) return null;

        return manager.getActiveNetworkInfo();
    }


    /***
     * A method to check if device connected to network or not
     * @param context The app context
     * @return true if connected, otherwise false
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNetworkConnected(@NonNull Context context) {
        NetworkInfo info = getActiveNetwork(context);
        return info != null && info.isConnected();
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected(@NonNull Context context) {
        if (PhoneUtils.isMarshmallow()) {
            return getNetworkCapabilities(context).hasTransport(NetworkCapabilities.TRANSPORT_WIFI);

        } else {
            NetworkInfo info = getActiveNetwork(context);

            if (info == null) return false;

            return info.getType() == ConnectivityManager.TYPE_WIFI;
        }
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMobileDataConnected(@NonNull Context context) {
        if (PhoneUtils.isMarshmallow()) {
            return getNetworkCapabilities(context).hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);

        } else {
            NetworkInfo info = getActiveNetwork(context);

            if (info == null) return false;

            return info.getType() == ConnectivityManager.TYPE_MOBILE;
        }
    }
}