package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.future.smarthome.Model.presenter.DonationRequestCreatePresenter;
import com.example.future.smarthome.View.DonationRequestCreateView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.RetrofitClient;
import com.example.future.smarthome.data.donation_request_create.DonationRequestCreate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationRequestCreateImpl implements DonationRequestCreatePresenter {
    DonationRequestCreateView donationRequestCreateView;
    private Context context;
    Call<DonationRequestCreate>requestCreateCall;

    public DonationRequestCreateImpl(DonationRequestCreateView donationRequestCreateView, Context context) {
        this.donationRequestCreateView = donationRequestCreateView;
        this.context = context;
    }

    @Override
    public void setDonationRequestData(String api_token, String patient_name, String patient_age, int blood_type_id, String bags_num, String hospital_name, String hospital_address, int city_id, String phone, String notes, Double latitude, Double longtitude) {
        if (TextUtils.isEmpty(patient_name) && TextUtils.isEmpty(patient_age)){
            donationRequestCreateView.onCanceled();
        }else if(TextUtils.isEmpty(bags_num) && TextUtils.isEmpty(hospital_name) && TextUtils.isEmpty(hospital_address)){
            donationRequestCreateView.onCanceled();
        }else if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(notes)){
            donationRequestCreateView.onCanceled();
        }else{
            createDonationCreate(patient_name,patient_age,blood_type_id,bags_num,hospital_name,hospital_address,city_id,phone,notes,latitude,longtitude);
        }
    }
    private void createDonationCreate(String patient_name, String patient_age, int blood_type_id, String bags_num, String hospital_name, String hospital_address, int city_id, String phone, String notes, Double latitude, Double longtitude) {
        requestCreateCall = RetrofitClient.getClient().create(Api.class).createDonationRequest("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",patient_name,patient_age,blood_type_id,bags_num,hospital_name,hospital_address,city_id,phone,notes,latitude,longtitude);
        Callback<DonationRequestCreate>callback = new CallBack();
        requestCreateCall.enqueue(callback);
    }
    private class CallBack implements Callback<DonationRequestCreate>{
        @Override
        public void onResponse(Call<DonationRequestCreate> call, Response<DonationRequestCreate> response) {
          if (response.body().getStatus() == 1){
              donationRequestCreateView.onSuccess(response.body().getMsg());
          }else {
              donationRequestCreateView.onCanceled();
          }
        }
        @Override
        public void onFailure(Call<DonationRequestCreate> call, Throwable t) {
            donationRequestCreateView.onFailure(t);
        }
    }
}
