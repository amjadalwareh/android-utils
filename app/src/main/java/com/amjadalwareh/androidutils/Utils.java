package com.amjadalwareh.androidutils;

import android.content.Context;

import androidx.annotation.NonNull;

final class Utils {

    public static <T> boolean isNull(T obj) {
        return obj == null;
    }

    public static Object getService(@NonNull Context context, @NonNull String name) {
        return context.getSystemService(name);
    }
}