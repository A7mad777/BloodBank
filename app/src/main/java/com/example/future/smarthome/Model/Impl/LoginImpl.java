package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.future.smarthome.Model.presenter.LoginPresenter;
import com.example.future.smarthome.View.LoginView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.Login.Login;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginImpl implements LoginPresenter {
    private Context context;
    private LoginView loginView;

    public LoginImpl(LoginView loginView, Context context) {
        this.context = context;
        this.loginView = loginView;
    }
    @Override
    public void setLogin(String phone, String passWord) {
        if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(passWord.trim())){
            Login(phone,passWord);
        }else {
          loginView.onComplete();
        }
    }
    private void Login(String phone, String passWord) {
        Call loginCall = RetrofitClient.getClient().create(Api.class).setUser(phone,passWord);
        Callback<Login> callback = new CallBack();
        loginCall.enqueue(callback);
    }
    private class CallBack implements Callback<Login>{
        @Override
        public void onResponse(Call<Login> call, Response<Login> response) {

            if (response.body().getStatus() == 1) {
                loginView.onSuccess();
            }else {
            loginView.onError(response);
            }
        }
        @Override
        public void onFailure(Call<Login> call, Throwable t) {
            loginView.onFailure(t);
        }
    }
}
