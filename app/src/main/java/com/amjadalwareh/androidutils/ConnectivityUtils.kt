package com.amjadalwareh.androidutils

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.amjadalwareh.androidutils.networking.*

object ConnectivityUtils {

    /**
     * Check if the device is connected to network or not
     *
     * @param context The app context
     * @return `true` if connected, otherwise `false`
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetworkConnected(context: Context): Boolean {
        return isNetworkConnected(context, null)
    }

    /**
     * Check if the device is connected to network or not
     *
     * @param context The app context
     * @param listener A listener for network callback
     * @return `true` if connected, otherwise `false`
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetworkConnected(context: Context, listener: NetworkListener?): Boolean {
        val info = getActiveNetwork(context)

        listener?.apply {
            if (PhoneUtils.isLollipop()) {

                val networkRequest = NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .build()

                val connectivityManager = getConnectivityManager(context)
                connectivityManager.registerNetworkCallback(networkRequest, NetworkMonitor(listener))

            } else {
                val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                context.registerReceiver(NetworkReceiver(context, listener), intentFilter)
            }
        }

        return info != null && info.isConnected
    }

    /**
     * Check if device WiFi is connected or not.
     *
     * @param context The app context.
     * @return `true` if connected, otherwise `false`.
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isWifiConnected(context: Context): Boolean {
        return if (PhoneUtils.isMarshmallow()) {
            getNetworkCapabilities(context).hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            val info = getActiveNetwork(context) ?: return false
            info.type == ConnectivityManager.TYPE_WIFI
        }
    }

    /**
     * Check if device mobile data is on or not.
     *
     * @param context The app context.
     * @return `true` if on, otherwise `false`.
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isMobileDataConnected(context: Context): Boolean {
        return if (PhoneUtils.isMarshmallow()) {
            getNetworkCapabilities(context).hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            val info = getActiveNetwork(context) ?: return false
            info.type == ConnectivityManager.TYPE_MOBILE
        }
    }

    /**
     * Turn WiFi on
     * Please note that starting from [Build.VERSION_CODES.Q],applications are not allowed to
     * enable Wi-Fi.
     *
     * @param context The app context
     * @return `true` if WiFi state has changed, but if the app is targeting [Build.VERSION_CODES.Q] then will return `false` or if the state has been not changed.
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    fun turnOnWifi(context: Context): Boolean {
        return changeWiFiState(context, true)
    }

    /**
     * Turn WiFi off
     * Please note that starting from [Build.VERSION_CODES.Q],applications are not allowed to
     * disable Wi-Fi.
     *
     * @param context The app context
     * @return `true` if WiFi state has changed, but if the app is targeting [Build.VERSION_CODES.Q] then will return `false` or if the state has been not changed.
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    fun turnOffWifi(context: Context): Boolean {
        return changeWiFiState(context, false)
    }

    /**
     * Open WiFi Settings, if the app run on device that has [Build.VERSION_CODES.Q] then it will open the new Settings panel.
     * you can read more about it [here](https://developer.android.com/reference/kotlin/android/provider/Settings.Panel)
     *
     * @param context the application context
     */
    @JvmStatic
    fun openWiFiSettings(context: Context) {
        Utils.startIntent(context, if (PhoneUtils.isAndroidQ()) Settings.Panel.ACTION_INTERNET_CONNECTIVITY else Settings.ACTION_WIRELESS_SETTINGS)
    }

    /**
     * Open Mobile data Settings.
     *
     * @param context the application context
     */
    @JvmStatic
    fun openDataSettings(context: Context) {
        Utils.startIntent(context, Settings.ACTION_DATA_ROAMING_SETTINGS)
    }

    /**
     * Open NFC Settings, if the app run on device that has [Build.VERSION_CODES.Q] then it will open the new Settings panel.
     * you can read more about it [here](https://developer.android.com/reference/kotlin/android/provider/Settings.Panel)
     *
     * @param context the application context
     */
    @JvmStatic
    fun openNfcSettings(context: Context) {
        if (PhoneUtils.isAndroidQ()) Utils.startIntent(context, Settings.Panel.ACTION_NFC) else Utils.startIntent(context, Settings.ACTION_NFC_SETTINGS)
    }

    /**
     * Get the IP address of the device
     *
     * @param addressCallback It's the callback to get the result
     */
    @JvmStatic
    fun getIpAddress(addressCallback: AddressCallback) {
        AddressAdapter().getAddress(addressCallback)
    }

    private fun getConnectivityManager(context: Context): ConnectivityManager {
        return Utils.getService(context, Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun getNetworkCapabilities(context: Context): NetworkCapabilities {
        val manager = getConnectivityManager(context)
        return manager.getNetworkCapabilities(manager.activeNetwork)!!
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun getActiveNetwork(context: Context): NetworkInfo? {
        val manager = getConnectivityManager(context) ?: return null
        return manager.activeNetworkInfo
    }

    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    private fun changeWiFiState(context: Context, turnOn: Boolean): Boolean {
        return try {
            if (PhoneUtils.isAndroidQ()) return false
            val manager = Utils.getService(context, Context.WIFI_SERVICE) as WifiManager
            manager.setWifiEnabled(turnOn)
        } catch (e: Exception) {
            false
        }
    }
}