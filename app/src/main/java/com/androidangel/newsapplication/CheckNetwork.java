package com.androidangel.newsapplication;

import android.content.Context;
import android.net.ConnectivityManager;

public class CheckNetwork {
    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean isNetworkNotAvailable(final Context context) {
        return !isNetworkAvailable(context);
    }
}


