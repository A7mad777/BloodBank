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
import com.example.future.smarthome.Model.Impl.NewPassWordImpl;
import com.example.future.smarthome.Model.presenter.NewPassWordPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.NewPassWordView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPassWord extends Fragment implements NewPassWordView, View.OnClickListener {
    NewPassWordPresenter newPassWordPresenter;
    private Context context;
    EditText passWordText;
    EditText passWord_confirm_text;
    EditText pin_code_text;
    EditText phoneText;
    Button change;
    SharedPreferences sharedPreferences;



    public NewPassWord() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_pass_word, container, false);
        pin_code_text = view.findViewById(R.id.editText7);
        change = view.findViewById(R.id.button6);
        change.setOnClickListener(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String pin_code = sharedPreferences.getString("pin_code",null);
        pin_code_text.setText(pin_code);
        return view;
    }

    @Override
    public void onSuccess(String msg) {
       // Toast.makeText(getActivity(), "تم تغيير كلمة المرور", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onClick(View v) {
        newPassWordPresenter = new NewPassWordImpl(this, context);
        passWordText = getActivity().findViewById(R.id.editText5);
        passWord_confirm_text = getActivity().findViewById(R.id.editText6);
        pin_code_text = getActivity().findViewById(R.id.editText7);
        phoneText = getActivity().findViewById(R.id.editText8);
        String passWord = passWordText.getText().toString();
        String passWord_confirm = passWord_confirm_text.getText().toString();
        String pin_code = pin_code_text.getText().toString();
        String phone = phoneText.getText().toString();
        newPassWordPresenter.initialNewPassWordData(passWord, passWord_confirm, pin_code, phone);
        LoginFragment loginFragment = new LoginFragment();
        LoginActivity activity = (LoginActivity) getActivity();
        activity.replaceFragment(loginFragment);
        Toast.makeText(getActivity(), "تم تغيير كلمة المرور", Toast.LENGTH_SHORT).show();
    }
}
