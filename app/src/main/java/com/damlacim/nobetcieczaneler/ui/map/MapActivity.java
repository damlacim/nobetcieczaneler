package com.damlacim.nobetcieczaneler.ui.map;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.damlacim.nobetcieczaneler.R;
import com.damlacim.nobetcieczaneler.databinding.ActivityMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private String pharmacyName;
    private double latitude, longitude;
    private ActivityMapBinding activityMapBinding;

    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
    private static final String PHARMACY_NAME = "PHARMACY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMapBinding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(activityMapBinding.getRoot());

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra(LATITUDE, 0);
        longitude = intent.getDoubleExtra(LONGITUDE, 0);
        pharmacyName = intent.getStringExtra(PHARMACY_NAME);

        setupViews();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void setupViews(){
        if (!pharmacyName.contains("ECZANESİ"))
            activityMapBinding.tvPharmacyName.setText(pharmacyName + " " + getString(R.string.pharmacy));
        else
            activityMapBinding.tvPharmacyName.setText(pharmacyName);

        activityMapBinding.btnBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setCompassEnabled(true); // Compass button activated
        googleMap.getUiSettings().setZoomControlsEnabled(true); // Zoom in/out buttons activated
        googleMap.setMinZoomPreference(20f);

        // Add a marker in Pharmacy and move the camera
        LatLng pharmacy = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(pharmacy).title(pharmacyName + " " + getString(R.string.pharmacy)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pharmacy));
    }
}