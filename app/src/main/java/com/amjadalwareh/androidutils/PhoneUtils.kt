package com.amjadalwareh.androidutils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

object PhoneUtils {

    /**
     * Check if device version is Android Lollipop with API 21
     * You can read more about this version [here](https://developer.android.com/about/versions/lollipop)
     *
     * @return `true` if it is api 21, otherwise false.
     */
    fun isLollipop(): Boolean = getVersion(Build.VERSION_CODES.LOLLIPOP)

    /**
     * Check if device version is Android Marshmallow with API 23
     * You can read more about this version [here](https://developer.android.com/about/versions/marshmallow)
     *
     * @return `true` if it is api 23, otherwise false.
     */
    fun isMarshmallow(): Boolean = getVersion(Build.VERSION_CODES.M)

    /**
     * Check if device version is Android Nougat with API 24
     * You can read more about this version [here](https://developer.android.com/about/versions/nougat)
     *
     * @return `true` if it is api 24, otherwise false.
     */
    fun isNougat(): Boolean = getVersion(Build.VERSION_CODES.N)

    /**
     * Check if device version is Android Oreo with API 26
     * You can read more about this version [here](https://developer.android.com/about/versions/oreo)
     *
     * @return `true` if it is api 26, otherwise false.
     */
    fun isOreo(): Boolean = getVersion(Build.VERSION_CODES.O)

    /**
     * Check if device version is Android Pie with API 28
     * You can read more about this version [here](https://developer.android.com/about/versions/pie)
     *
     * @return `true` if it is api 28, otherwise false.
     */
    fun isPie(): Boolean = getVersion(Build.VERSION_CODES.P)

    /**
     * Check if device version is Android 10 with API 29
     * You can read more about this version [here](https://developer.android.com/about/versions/10)
     *
     * @return `true` if it is api 29, otherwise false.
     */
    fun isAndroidQ(): Boolean = getVersion(Build.VERSION_CODES.Q)

    /**
     * Check if an application is installed or not.
     * Please note that if the application is installed but it is not enabled then the method will continue to return `true`.
     *
     * @param context     the context of the application
     * @param packageName the package name to search for
     * @return `true` if the application is exist, otherwise return `false`
     */
    fun isPackageInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    /**
     * Check if requested permission is granted or not.
     *
     * @param context    the application context.
     * @param permission The requested permission.
     * @return `true` if permission is granted, otherwise `false`
     */
    fun checkPermissionGranted(context: Context, permission: String?): Boolean {
        return if (!isMarshmallow()) true else context.checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    private fun getVersion(version: Int): Boolean {
        return Build.VERSION.SDK_INT >= version
    }
}