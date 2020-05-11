package com.example.future.smarthome.View;

public interface ProfileView {
    void onSuccess(String msg);
    void onFailure(Throwable t);
    void onCanceled();
    void onPassField();
    void onError(String msg);

}
