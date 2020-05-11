package com.example.future.smarthome.View;

import com.example.future.smarthome.data.FavouriteList.Favourites;

import java.util.List;

public interface FavouriteView {
    void onSuccess(List<Favourites>favourites);
    void onFailure(Throwable t);
}
