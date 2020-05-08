package com.amjadalwareh.androidutils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission

class NetworkReceiver(var context: Context?, var networkListener: NetworkListener?) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        networkListener?.let { it.isConnected(ConnectivityUtils.isNetworkConnected(context)) }
    }
}