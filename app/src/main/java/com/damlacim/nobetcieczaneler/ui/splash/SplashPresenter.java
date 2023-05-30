package com.damlacim.nobetcieczaneler.ui.splash;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatDelegate;

public class SplashPresenter {

    private final SplashListener listener;
    private final Context context;

    public SplashPresenter(Context context, SplashListener listener) {
        this.context = context;
        this.listener = listener;
        disableDarkMode();
    }

    private void disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void isInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            listener.openMainPage();
        else
            listener.showNetworkErrorDialog();
    }
}
