package com.example.future.smarthome.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Activities.PostsDetails;
import com.example.future.smarthome.R;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.RetrofitClient;
import com.example.future.smarthome.data.postToggleFavourite.Data;
import com.example.future.smarthome.data.postToggleFavourite.PostToggleFavourite;
import com.example.future.smarthome.data.posts.PostsList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    Context context;
    List<PostsList> postsLists;
    Call<PostToggleFavourite> favouriteCall;
    List<Data> favouriteChecked;
    Data postToggleFavourite;
    PostToggleFavourite ss;

    public PostsAdapter(Context context, List<PostsList> postsLists) {
        this.context = context;
        this.postsLists = postsLists;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.posts_row, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsViewHolder holder, final int position) {
        PostsList posts = postsLists.get(position);
        holder.Title.setText(posts.getTitle());
        Picasso.get().load(posts.getThumbnailFullPath()).into(holder.post);
        if (posts.getIsFavourite()){
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               PostsList poster = postsLists.get(position);
                Log.i("is checked","is checked");
                if (holder.checkBox.isChecked()){
                    Boolean check = addToFavourite(poster.getId());
                    if (check){
                        Log.i("isCheked","start");
                        poster.setIsFavourite(true);
                        notifyDataSetChanged();
                    }
                }else{
                    poster.setIsFavourite(false);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return postsLists.size();
    }
    public Boolean addToFavourite(int id) {
        final boolean [] isChecked = {false};
        favouriteCall = RetrofitClient.getClient().create(Api.class).checkPost(id,"Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        favouriteCall.enqueue(new Callback<PostToggleFavourite>() {
            @Override
            public void onResponse(retrofit2.Call<PostToggleFavourite> call, Response<PostToggleFavourite> response) {
                PostToggleFavourite user = response.body();
                if (user.getStatus() == 1) {
                    isChecked[0] = true;
                } else {
                    isChecked[0] = false;
                }
            }
            @Override
            public void onFailure(retrofit2.Call<PostToggleFavourite> call, Throwable t) {
                Log.i("isChecked", "error  " + t.getMessage());
                isChecked[0] = false;
            }
        });
        return isChecked[0];
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        int id;
        ImageView post;
        CardView cardView;
        CheckBox checkBox;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            int id = 2;
            Title = itemView.findViewById(R.id.textView5);
            post = itemView.findViewById(R.id.imageView2);
            checkBox = itemView.findViewById(R.id.checkBox2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, PostsDetails.class);
                    context.startActivity(i);
                }
            });

        }
    }
}
