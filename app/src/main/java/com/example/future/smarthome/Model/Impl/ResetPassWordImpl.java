package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.future.smarthome.Model.presenter.ResetPassWordPresenter;
import com.example.future.smarthome.View.ResetPassWordView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.ResetPassWord.ResetPassWord;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassWordImpl implements ResetPassWordPresenter {
    ResetPassWordView resetPassWordView;
    private Context context;
    Call<ResetPassWord>resetPassWordCall;

    public ResetPassWordImpl(ResetPassWordView resetPassWordView, Context context) {
        this.resetPassWordView = resetPassWordView;
        this.context = context;
    }

    @Override
    public void initialResetData(String phone) {
        if (!TextUtils.isEmpty(phone)){
            sendPhone(phone);
        }else {
            resetPassWordView.onComplete("أدخل رقم الهاتف");
        }
    }

    private void sendPhone(String phone) {
        resetPassWordCall = RetrofitClient.getClient().create(Api.class).setResetPassWord(phone);
        Callback<ResetPassWord> callback = new CallBack();
        resetPassWordCall.enqueue(callback);
    }

    private class CallBack implements Callback<ResetPassWord>{

        @Override
        public void onResponse(Call<ResetPassWord> call, Response<ResetPassWord> response) {
            if (response.body().getStatus() == 1){
                resetPassWordView.onSuccess(response.body().getMsg(),response.body().getData().getPinCodeForTest().toString());

            }
        }
        @Override
        public void onFailure(Call<ResetPassWord> call, Throwable t) {
            resetPassWordView.onFailure(t.getMessage());
        }
    }
}
