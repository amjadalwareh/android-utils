package com.amjadalwareh.androidutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;

public final class PhoneUtils {

    /**
     * Check if device version is Android Lollipop with API 21
     * You can read more about this version <a href="https://developer.android.com/about/versions/lollipop">here</a>
     *
     * @return {@code true} if it is api 21, otherwise false.
     */
    public static boolean isLollipop() {
        return getVersion(Build.VERSION_CODES.LOLLIPOP);
    }

    /**
     * Check if device version is Android Marshmallow with API 23
     * You can read more about this version <a href="https://developer.android.com/about/versions/marshmallow">here</a>
     *
     * @return {@code true} if it is api 23, otherwise false.
     */
    public static boolean isMarshmallow() {
        return getVersion(Build.VERSION_CODES.M);
    }

    /**
     * Check if device version is Android Nougat with API 24
     * You can read more about this version <a href="https://developer.android.com/about/versions/nougat">here</a>
     *
     * @return {@code true} if it is api 24, otherwise false.
     */
    public static boolean isNougat() {
        return getVersion(Build.VERSION_CODES.N);
    }

    /**
     * Check if device version is Android Oreo with API 26
     * You can read more about this version <a href="https://developer.android.com/about/versions/oreo">here</a>
     *
     * @return {@code true} if it is api 26, otherwise false.
     */
    public static boolean isOreo() {
        return getVersion(Build.VERSION_CODES.O);
    }

    /**
     * Check if device version is Android Pie with API 28
     * You can read more about this version <a href="https://developer.android.com/about/versions/pie">here</a>
     *
     * @return {@code true} if it is api 28, otherwise false.
     */
    public static boolean isPie() {
        return getVersion(Build.VERSION_CODES.P);
    }

    /**
     * Check if device version is Android 10 with API 29
     * You can read more about this version <a href="https://developer.android.com/about/versions/10">here</a>
     *
     * @return {@code true} if it is api 29, otherwise false.
     */
    public static boolean isAndroidQ() {
        return getVersion(Build.VERSION_CODES.Q);
    }

    /**
     * Check if an application is installed or not.
     * Please note that if the application is installed but it is not enabled then the method will continue to return {@code true}.
     *
     * @param context     the context of the application
     * @param packageName the package name to search for
     * @return {@code true} if the application is exist, otherwise return {@code false}
     */
    public static boolean isPackageInstalled(@NonNull Context context, @NonNull String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if requested permission is granted or not.
     *
     * @param context    the application context.
     * @param permission The requested permission.
     * @return {@code true} if permission is granted, otherwise {@code false}
     */
    public static boolean checkPermissionGranted(@NonNull Context context, String permission) {
        if (!isMarshmallow()) return true;
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean getVersion(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

}