package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.ReportImpl;
import com.example.future.smarthome.Model.presenter.ReportPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.ReportView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Report extends Fragment implements ReportView, View.OnClickListener {
    private Context context;
    ReportPresenter reportPresenter;
    Button send;
    EditText messageText;
    Toolbar toolbar;
    TextView report_Tv;
    public Report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_report, container, false);
       send = view.findViewById(R.id.button4);
       send.setOnClickListener(this);
       toolbar = getActivity().findViewById(R.id.toolbar);
       report_Tv = toolbar.findViewById(R.id.tv);
       report_Tv.setText("إبـلاغ");
       return view;
    }
    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(), "تم الإبلاغ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "أدخل نص الرساله", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        reportPresenter = new ReportImpl(this,context);
        messageText = getActivity().findViewById(R.id.editText23);
        String message = messageText.getText().toString();
        reportPresenter.initialReportData("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",message);
    }
}
