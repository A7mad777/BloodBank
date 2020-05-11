package com.example.future.smarthome.View;

import com.example.future.smarthome.data.Login.Login;

import retrofit2.Response;

public interface LoginView {
    void onSuccess();
    void onCancel();
    void onError(Response<Login> response);
    void onComplete();
    void onFailure(Throwable t);
}
