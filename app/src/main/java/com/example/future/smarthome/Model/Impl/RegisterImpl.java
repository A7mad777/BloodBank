package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.future.smarthome.Model.presenter.RegistrerPresenter;
import com.example.future.smarthome.View.LoginView;
import com.example.future.smarthome.View.RegisterView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.Register.Register;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterImpl implements RegistrerPresenter {
    RegisterView registerView;
    private Context context;
    Call<Register> registerCall;

    public RegisterImpl(RegisterView registerView, Context context) {
        this.registerView = registerView;
        this.context = context;
    }

    @Override
    public void setRegister(String name, String email, String birth_date, int city_id, String phone, String donation_lastDate, String passWord, String passWord_Confirm, int blood_type) {
        int blood_type_id = 1;
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone)) {
            registerView.onCancel();
        } else if (TextUtils.isEmpty(email.trim()) && TextUtils.isEmpty(passWord.trim()) && TextUtils.isEmpty(birth_date)) {
            registerView.onCancel();
        } else if (!TextUtils.equals(passWord, passWord_Confirm)) {
            registerView.onCancel();
        } else if (TextUtils.isEmpty(donation_lastDate)) {
           registerView.onCancel();
        } else {
           RegisterThis(name,email,birth_date,city_id,phone,donation_lastDate,passWord,passWord_Confirm,blood_type);
        }
    }

    private void RegisterThis(String name, String email, String birth_date, int city_id, String phone, String donation_lastDate, String passWord, String passWord_confirm, int blood_type) {
        registerCall = RetrofitClient.getClient().create(Api.class).creatUser(name,email,birth_date,city_id,phone,donation_lastDate,passWord,passWord_confirm,blood_type);
        Callback<Register> callback = new CallBack();
        registerCall.enqueue(callback);
    }
    private class CallBack implements Callback<Register>{

        @Override
        public void onResponse(Call<Register> call, Response<Register> response) {
            if (response.body().getStatus() == 1){
                registerView.onSuccess();
            }else {
                registerView.onError(response);
            }
        }
        @Override
        public void onFailure(Call<Register> call, Throwable t) {
            registerView.onFailure(t);
        }
    }
}

