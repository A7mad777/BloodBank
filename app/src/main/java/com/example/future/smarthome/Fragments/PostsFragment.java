package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.future.smarthome.Model.Impl.BloodTypeImpl;
import com.example.future.smarthome.Model.Impl.PostsImpl;
import com.example.future.smarthome.Model.presenter.BloodPresenter;
import com.example.future.smarthome.Model.presenter.CitiesPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.BloodTypeView;
import com.example.future.smarthome.View.PostsView;
import com.example.future.smarthome.adapters.PostsAdapter;
import com.example.future.smarthome.data.ListOfBlood.Bloods;
import com.example.future.smarthome.data.posts.PostsList;
import com.example.future.smarthome.Model.presenter.PostsPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment implements PostsView,BloodTypeView {
    List<PostsList> postsLists;
    RecyclerView recyclerView;
    PostsAdapter adapter;
    private Context context;
    PostsView postsView;
    PostsPresenter presenter;
    BloodPresenter bloodPresenter;
    ArrayAdapter<String>arrayAdapter;
    Spinner Blood_type_Spinner;
    List<Bloods>bloodsList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        presenter = new PostsImpl(this,context);
        presenter.setDataToRecyclerView();
        bloodPresenter = new BloodTypeImpl(this,context);
        bloodPresenter.initialBloodData();
        onResume();
        return view;
    }

    @Override
    public void getPostsArrayList(List<PostsList> postsLists) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView = getActivity().findViewById(R.id.Rv);
        recyclerView.setLayoutManager(manager);
        PostsAdapter adapter = new PostsAdapter(getActivity(),postsLists);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setBloodAdapter(List<Bloods> bloodsList, List<String> stringList, List<Integer> integerList) {
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void setBloodSpinner(final List<Integer> integerList) {
        final int [] Id = {-1};
        Blood_type_Spinner = getActivity().findViewById(R.id.spinner6);
        Blood_type_Spinner.setAdapter(arrayAdapter);
        Blood_type_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int blood_type = 2;
                if (position != 0) {
                    Id[0] = integerList.get(position - 1);
                    Blood_type_Spinner.getSelectedItem().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
