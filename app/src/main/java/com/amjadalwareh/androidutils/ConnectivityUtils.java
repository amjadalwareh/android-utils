package com.amjadalwareh.androidutils;

import android.Manifest;
import android.content.Context;
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

    /**
     * Check if the device is connected to network or not
     *
     * @param context The app context
     * @return {@code true} if connected, otherwise {@code false}
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNetworkConnected(@NonNull Context context) {
        NetworkInfo info = getActiveNetwork(context);
        return info != null && info.isConnected();
    }

    /**
     * Check if device WiFi is connected or not.
     *
     * @param context The app context.
     * @return {@code true} if connected, otherwise {@code false}.
     */
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

    /**
     * Check if device mobile data is on or not.
     *
     * @param context The app context.
     * @return {@code true} if on, otherwise {@code false}.
     */
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

    /**
     * Turn WiFi on
     * Please note that starting from {@link Build.VERSION_CODES#Q},applications are not allowed to
     * enable Wi-Fi.
     *
     * @param context The app context
     * @return {@code true} if WiFi state has changed, but if the app is targeting {@link Build.VERSION_CODES#Q} then will return {@code false} or if the state has been not changed.
     */
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    public static boolean turnOnWifi(@NonNull Context context) {
        return changeWiFiState(context, true);
    }

    /**
     * Turn WiFi off
     * Please note that starting from {@link Build.VERSION_CODES#Q},applications are not allowed to
     * disable Wi-Fi.
     *
     * @param context The app context
     * @return {@code true} if WiFi state has changed, but if the app is targeting {@link Build.VERSION_CODES#Q} then will return {@code false} or if the state has been not changed.
     */
    @RequiresPermission(android.Manifest.permission.CHANGE_WIFI_STATE)
    public static boolean turnOffWifi(@NonNull Context context) {
        return changeWiFiState(context, false);
    }

    /**
     * Open WiFi Settings, if the app run on device that has {@link Build.VERSION_CODES#Q} then it will open the new Settings panel.
     * you can read more about it <a href="https://developer.android.com/reference/kotlin/android/provider/Settings.Panel">here</a>
     *
     * @param context the application context
     */
    public static void openWiFiSettings(@NonNull Context context) {
        if (PhoneUtils.isAndroidQ())
            Utils.startIntent(context, Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
        else
            Utils.startIntent(context, Settings.ACTION_WIRELESS_SETTINGS);
    }

    /**
     * Open Mobile data Settings.
     *
     * @param context the application context
     */
    public static void openDataSettings(@NonNull Context context) {
        Utils.startIntent(context, Settings.ACTION_DATA_ROAMING_SETTINGS);
    }

    /**
     * Open NFC Settings, if the app run on device that has {@link Build.VERSION_CODES#Q} then it will open the new Settings panel.
     * you can read more about it <a href="https://developer.android.com/reference/kotlin/android/provider/Settings.Panel">here</a>
     *
     * @param context the application context
     */
    public static void openNfcSettings(@NonNull Context context) {
        if (PhoneUtils.isAndroidQ())
            Utils.startIntent(context, Settings.Panel.ACTION_NFC);
        else
            Utils.startIntent(context, Settings.ACTION_NFC_SETTINGS);
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
}