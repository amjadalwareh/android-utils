package com.amjadalwareh.androidutils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

public final class ConnectivityUtils {

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

    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    public static boolean turnOnWifi(@NonNull Context context) {
        return changeWiFiState(context, true);
    }

    @RequiresPermission(android.Manifest.permission.CHANGE_WIFI_STATE)
    public static boolean turnOffWifi(@NonNull Context context) {
        return changeWiFiState(context, false);
    }

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

    @RequiresPermission(android.Manifest.permission.CHANGE_WIFI_STATE)
    private static boolean changeWiFiState(@NonNull Context context, boolean turnOn) {
        try {
            if (PhoneUtils.isAndroidQ()) return false;

            WifiManager manager = (WifiManager) Utils.getService(context, Context.WIFI_SERVICE);

            return manager.setWifiEnabled(turnOn);
        } catch (Exception e) {
            return false;
        }
    }

    public static void openWiFiSettings(@NonNull Context context) {
        if (PhoneUtils.isAndroidQ())
            Utils.startIntent(context, Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
        else
            Utils.startIntent(context, Settings.ACTION_WIRELESS_SETTINGS);
    }

    public static void openDataSettings(@NonNull Context context) {
        Utils.startIntent(context, Settings.ACTION_DATA_ROAMING_SETTINGS);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static void openInternetPanel(@NonNull Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);

        if (requestCode == -1) activity.startActivity(intent);
        else activity.startActivityForResult(intent, requestCode);
    }
}