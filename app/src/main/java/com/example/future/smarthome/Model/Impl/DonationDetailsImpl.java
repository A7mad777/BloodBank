package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.DonationDetailsPresenter;
import com.example.future.smarthome.View.DonationDetailsView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.DonationDetails.DonationDetails;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationDetailsImpl implements DonationDetailsPresenter {
    DonationDetailsView donationDetailsView;
    private Context context;
    Call<DonationDetails> donationDetailsCall;

    public DonationDetailsImpl(DonationDetailsView donationDetailsView, Context context) {
        this.donationDetailsView = donationDetailsView;
        this.context = context;
    }

    @Override
    public void initialDonationDetails() {
        donationDetailsCall = RetrofitClient.getClient().create(Api.class).getDonationDetails("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",1);
        Callback<DonationDetails> callback = new CallBack();
        donationDetailsCall.enqueue(callback);
    }
    private class CallBack implements Callback<DonationDetails>{
        @Override
        public void onResponse(Call<DonationDetails> call, Response<DonationDetails> response) {
            donationDetailsView.onSuccess(response.body().getData());
        }

        @Override
        public void onFailure(Call<DonationDetails> call, Throwable t) {
            donationDetailsView.onFailure(t);

        }
    }
}
