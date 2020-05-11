package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.future.smarthome.Model.presenter.NewPassWordPresenter;
import com.example.future.smarthome.View.NewPassWordView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.NewPassWord.NewPassWord;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPassWordImpl implements NewPassWordPresenter {
    private NewPassWordView newPassWordView;
    private Context context;
    Call<NewPassWord> newPassWordCall;

    public NewPassWordImpl(NewPassWordView newPassWordView, Context context) {
        this.newPassWordView = newPassWordView;
        this.context = context;
    }

    @Override
    public void initialNewPassWordData(String passWord, String confirm_password, String pin_code, String phone) {
        if (TextUtils.isEmpty(passWord) && TextUtils.isEmpty(confirm_password)){
            newPassWordView.onFailure("أدخل البيانات كاملة");
        }else if (!TextUtils.equals(passWord,confirm_password)){
            newPassWordView.onFailure("أدخل البيانات كاملة");

        }else if (TextUtils.isEmpty(pin_code) && TextUtils.isEmpty(phone)){
            newPassWordView.onFailure("أدخل البيانات كاملة");

        }else {
            setUpNesPassWord(passWord,confirm_password,pin_code,phone);
        }
    }

    private void setUpNesPassWord(String passWord, String confirm_password, String pin_code, String phone) {
        newPassWordCall = RetrofitClient.getClient().create(Api.class).setNewPassWord(passWord, confirm_password, pin_code, phone);
        Callback<NewPassWord>callback = new CallBack();
        newPassWordCall.enqueue(callback);
    }
    private class CallBack implements Callback<NewPassWord>{
        @Override
        public void onResponse(Call<NewPassWord> call, Response<NewPassWord> response) {
          if (response.body().getStatus() == 1){
              newPassWordView.onSuccess(response.body().getMsg());
          }
        }
        @Override
        public void onFailure(Call<NewPassWord> call, Throwable t) {
            newPassWordView.onFailure(t.getMessage());
        }
    }
}
