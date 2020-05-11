package com.example.future.smarthome.View;

import com.example.future.smarthome.data.Settings.Data;

public interface SettingsView {
    void onFailure(Throwable t);
    void onSuccess(Data data);
}
