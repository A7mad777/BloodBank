package com.example.future.smarthome.View;

import com.example.future.smarthome.data.NotificationList.Datum;
import com.example.future.smarthome.data.NotificationList.NotificationList;

import java.util.List;

public interface NotificationListView {
    void onSuccess(List<Datum> notificationLists);
    void onFailure(Throwable t);
}
