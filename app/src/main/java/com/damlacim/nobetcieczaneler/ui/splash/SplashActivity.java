package com.damlacim.nobetcieczaneler.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.damlacim.nobetcieczaneler.R;
import com.damlacim.nobetcieczaneler.databinding.ActivitySplashBinding;
import com.damlacim.nobetcieczaneler.ui.customView.CustomAlertDialog;
import com.damlacim.nobetcieczaneler.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashListener {

    private SplashPresenter splashPresenter;
    private ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        splashPresenter = new SplashPresenter(this, SplashActivity.this);
        splashPresenter.isInternetConnection();
        setContentView(splashBinding.getRoot());
    }

    @Override
    public void openMainPage() {
        runOnUiThread(() -> new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(R.anim.anim_slide_in_right,
                    R.anim.anim_slide_out_left);
        }, 1000));
    }

    @Override
    public void showNetworkErrorDialog() {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(this);
        customAlertDialog.showAnimation();
        customAlertDialog.setTitle(R.string.no_internet_title);
        customAlertDialog.setDescription(R.string.no_internet_desc);
        customAlertDialog.setPositiveButton(getString(R.string.okay), v -> finish());
        customAlertDialog.setCancelButton(getString(R.string.open_internet_settings), v -> {
            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            finish();
        });
        customAlertDialog.show();
    }
}
