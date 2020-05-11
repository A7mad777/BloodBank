package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.DonationPresenter;
import com.example.future.smarthome.View.DonationView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.Donation.Donation;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationImpl implements DonationPresenter {
    private Context context;
    DonationView donationView;
    Call<Donation> donationCall;

    public DonationImpl(DonationView donationView,Context context) {
        this.context = context;
        this.donationView = donationView;
    }

    @Override
    public void initialData() {
        donationCall = RetrofitClient.getClient().create(Api.class).getDonation("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",1);
        Callback<Donation> callback = new CallBack();
        donationCall.enqueue(callback);
    }
    private class CallBack implements Callback<Donation>{
        @Override
        public void onResponse(Call<Donation> call, Response<Donation> response) {
            donationView.getDonationList(response.body().getData().getData());
        }

        @Override
        public void onFailure(Call<Donation> call, Throwable t) {
            donationView.onFailure(t);

        }
    }
}
