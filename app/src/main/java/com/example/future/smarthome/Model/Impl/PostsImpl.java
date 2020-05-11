package com.example.future.smarthome.Model.Impl;

import android.content.Context;

import com.example.future.smarthome.Model.presenter.PostsPresenter;
import com.example.future.smarthome.View.PostsView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.RetrofitClient;
import com.example.future.smarthome.data.posts.Posts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsImpl implements PostsPresenter {
    private Context context;
    Call<Posts>postsCall;
    PostsPresenter presenter;
    PostsView postsView;

    public PostsImpl(PostsView postsView, Context context) {
        this.context = context;
        this.postsView = postsView;
    }

    @Override
    public void setDataToRecyclerView() {
        postsCall = RetrofitClient.getClient().create(Api.class).getPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",1);
        Callback<Posts> callback = new CallBack();
        postsCall.enqueue(callback);
    }
    private class CallBack implements Callback<Posts>{

        @Override
        public void onResponse(Call<Posts> call, Response<Posts> response) {
            postsView.getPostsArrayList(response.body().getData().getData());
        }

        @Override
        public void onFailure(Call<Posts> call, Throwable t) {


        }
    }
}
