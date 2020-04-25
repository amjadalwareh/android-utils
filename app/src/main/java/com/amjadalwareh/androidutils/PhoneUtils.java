package com.amjadalwareh.androidutils;

import android.content.Context;
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

    public static boolean isOreo() {
        return getVersion(Build.VERSION_CODES.O);
    }

    private static boolean getVersion(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

    public static boolean checkPermissionGranted(@NonNull Context context, String permission) {
        if (!isMarshmallow()) return true;
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

}