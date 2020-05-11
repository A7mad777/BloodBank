package com.example.future.smarthome.View;

public interface ReportView {
    void onSuccess();
    void onFailure(Throwable throwable);
    void onError();
}
