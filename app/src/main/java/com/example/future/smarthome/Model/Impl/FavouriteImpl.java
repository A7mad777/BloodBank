package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.FavouritePresenter;
import com.example.future.smarthome.View.FavouriteView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.FavouriteList.FavouriteList;
import com.example.future.smarthome.data.FavouriteList.Favourites;
import com.example.future.smarthome.data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteImpl implements FavouritePresenter {
    Call<FavouriteList>favouriteListCall;
    List<Favourites>favourites;
    FavouriteView favouriteView;
    Context context;

    public FavouriteImpl(FavouriteView favouriteView, Context context) {
        this.favouriteView = favouriteView;
        this.context = context;
    }

    @Override
    public void initialFavouriteData() {
        favouriteListCall = RetrofitClient.getClient().create(Api.class).getFavouriteList("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        Callback<FavouriteList> callback = new CallBack();
        favouriteListCall.enqueue(callback);
    }
    private class CallBack implements Callback<FavouriteList>{
        @Override
        public void onResponse(Call<FavouriteList> call, Response<FavouriteList> response) {
            favouriteView.onSuccess(response.body().getData().getData());
        }
        @Override
        public void onFailure(Call<FavouriteList> call, Throwable t) {
            favouriteView.onFailure(t);
        }
    }
}
