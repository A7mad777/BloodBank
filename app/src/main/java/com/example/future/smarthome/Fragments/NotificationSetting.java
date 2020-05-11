package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.future.smarthome.Model.Impl.MultiSelectionSpinner;
import com.example.future.smarthome.Model.Impl.NotificationSettingImpl;
import com.example.future.smarthome.Model.presenter.NotificationSettingPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.NotificationSettingView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSetting extends Fragment implements NotificationSettingView {
    ArrayAdapter<String> governorAdapter;
    ArrayAdapter<String> bloodAdapter;
    NotificationSettingPresenter notificationSettingPresenter;
    private Context context;
    List<String>governorList;
    List<String>bloodList;
    MultiSelectionSpinner governorsSpinner;
    MultiSelectionSpinner bloodSpinner;

    public NotificationSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_setting, container, false);
        governorsSpinner = view.findViewById(R.id.spinner4);
        bloodSpinner = view.findViewById(R.id.spinner5);
        notificationSettingPresenter = new NotificationSettingImpl(this,context);
        notificationSettingPresenter.initialNotificationSettingData("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",governorList,bloodList);
        return view;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void setAdapterGovenrors(List<String> governors) {
        governorAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,governors);
        governorsSpinner.setItems(governors);

    }

    @Override
    public void setSpinnerGovernors() {
        final int Id [] = {-1};
        final List<Integer>integerList = new ArrayList<>();
        governorsSpinner = getActivity().findViewById(R.id.spinner4);
        governorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (position != 0) {
                   Id[0] = integerList.get(position - 1);
                   governorsSpinner.getSelectedItem().toString();
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }

    @Override
    public void setAdapterBloodTypes(List<String> bloodTypes) {
        bloodAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bloodTypes);
        bloodSpinner.setItems(bloodTypes);
    }

    @Override
    public void setSpinnerBloodTypes(List<String> bloodTypes) {
        List<String>stringList = new ArrayList<>();
        final List<Integer>integers = new ArrayList<>();
        final int [] Id = {-1};
        bloodSpinner = getActivity().findViewById(R.id.spinner5);
        bloodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int blood_type = 2;
                if (position != 0) {
                    Id[0] = integers.get(position - 1);
                    bloodSpinner.getSelectedItem().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
