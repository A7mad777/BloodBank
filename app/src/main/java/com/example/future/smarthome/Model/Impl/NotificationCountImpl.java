package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.NotificationPresenter;
import com.example.future.smarthome.View.NotificationView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.NotificationCount.NotificationCount;
import com.example.future.smarthome.data.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationCountImpl implements NotificationPresenter {
    private Context context;
    private NotificationView notificationView;
    Call<NotificationCount>notificationCountCall;

    public NotificationCountImpl(NotificationView notificationView, Context context) {
        this.context = context;
        this.notificationView = notificationView;
    }

    @Override
    public void initailNotificationData(String api_token) {
        notificationCountCall = RetrofitClient.getClient().create(Api.class).getNotificationCount("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl");
        Callback<NotificationCount> callback = new CallBack();
        notificationCountCall.enqueue(callback);
    }
    private class CallBack implements Callback<NotificationCount>{

        @Override
        public void onResponse(Call<NotificationCount> call, Response<NotificationCount> response) {
          notificationView.onSuccess(response.body().getData().getNotificationsCount());
        }

        @Override
        public void onFailure(Call<NotificationCount> call, Throwable t) {
            notificationView.onFailure(t);
        }
    }
}
