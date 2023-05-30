package com.damlacim.nobetcieczaneler.ui.main;

import android.content.Context;

import com.damlacim.nobetcieczaneler.model.ProvinceModel;
import com.damlacim.nobetcieczaneler.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private final Context context;
    private final MainListener listener;
    private final List<String> provinceList = new ArrayList<>();
    private List<ProvinceModel> provinceModels = new ArrayList<>();
    private static final String JSON_FILE_NAME = "turkey_provinces_and_districts.json";

    public MainPresenter(Context context, MainListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public List<String> getProvince() {
        provinceModels = getProvinceModels();
        for (int i = 0; i < provinceModels.size(); i++)
            provinceList.add(provinceModels.get(i).getIl());
        return provinceList;
    }

    public List<ProvinceModel> getProvinceModels() {
        String jsonFileString = JsonUtils.getJsonFromAssets(context, JSON_FILE_NAME);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<ProvinceModel>>() {
        }.getType();

        provinceModels = gson.fromJson(jsonFileString, listUserType);
        return provinceModels;
    }

    public void isValid(int cityIndexValue) {
        if (cityIndexValue == 0)
            listener.showErrorPopup();
        else
            listener.openResultPage();
    }
}
