package com.damlacim.nobetcieczaneler.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.damlacim.nobetcieczaneler.R;
import com.damlacim.nobetcieczaneler.databinding.ActivityResultBinding;
import com.damlacim.nobetcieczaneler.model.PharmacyModel;
import com.damlacim.nobetcieczaneler.model.ProvinceModel;
import com.damlacim.nobetcieczaneler.ui.adapters.PharmacyAdapter;
import com.damlacim.nobetcieczaneler.ui.map.MapActivity;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements ResultListener, PharmacyAdapter.PharmacyClickListener {

    private String districtsStringValue;
    private ResultPresenter resultPresenter;
    private ActivityResultBinding resultBinding;
    private int cityIndexValue, districtsIndexValue;
    private final List<String> provinceList = new ArrayList<>();

    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
    private static final String PHARMACY_NAME = "PHARMACY_NAME";
    private static final String CITY_INDEX_VALUE = "CITY_INDEX_VALUE";
    private static final String DISTRICTS_INDEX_VALUE = "DISTRICTS_INDEX_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        resultPresenter = new ResultPresenter(ResultActivity.this, this);
        setContentView(resultBinding.getRoot());

        Intent intent = getIntent();
        cityIndexValue = intent.getIntExtra(CITY_INDEX_VALUE, 0);
        districtsIndexValue = intent.getIntExtra(DISTRICTS_INDEX_VALUE, 0);

        resultBinding.btnBack.setOnClickListener(v -> onBackPressed());
        setSearchedData();

        resultPresenter.fetchDutyPharmacies(cityIndexValue, districtsStringValue);
    }

    public void setSearchedData() {
        List<ProvinceModel> provinceModels = resultPresenter.getProvinceModels();
        for (int i = 0; i < provinceModels.size(); i++) {
            provinceList.add(provinceModels.get(i).getIl());
        }
        districtsStringValue = provinceModels.get(cityIndexValue).getIlceleri().get(districtsIndexValue);
        resultBinding.tvHeaderTitle.setText(districtsStringValue + "/" + provinceList.get(cityIndexValue));
    }

    @Override
    public void showLoading() {
        resultBinding.rvResult.setVisibility(View.GONE);
        resultBinding.lytHeader.setVisibility(View.GONE);
        resultBinding.loadingAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPage() {
        //TODO No Results Found Popup Will Be Added
    }

    @Override
    public void showDutyPharmaciesPage() {
        new Handler().postDelayed(() -> {
            resultBinding.rvResult.setVisibility(View.VISIBLE);
            resultBinding.lytHeader.setVisibility(View.VISIBLE);
            resultBinding.loadingAnimationView.setVisibility(View.GONE);

            List<PharmacyModel> pharmacyModelList = resultPresenter.getDutyPharmacies();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ResultActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            linearLayoutManager.scrollToPosition(0);
            resultBinding.rvResult.setLayoutManager(linearLayoutManager);
            PharmacyAdapter customAdapter = new PharmacyAdapter(pharmacyModelList, this);
            customAdapter.setOnClickListener(this);
            resultBinding.rvResult.setAdapter(customAdapter);
            if (pharmacyModelList.isEmpty())
                resultBinding.lytNotFound.setVisibility(View.VISIBLE);
        }, 1000);

    }

    @Override
    public void onClick(int position, PharmacyModel pharmacy) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(LATITUDE, Double.parseDouble(pharmacy.getEnlem()));
        intent.putExtra(LONGITUDE, Double.parseDouble(pharmacy.getBoylam()));
        intent.putExtra(PHARMACY_NAME, pharmacy.getEczaneAdi());

        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_left);
    }
}
