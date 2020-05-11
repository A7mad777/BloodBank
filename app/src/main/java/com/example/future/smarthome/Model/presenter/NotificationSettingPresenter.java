package com.example.future.smarthome.Model.presenter;

import java.util.List;

public interface NotificationSettingPresenter {
    void initialNotificationSettingData(String api_token, List<String> governors,List<String>blood_types);
}
