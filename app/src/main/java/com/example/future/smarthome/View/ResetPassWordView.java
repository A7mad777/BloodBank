package com.example.future.smarthome.View;

public interface ResetPassWordView {
    void onSuccess(String msg,String pincode);
    void onFailure(String message);
    void onComplete(String msg);
}
