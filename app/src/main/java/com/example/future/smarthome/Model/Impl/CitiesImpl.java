package com.example.future.smarthome.Model.Impl;

import android.app.Activity;
import android.content.Context;

import com.example.future.smarthome.Model.presenter.CitiesPresenter;
import com.example.future.smarthome.View.CitiesView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.ListOofCities.Datum;
import com.example.future.smarthome.data.ListOofCities.ListOfCities;
import com.example.future.smarthome.data.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitiesImpl implements CitiesPresenter {
    private CitiesView citiesView;
    private Context context;
    Call<ListOfCities>citiesCall;
    List<Datum>cities;


    public CitiesImpl(CitiesView citiesView, Context context) {
        this.citiesView = citiesView;
        this.context = context;
    }
    @Override
    public void setCitiesData(int id) {
        citiesCall = RetrofitClient.getClient().create(Api.class).getListOfCities(id);
        Callback<ListOfCities> callback = new CallBack();
        citiesCall.enqueue(callback);
    }
    private class CallBack implements Callback<ListOfCities>{
        @Override
        public void onResponse(Call<ListOfCities> call, Response<ListOfCities> response) {
            cities = response.body().getData();
            List<String> stringList = new ArrayList<>();
            List<Integer>integerList = new ArrayList<>();
            stringList.add("إختر المدينـه");
            for (int i = 0; i < response.body().getData().size(); i++) {
                stringList.add(response.body().getData().get(i).getName());
                integerList.add(response.body().getData().get(i).getId());
                citiesView.setAdapterCity(stringList,integerList);
                citiesView.setCitySpinner(integerList);
            }
        }

        @Override
        public void onFailure(Call<ListOfCities> call, Throwable t) {

        }
    }
}
