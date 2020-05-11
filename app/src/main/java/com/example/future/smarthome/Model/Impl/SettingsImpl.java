package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Fragments.ContactUs;
import com.example.future.smarthome.Model.presenter.SettingPresenter;
import com.example.future.smarthome.View.ContactView;
import com.example.future.smarthome.View.SettingsView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.RetrofitClient;
import com.example.future.smarthome.data.Settings.Data;
import com.example.future.smarthome.data.Settings.Settings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsImpl implements SettingPresenter {
    private SettingsView settingsView;
    private Context context;
    Call<Settings> settingsCall;
    SettingPresenter presenter;

    public SettingsImpl(SettingsView settingsView, Context context) {
        this.settingsView = settingsView;
        this.context = context;
    }

    @Override
    public void initialSettingPresenter() {
        settingsCall = RetrofitClient.getClient().create(Api.class).getSetting("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        Callback<Settings> callback = new CallBack();
        settingsCall.enqueue(callback);
    }


    private class CallBack implements Callback<Settings>{
        @Override
        public void onResponse(Call<Settings> call, Response<Settings> response) {
            settingsView.onSuccess(response.body().getData());
        }
        @Override
        public void onFailure(Call<Settings> call, Throwable t) {
            settingsView.onFailure(t);

        }
    }
}
