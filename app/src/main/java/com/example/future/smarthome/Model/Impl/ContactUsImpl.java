package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.future.smarthome.Model.presenter.ContactUsPresenter;
import com.example.future.smarthome.View.ContactView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.ContactUs.ContactUs;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsImpl implements ContactUsPresenter {
    private ContactView contactView;
    private Context context;
    Call<ContactUs>contactUsCall;

    public ContactUsImpl(ContactView contactView, Context context) {
        this.contactView = contactView;
        this.context = context;
    }

    @Override
    public void initialContactsData(String name,String phone,String message,String email,String title) {
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone)) {
            contactView.onFailure();
        } else if (TextUtils.isEmpty(message) && TextUtils.isEmpty(email) && TextUtils.isEmpty(title)) {
           contactView.onFailure();
        } else {
            contactCall();
        }
    }
    private class CallBack implements Callback<ContactUs>{
        @Override
        public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
            if (response.body().getStatus() == 1){
                contactView.onSuccess(response.body().getData());
            }
        }
        @Override
        public void onFailure(Call<ContactUs> call, Throwable t) {
            contactView.onError(t);
        }
    }
    private void contactCall(){
        contactUsCall = RetrofitClient.getClient().create(Api.class).createContact("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27","Test Contact","Help me");
        Callback<ContactUs> callback = new CallBack();
        contactUsCall.enqueue(callback);
    }
}
