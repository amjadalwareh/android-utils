package com.amjadalwareh.androidutils.networking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.amjadalwareh.androidutils.ConnectivityUtils

class NetworkReceiver(var context: Context?, var networkListener: NetworkListener?) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        networkListener?.let { it.isConnected(ConnectivityUtils.isNetworkConnected(context)) }
    }
}