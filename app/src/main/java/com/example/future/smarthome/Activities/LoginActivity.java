package com.example.future.smarthome.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.future.smarthome.Fragments.LoginFragment;
import com.example.future.smarthome.Fragments.Moon;
import com.example.future.smarthome.R;
import com.example.future.smarthome.Model.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    Button Register;
    LoginPresenter loginPresenter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,new LoginFragment()).commit();
    }
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,fragment).commit();
    }

}
