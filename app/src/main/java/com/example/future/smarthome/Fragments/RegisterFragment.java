package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.future.smarthome.Activities.HomeActivity;
import com.example.future.smarthome.Model.Impl.CitiesImpl;
import com.example.future.smarthome.Model.Impl.GovernorsImpl;
import com.example.future.smarthome.Model.Impl.RegisterImpl;
import com.example.future.smarthome.Model.presenter.CitiesPresenter;
import com.example.future.smarthome.Model.presenter.GovernorsPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.CitiesView;
import com.example.future.smarthome.View.ListOfGovernorsView;
import com.example.future.smarthome.View.LoginView;
import com.example.future.smarthome.View.RegisterView;
import com.example.future.smarthome.View.ReportView;
import com.example.future.smarthome.data.Login.Login;

import com.example.future.smarthome.Model.presenter.RegistrerPresenter;
import com.example.future.smarthome.data.Register.Register;

import java.util.List;

import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements RegisterView,View.OnClickListener,ListOfGovernorsView,CitiesView {
    RegistrerPresenter registrerPresenter;
    private Context context;
    EditText nameText;
    EditText MailText;
    EditText donation_last_date_Text;
    EditText blood_type_Text;
    EditText phone_Text;
    EditText passWord_Text;
    EditText passWord_Confirm_Text;
    EditText birth_date_Text;
    Button SignUp;
    Spinner governerSpinner;
    Spinner citySpinner;
    GovernorsPresenter governorsPresenter;
    ArrayAdapter<String> governorAdapter;
    ArrayAdapter<String> citiesAdapter;
    CitiesPresenter citiesPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_register, container, false);
         SignUp = view.findViewById(R.id.SignUp);
         SignUp.setOnClickListener(this);
         governorsPresenter = new GovernorsImpl(this,context);
         governorsPresenter.setDataOfGovernors();
         return view;
    }
    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(), "تم التسجيل", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(getActivity(), "أدخل البيانات صحيحه", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Response<Register> response) {
        Toast.makeText(getActivity(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSpinner(final List<Integer> integerList) {
        citiesPresenter = new CitiesImpl(this,context);
        final int [] Id = {-1};
        governerSpinner = getActivity().findViewById(R.id.spinner);
        governerSpinner.setAdapter(governorAdapter);
        governerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    Id[0] = integerList.get(position-1);
                    governerSpinner.getSelectedItem().toString();
                    citiesPresenter.setCitiesData(integerList.get(position));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void setAdapter(List<String> stringList, List<Integer> integerList) {
        governorAdapter = new ArrayAdapter<String >(getActivity(),android.R.layout.simple_spinner_dropdown_item,stringList);
        governorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        registrerPresenter = new RegisterImpl(this,context);
        nameText = getActivity().findViewById(R.id.name);
        MailText = getActivity().findViewById(R.id.email);
        birth_date_Text = getActivity().findViewById(R.id.birthDate);
        blood_type_Text = getActivity().findViewById(R.id.BloodDonation);
        phone_Text = getActivity().findViewById(R.id.tel);
        passWord_Text = getActivity().findViewById(R.id.passWord1);
        passWord_Confirm_Text = getActivity().findViewById(R.id.CPassWord);
        donation_last_date_Text = getActivity().findViewById(R.id.LastDate);
        String name = nameText.getText().toString();
        String mail = MailText.getText().toString();
        String donation_last_date = donation_last_date_Text.getText().toString();
        String blood_type = blood_type_Text.getText().toString();
        String phone = phone_Text.getText().toString();
        String passWord = passWord_Text.getText().toString();
        String passWord_confirmation = passWord_Confirm_Text.getText().toString();
        String birthDate = birth_date_Text.getText().toString();
        int city_id = 1;
        int blood_type_id = 1;
        registrerPresenter.setRegister(name, mail, birthDate, getId(), phone, donation_last_date, passWord, passWord_confirmation, blood_type_id);
    }

    @Override
    public void setAdapterCity(List<String> strings, List<Integer> integerList) {
        citiesAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,strings);
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void setCitySpinner(final List<Integer> integerList) {
        final int [] Id = {-1};
        citySpinner = getActivity().findViewById(R.id.spinner2);
        citySpinner.setAdapter(citiesAdapter);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    citySpinner.getSelectedItem().toString();
                    Id[0] = integerList.get(position-1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
