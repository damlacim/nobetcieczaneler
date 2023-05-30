package com.damlacim.nobetcieczaneler.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.damlacim.nobetcieczaneler.R;
import com.damlacim.nobetcieczaneler.databinding.ActivityMainBinding;
import com.damlacim.nobetcieczaneler.model.ProvinceModel;
import com.damlacim.nobetcieczaneler.ui.customView.CustomAlertDialog;
import com.damlacim.nobetcieczaneler.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, com.damlacim.nobetcieczaneler.ui.main.MainListener {

    private MainPresenter mainPresenter;
    private ActivityMainBinding mainBinding;
    private CustomAlertDialog customAlertDialog;
    private int cityIndexValue, districtsIndexValue;
    private List<ProvinceModel> provinceModels = new ArrayList<>();

    private static final String CITY_INDEX_VALUE = "CITY_INDEX_VALUE";
    private static final String DISTRICTS_INDEX_VALUE = "DISTRICTS_INDEX_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mainPresenter = new MainPresenter(MainActivity.this, this);
        initViews();
        setContentView(mainBinding.getRoot());
    }

    public void initViews() {
        List<String> provinceList = mainPresenter.getProvince();
        provinceModels = mainPresenter.getProvinceModels();

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_list_item,
                provinceList);
        mainBinding.spinnerCity.setAdapter(provinceAdapter);
        mainBinding.spinnerCity.setOnItemSelectedListener(this);

        mainBinding.tvSearchBtn.setOnClickListener(v -> {
            cityIndexValue = mainBinding.spinnerCity.getSelectedItemPosition();
            districtsIndexValue = mainBinding.spinnerDistrict.getSelectedItemPosition();
            mainPresenter.isValid(cityIndexValue);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int cityIndexValue = mainBinding.spinnerCity.getSelectedItemPosition();
        List<String> districtsList = provinceModels.get(cityIndexValue).getIlceleri();

        ArrayAdapter<String> districtsAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_list_item,
                districtsList);
        mainBinding.spinnerDistrict.setAdapter(districtsAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showErrorPopup() {
        customAlertDialog = new CustomAlertDialog(this);
        customAlertDialog.setTitle(R.string.selection_error_title);
        customAlertDialog.setDescription(R.string.selection_error_desc);
        customAlertDialog.setPositiveButton(getString(R.string.okay), v -> customAlertDialog.dismiss());
        customAlertDialog.show();
    }

    @Override
    public void openResultPage() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CITY_INDEX_VALUE, cityIndexValue);
        intent.putExtra(DISTRICTS_INDEX_VALUE, districtsIndexValue);

        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_left);
    }
}