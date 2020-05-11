package com.example.future.smarthome.View;

public interface DonationRequestCreateView {
    void onSuccess(String msg);
    void onCanceled();
    void onFailure(Throwable t);
}
