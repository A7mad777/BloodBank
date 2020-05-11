package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Fragments.Settings;
import com.example.future.smarthome.Model.presenter.NotificationSettingPresenter;
import com.example.future.smarthome.View.NotificationSettingView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.NotificationSetting.NotificationSetting;
import com.example.future.smarthome.data.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationSettingImpl implements NotificationSettingPresenter {
    NotificationSettingView notificationSettingView;
    private Context context;
    Call<NotificationSetting>notificationSettingCall;
    List<String>governors;
    List<String> blood_types;

    public NotificationSettingImpl(NotificationSettingView notificationSettingView, Context context) {
        this.notificationSettingView = notificationSettingView;
        this.context = context;
    }

    @Override
    public void initialNotificationSettingData(String api_token, List<String> governors, List<String> blood_types) {
        notificationSettingCall = RetrofitClient.getClient().create(Api.class).setNotificationSetting(api_token,governors,blood_types);
        Callback<NotificationSetting>callback = new CallBack();
        notificationSettingCall.enqueue(callback);
    }
    private class CallBack implements Callback<NotificationSetting>{
        @Override
        public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
            if (response.body().getStatus() == 1) {
                List<String> stringList = new ArrayList<>();
                List<Integer> integers = new ArrayList<>();
                governors = response.body().getData().getGovernorates();
                blood_types = response.body().getData().getBloodTypes();
                List<Integer> integerList = new ArrayList<>();
                notificationSettingView.setAdapterGovenrors(governors);
                notificationSettingView.setAdapterBloodTypes(blood_types);
            }
        }
        @Override
        public void onFailure(Call<NotificationSetting> call, Throwable t) {
            notificationSettingView.onFailure(t);
        }
    }
}
