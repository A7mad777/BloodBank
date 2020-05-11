package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.future.smarthome.Model.presenter.ProfilePresenter;
import com.example.future.smarthome.View.ProfileView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileImpl implements ProfilePresenter {
    private ProfileView profileView;
    private Context context;
    Call<com.example.future.smarthome.data.Profile.Profile>profileCall;

    public ProfileImpl(ProfileView profileView, Context context) {
        this.profileView = profileView;
        this.context = context;
    }


    @Override
    public void initialProfileData(String name, String email, String birth_date, int city_id, String phone, String donation_date, String passWord, String passWord_confirm, int blood_id, String api_token) {
        if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(email.trim())){
            profileView.onCanceled();
        }else if (TextUtils.isEmpty(birth_date) && TextUtils.isEmpty(phone)){
            profileView.onCanceled();
        }else if (TextUtils.isEmpty(donation_date)&& TextUtils.isEmpty(passWord.trim()) && TextUtils.isEmpty(passWord_confirm.trim())){
           profileView.onCanceled();

        }else if (!TextUtils.equals(passWord,passWord_confirm)){
           profileView.onPassField();
        }else {
            EditProfie(name,email,birth_date,city_id,phone,donation_date,passWord,passWord_confirm,blood_id,api_token);
        }

    }

    private void EditProfie(String name, String email, String birth_date, int city_id, String phone, String donation_date, String passWord, String passWord_confirm, int blood_id, String api_token) {
        profileCall = RetrofitClient.getClient().create(Api.class).editProfile(name,email,birth_date,city_id,phone,donation_date,passWord,passWord_confirm,blood_id,api_token);
        Callback<com.example.future.smarthome.data.Profile.Profile>callback = new CallBack();
        profileCall.enqueue(callback);
    }

    private class CallBack implements Callback<com.example.future.smarthome.data.Profile.Profile>{

        @Override
        public void onResponse(Call<com.example.future.smarthome.data.Profile.Profile> call, Response<com.example.future.smarthome.data.Profile.Profile> response) {
            if (response.body().getStatus() == 1){
                profileView.onSuccess(response.body().getMsg());
            }else {
                profileView.onError(response.body().getMsg());
            }
        }

        @Override
        public void onFailure(Call<com.example.future.smarthome.data.Profile.Profile> call, Throwable t) {
            profileView.onFailure(t);
        }
    }
}
