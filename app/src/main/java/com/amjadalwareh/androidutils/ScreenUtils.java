package com.amjadalwareh.androidutils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public final class ScreenUtils {

    private static InputMethodManager getInputMethodManager(View view) {
        return (InputMethodManager) Utils.getService(view.getContext(), Context.INPUT_METHOD_SERVICE);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * Hide the keyboard
     *
     * @param view The view that has the current focus
     */
    public static void hideKeyboard(View view) {
        if (Utils.isNull(view)) {
            return;
        }

        getInputMethodManager(view).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Show the keyboard
     *
     * @param view The view that has the current focus
     */
    public static void showKeyboard(View view) {
        if (Utils.isNull(view)) {
            return;
        }

        getInputMethodManager(view).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Converts dp to pixels.
     *
     * @param context Context to get resources and device specific display metrics
     * @param dp      A value we need to convert into pixels
     * @return A float number of dp value.
     */
    public static float dpToPixel(@NonNull Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics(context));
    }

    /**
     * Converts pixels to dp.
     *
     * @param context Context to get resources and device specific display metrics
     * @param px      A value we need to convert into dp
     * @return A float number of px value.
     */
    public static float pixelsToDp(@NonNull Context context, float px) {
        return px / getDisplayMetrics(context).density;
    }

}