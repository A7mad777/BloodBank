package com.example.future.smarthome.View;

import java.util.List;

public interface NotificationSettingView {
    void onSuccess();
    void onFailure(Throwable throwable);
    void setAdapterGovenrors(List<String>governors);
    void setSpinnerGovernors();
    void setAdapterBloodTypes(List<String>bloodTypes);
    void setSpinnerBloodTypes(List<String>bloodTypes);
}
