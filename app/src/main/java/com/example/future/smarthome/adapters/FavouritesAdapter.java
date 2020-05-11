package com.example.future.smarthome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.future.smarthome.R;
import com.example.future.smarthome.data.FavouriteList.Favourites;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {
    private Context context;
    List<Favourites>favouritesList;

    public FavouritesAdapter(Context context, List<Favourites> favourites) {
        this.context = context;
        favouritesList = favourites;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_row,parent,false);
        return new FavouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
        Favourites data = favouritesList.get(position);
        holder.textView.setText(data.getTitle());
        Picasso.get().load(data.getThumbnailFullPath()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img;
        TextView textView;
        CheckBox checkBox;
        public FavouritesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView5);
            img = itemView.findViewById(R.id.imageView2);
        }
    }
}
