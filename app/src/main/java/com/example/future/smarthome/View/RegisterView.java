package com.example.future.smarthome.View;

import com.example.future.smarthome.data.Login.Login;
import com.example.future.smarthome.data.Register.Register;

import retrofit2.Response;

public interface RegisterView {
    void onSuccess();
    void onCancel();
    void onError(Response<Register> response);
    void onComplete();
    void onFailure(Throwable t);
}
