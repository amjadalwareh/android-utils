package com.amjadalwareh.androidutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;

public final class PhoneUtils {

    public static boolean isLollipop() {
        return getVersion(Build.VERSION_CODES.LOLLIPOP);
    }

    public static boolean isMarshmallow() {
        return getVersion(Build.VERSION_CODES.M);
    }

    public static boolean isNougat() {
        return getVersion(Build.VERSION_CODES.N);
    }

    public static boolean isOreo() {
        return getVersion(Build.VERSION_CODES.O);
    }

    public static boolean isPie() {
        return getVersion(Build.VERSION_CODES.P);
    }

    public static boolean isAndroidQ() {
        return getVersion(Build.VERSION_CODES.Q);
    }

//    public static boolean isAndroid11() {
//        return getVersion(Build.VERSION_CODES.R);
//    }

    /**
     * Check if an App is installed or not.
     * Please note that if the app is installed but it is not enabled then the method will continue to return true.
     *
     * @param context     the context of the application
     * @param packageName the package name to search for
     * @return true if the app is exist, otherwise return false
     */
    public static boolean isPackageInstalled(@NonNull Context context, @NonNull String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private static boolean getVersion(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

    public static boolean checkPermissionGranted(@NonNull Context context, String permission) {
        if (!isMarshmallow()) return true;
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

}