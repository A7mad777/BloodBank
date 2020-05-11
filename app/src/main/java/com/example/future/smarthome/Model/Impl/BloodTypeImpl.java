package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.BloodPresenter;
import com.example.future.smarthome.View.BloodTypeView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.ListOfBlood.Bloods;
import com.example.future.smarthome.data.ListOfBlood.ListOfBlood;
import com.example.future.smarthome.data.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodTypeImpl implements BloodPresenter {
    private BloodTypeView bloodTypeView;
    private Context context;
    Call<ListOfBlood>bloodCall;
    List<Bloods>bloodsList = new ArrayList<>();

    public BloodTypeImpl(BloodTypeView bloodTypeView, Context context) {
        this.bloodTypeView = bloodTypeView;
        this.context = context;
    }

    @Override
    public void initialBloodData() {
        bloodCall = RetrofitClient.getClient().create(Api.class).getBloodTypes();
        Callback<ListOfBlood> callback = new CallBack();
        bloodCall.enqueue(callback);
    }
    private class CallBack implements Callback<ListOfBlood>{
        @Override
        public void onResponse(Call<ListOfBlood> call, Response<ListOfBlood> response) {
            bloodsList = response.body().getData();
            List<String>stringList = new ArrayList<>();
            stringList.add("إختر فصيلة الدم");
            List<Integer>integerList = new ArrayList<>();
            for (int i = 0 ; i < bloodsList.size(); i++){
                stringList.add(bloodsList.get(i).getName());
                integerList.add(bloodsList.get(i).getId());
                bloodTypeView.setBloodAdapter(bloodsList,stringList,integerList);
                bloodTypeView.setBloodSpinner(integerList);
            }
        }
        @Override
        public void onFailure(Call<ListOfBlood> call, Throwable t) {

        }
    }
}
