package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.NotificationListPresenter;
import com.example.future.smarthome.View.NotificationListView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.NotificationList.NotificationList;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationListImpl implements NotificationListPresenter {
    private Context context;
    private NotificationListView notificationListView;
    Call<NotificationList>notificationListCall;

    public NotificationListImpl(NotificationListView notificationListView,Context context) {
        this.context = context;
        this.notificationListView = notificationListView;
    }

    @Override
    public void initialNotificationListData() {
        notificationListCall = RetrofitClient.getClient().create(Api.class).getNotificationList("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl");
        Callback<NotificationList> callback = new CallBack();
        notificationListCall.enqueue(callback);
    }
    private class CallBack implements Callback<NotificationList>{

        @Override
        public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
            notificationListView.onSuccess(response.body().getData().getData());
        }

        @Override
        public void onFailure(Call<NotificationList> call, Throwable t) {
            notificationListView.onFailure(t);
        }
    }
}
