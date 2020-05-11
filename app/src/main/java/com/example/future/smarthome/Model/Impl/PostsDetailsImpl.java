package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.PostsDetailsPresenter;
import com.example.future.smarthome.View.PostsDetailsView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsDetailsImpl implements PostsDetailsPresenter {
    private PostsDetailsView postsDetailsView;
    private Context context;
    Call<com.example.future.smarthome.data.PostsDetails.PostsDetails>postsDetailsCall;

    public PostsDetailsImpl(PostsDetailsView postsDetailsView, Context context) {
        this.postsDetailsView = postsDetailsView;
        this.context = context;
    }

    @Override
    public void initialDetails() {
        postsDetailsCall = RetrofitClient.getClient().create(Api.class).getPostsDetails("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",1,1);
        Callback<com.example.future.smarthome.data.PostsDetails.PostsDetails> callback = new CallBack();
        postsDetailsCall.enqueue(callback);

    }
    private class CallBack implements Callback<com.example.future.smarthome.data.PostsDetails.PostsDetails>{

        @Override
        public void onResponse(Call<com.example.future.smarthome.data.PostsDetails.PostsDetails> call, Response<com.example.future.smarthome.data.PostsDetails.PostsDetails> response) {
         postsDetailsView.onSuccess(response.body().getData());
        }

        @Override
        public void onFailure(Call<com.example.future.smarthome.data.PostsDetails.PostsDetails> call, Throwable t) {
            postsDetailsView.onFailure(t);

        }
    }
}
