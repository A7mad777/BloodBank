package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Activities.HomeActivity;
import com.example.future.smarthome.Activities.LoginActivity;
import com.example.future.smarthome.Activities.MapsActivity;
import com.example.future.smarthome.Model.Impl.LoginImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.LoginView;
import com.example.future.smarthome.data.Login.Login;
import com.example.future.smarthome.Model.presenter.LoginPresenter;

import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginView,View.OnClickListener {
    private LoginPresenter loginPresenter;
    private Context context;
    Button Login;
    LoginImpl loginImpl;
    EditText phoneText;
    EditText passWordText;
    TextView resetPassword;
    Button Register;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Login = view.findViewById(R.id.button1);
        Register = view.findViewById(R.id.btn2);
        resetPassword = view.findViewById(R.id.reset);
        Login.setOnClickListener(this);
        Register.setOnClickListener(this);
        resetPassword.setOnClickListener(this);
        return view;
    }

    @Override
    public void onSuccess() {
            startActivity(new Intent(getActivity(), HomeActivity.class));
        Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel() {
        Toast.makeText(getActivity(), "Login Canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Response<Login>response) {
    Toast.makeText(getActivity(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
}


    @Override
    public void onComplete() {
        Toast.makeText(getActivity(), "Some Fields Are required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        loginPresenter = new LoginImpl(this,context);
        switch (v.getId()){
            case R.id.button1:
                phoneText = getActivity().findViewById(R.id.editText2);
                passWordText = getActivity().findViewById(R.id.editText3);
                String phone = phoneText.getText().toString();
                String passWord = passWordText.getText().toString();
                loginPresenter.setLogin(phone, passWord);
                break;
            case R.id.btn2:
                RegisterFragment fragment = new RegisterFragment();
                LoginActivity activity = (LoginActivity) getActivity();
                activity.replaceFragment(fragment);
                break;
            case R.id.reset:
                ResetPassWord resetPassWord = new ResetPassWord();
                LoginActivity activity1 = (LoginActivity) getActivity();
                activity1.replaceFragment(resetPassWord);
                break;
        }
    }
}
