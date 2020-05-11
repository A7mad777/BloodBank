package com.example.future.smarthome.View;



public interface NotificationView {
    void onSuccess(int notificationCount);
    void onFailure(Throwable t);
}
