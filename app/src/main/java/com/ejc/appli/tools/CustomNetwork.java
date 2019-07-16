package com.ejc.appli.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CustomNetwork {

    public static boolean isNetworkAvailable(Context contexte) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) contexte.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
