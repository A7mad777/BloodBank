package com.example.future.smarthome.Model.Impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.future.smarthome.Model.presenter.ReportPresenter;
import com.example.future.smarthome.View.ReportView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.Report.Report;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportImpl implements ReportPresenter {
    ReportView reportView;
    Context context;
    Call<Report>reportCall;

    public ReportImpl(ReportView reportView, Context context) {
        this.reportView = reportView;
        this.context = context;
    }

    @Override
    public void initialReportData(String api_token, String message) {
        if (TextUtils.isEmpty(message)){
            reportView.onError();
        }else {
            ReportData(api_token,message);
        }

    }

    private void ReportData(String api_token,String message) {
        reportCall = RetrofitClient.getClient().create(Api.class).sendReport(api_token, message);
        Callback<Report>callback = new CallBack();
        reportCall.enqueue(callback);
    }

    private class CallBack implements Callback<Report>{

        @Override
        public void onResponse(Call<Report> call, Response<Report> response) {
            if (response.body().getStatus() == 1){
                reportView.onSuccess();
            }
        }
        @Override
        public void onFailure(Call<Report> call, Throwable t) {
            reportView.onFailure(t);
        }
    }
}
