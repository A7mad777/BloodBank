package com.example.future.smarthome.View;

import android.view.View;

import com.example.future.smarthome.data.ContactUs.Data;

import java.util.List;

public interface ContactView {
    void onSuccess(Data contacts);
    void onFailure();
    void onError(Throwable t);

}
