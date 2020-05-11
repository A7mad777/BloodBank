package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.FavouriteImpl;
import com.example.future.smarthome.Model.presenter.FavouritePresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.FavouriteView;
import com.example.future.smarthome.adapters.FavouritesAdapter;
import com.example.future.smarthome.data.FavouriteList.Favourites;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteList extends Fragment implements FavouriteView {
    RecyclerView recyclerView;
    FavouritePresenter favouritePresenter;
    FavouriteView favouriteView;
    private Context context;
    Toolbar toolbar;
    TextView textView_Favourite;

    public FavouriteList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourite_list, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        textView_Favourite = toolbar.findViewById(R.id.tv);
        textView_Favourite.setText("المفـضـلـه");
        favouritePresenter = new FavouriteImpl(this,context);
        favouritePresenter.initialFavouriteData();
        return view;
    }
    @Override
    public void onSuccess(List<Favourites> favourites) {
        recyclerView = getActivity().findViewById(R.id.Rvv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        FavouritesAdapter adapter = new FavouritesAdapter(getActivity(),favourites);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
