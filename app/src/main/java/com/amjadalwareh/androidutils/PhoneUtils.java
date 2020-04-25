package com.amjadalwareh.androidutils;

import android.os.Build;

public final class PhoneUtils {

    public static boolean isLollipop() {
        return getVersion(Build.VERSION_CODES.LOLLIPOP);
    }

    public static boolean isMarshmallow() {
        return getVersion(Build.VERSION_CODES.M);
    }

    private static boolean getVersion(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

}