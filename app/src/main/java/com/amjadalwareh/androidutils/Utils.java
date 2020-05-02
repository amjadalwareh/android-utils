package com.amjadalwareh.androidutils;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

final class Utils {

    static <T> boolean isNull(T obj) {
        return obj == null;
    }

    static Object getService(@NonNull Context context, @NonNull String name) {
        return context.getSystemService(name);
    }

    static void startIntent(@NonNull Context context, @NonNull String intentName) {
        Intent intent = new Intent(intentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}