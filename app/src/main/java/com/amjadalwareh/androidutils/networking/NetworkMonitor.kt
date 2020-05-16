package com.amjadalwareh.androidutils.networking

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import com.amjadalwareh.androidutils.networking.NetworkListener

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkMonitor(var networkListener: NetworkListener) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        networkListener.isConnected(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        networkListener.isConnected(false)
    }

    override fun onUnavailable() {
        super.onUnavailable()
        networkListener.isConnected(false)
    }
}