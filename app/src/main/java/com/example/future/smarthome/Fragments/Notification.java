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

import com.example.future.smarthome.Model.Impl.NotificationListImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.NotificationListView;
import com.example.future.smarthome.adapters.NotificationListAdapter;
import com.example.future.smarthome.data.NotificationList.Datum;
import com.example.future.smarthome.Model.presenter.NotificationListPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment implements NotificationListView {
    RecyclerView recyclerView;
    NotificationListPresenter presenter;
    private Context context;
    Toolbar toolbar;
    TextView notify_Tv;
    public Notification() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_notification, container, false);
       toolbar = getActivity().findViewById(R.id.toolbar);
       notify_Tv = toolbar.findViewById(R.id.tv);
       notify_Tv.setText("التنبيهات");
       presenter = new NotificationListImpl(this,context);
       presenter.initialNotificationListData();
       return view;
    }

    @Override
    public void onSuccess(List<Datum> notificationLists) {
        recyclerView = getActivity().findViewById(R.id.Rv1);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        NotificationListAdapter adapter = new NotificationListAdapter(notificationLists,getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
    }
}
