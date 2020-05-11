package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.future.smarthome.Activities.LoginActivity;
import com.example.future.smarthome.Model.Impl.ResetPassWordImpl;
import com.example.future.smarthome.Model.presenter.ResetPassWordPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.ResetPassWordView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassWord extends Fragment implements ResetPassWordView, View.OnClickListener {
    SharedPreferences preferences;
    ResetPassWordPresenter resetPassWordPresenter;
    private Context context;
    EditText phoneText;
    Button send;
    public ResetPassWord() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_reset_pass_word, container, false);
       send = view.findViewById(R.id.button3);
       send.setOnClickListener(this);
       return view;
    }

    @Override
    public void onSuccess(String msg, String pincode) {
        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preferences.edit().putString("pin_code",pincode.toString()).commit();
        NewPassWord newPassWord = new NewPassWord();
        LoginActivity activity = (LoginActivity) getActivity();
        activity.replaceFragment(newPassWord);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete(String msg) {
        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        phoneText = getActivity().findViewById(R.id.editText4);
        resetPassWordPresenter = new ResetPassWordImpl(this,context);
        String phone = phoneText.getText().toString();
        resetPassWordPresenter.initialResetData(phone);
    }
}
