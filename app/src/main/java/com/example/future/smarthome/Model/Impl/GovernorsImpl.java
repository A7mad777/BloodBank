package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.widget.Spinner;

import com.example.future.smarthome.Model.presenter.GovernorsPresenter;
import com.example.future.smarthome.View.CitiesView;
import com.example.future.smarthome.View.ListOfGovernorsView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.ListOfGovernorates.Governors;
import com.example.future.smarthome.data.ListOfGovernorates.ListOfGovernorates;
import com.example.future.smarthome.data.ListOofCities.Datum;
import com.example.future.smarthome.data.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GovernorsImpl implements GovernorsPresenter{
    ListOfGovernorsView listOfGovernorsView;
    private Context context;
    Call<ListOfGovernorates>governorsCall;
    List<Governors> governorsList;
    Spinner spinner;
    public GovernorsImpl(ListOfGovernorsView listOfGovernorsView, Context context) {
        this.listOfGovernorsView = listOfGovernorsView;
        this.context = context;
    }
    @Override
    public void setDataOfGovernors() {
        governorsCall = RetrofitClient.getClient().create(Api.class).getListOfGovernors();
        Callback<ListOfGovernorates> callback = new CallBack();
        governorsCall.enqueue(callback);
    }

    private class CallBack implements Callback<ListOfGovernorates>{
        @Override
        public void onResponse(Call<ListOfGovernorates> call, Response<ListOfGovernorates> response) {
            List<String>stringList = new ArrayList<>();
            List<Integer>integers = new ArrayList<>();
            stringList.add("إختر المحافظة");
            integers.add(0);
            governorsList = response.body().getData();
            for (int i = 0 ; i <response.body().getData().size(); i++) {
                stringList.add(response.body().getData().get(i).getName());
                integers.add(response.body().getData().get(i).getId());
                listOfGovernorsView.setAdapter(stringList,integers);
                listOfGovernorsView.setSpinner(integers);
            }
        }
        @Override
        public void onFailure(Call<ListOfGovernorates> call, Throwable t) {
            listOfGovernorsView.onFailure(t);
        }
    }
}
