package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.DonationImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.DonationView;
import com.example.future.smarthome.adapters.DonationAdapter;
import com.example.future.smarthome.data.Donation.DonationList;
import com.example.future.smarthome.Model.presenter.DonationPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class donation_request_Fragment extends Fragment implements DonationView {
    RecyclerView recyclerView;
    DonationPresenter presenter;
    private Context context;

    public donation_request_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_, container, false);
        presenter = new DonationImpl(this,context);
        presenter.initialData();
        onResume();
        return view;
    }

    @Override
    public void getDonationList(List<DonationList> donationLists) {
        recyclerView = getActivity().findViewById(R.id.Rv2);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        DonationAdapter adapter = new DonationAdapter(getActivity(),donationLists);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
