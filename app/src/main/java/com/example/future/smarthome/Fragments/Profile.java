package com.example.future.smarthome.Fragments;


import android.content.Context;
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

import com.example.future.smarthome.Model.Impl.BloodTypeImpl;
import com.example.future.smarthome.Model.Impl.CitiesImpl;
import com.example.future.smarthome.Model.Impl.GovernorsImpl;
import com.example.future.smarthome.Model.Impl.ProfileImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.BloodTypeView;
import com.example.future.smarthome.View.CitiesView;
import com.example.future.smarthome.View.ListOfGovernorsView;
import com.example.future.smarthome.View.ProfileView;
import com.example.future.smarthome.data.ListOfBlood.Bloods;
import com.example.future.smarthome.data.ListOfGovernorates.Governors;
import com.example.future.smarthome.data.ListOofCities.Datum;
import com.example.future.smarthome.Model.presenter.BloodPresenter;
import com.example.future.smarthome.Model.presenter.CitiesPresenter;
import com.example.future.smarthome.Model.presenter.GovernorsPresenter;
import com.example.future.smarthome.Model.presenter.ProfilePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment implements ProfileView,ListOfGovernorsView,CitiesView,BloodTypeView,View.OnClickListener {
    Button Modify;
    EditText nameText;
    EditText emailText;
    EditText birth_date_Text;
    Spinner city_id;
    EditText phoneText;
    EditText donation_last_date_Text;
    EditText passWordText;
    EditText passWord_Confirmation_Tex;
    Spinner blood_type;
    Spinner GovernorsSpinner;
    Spinner spinner ;
    CitiesImpl citiesImp;
    GovernorsPresenter presenter;
    private Context context;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> cityAdapter;
    List<Governors>governorates = new ArrayList<>();
    CitiesPresenter citiesPresenter;
    List<Datum>list = new ArrayList<>();
    ProfilePresenter profilePresenter;
    ArrayAdapter<String> bloodAdapter;
    Spinner bloodSpinner;
    BloodPresenter bloodPresenter;
    List<Bloods>bloodsList = new ArrayList<>();




    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        int id = 1;
        Modify = view.findViewById(R.id.button2);
        presenter = new GovernorsImpl(this, context);
        presenter.setDataOfGovernors();
        bloodPresenter = new BloodTypeImpl(this,context);
        bloodPresenter.initialBloodData();
        Modify.setOnClickListener(this);
        return view;
    }
    @Override
    public void onSuccess(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSpinner(final List<Integer> integers) {
        citiesPresenter = new CitiesImpl(this,context);
        final int[] Id = {-1};
        GovernorsSpinner = getActivity().findViewById(R.id.spinner3);
        GovernorsSpinner.setAdapter(arrayAdapter);
        GovernorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Id[0] = integers.get(position - 1);
                    GovernorsSpinner.getSelectedItem().toString();
                    citiesPresenter.setCitiesData(integers.get(position));
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void setAdapter( List<String> stringList, List<Integer> integerList) {
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCanceled() {
        Toast.makeText(getActivity(), "some Fields Are required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPassField() {
        Toast.makeText(getActivity(), "passWord Are Mismatched", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapterCity( List<String> strings, List<Integer> integerList) {
        cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strings);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void setCitySpinner( final List<Integer> integerList) {
        final int[] Id = {-1};
        spinner = getActivity().findViewById(R.id.spinner9);
        spinner.setAdapter(cityAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Id[0] = integerList.get(position - 1);
                    spinner.getSelectedItem().toString();
                }
            }
     @Override
     public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void setBloodAdapter(List<Bloods> bloodsList, List<String> stringList, List<Integer> integerList) {
        bloodAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,stringList);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public void setBloodSpinner(final List<Integer> integerList) {
        final int [] Id = {-1};
        bloodSpinner = getActivity().findViewById(R.id.spinner10);
        bloodSpinner.setAdapter(bloodAdapter);
        bloodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        Id[0] = integerList.get(position - 1);
                        bloodSpinner.getSelectedItem().toString();
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public void onClick(View v) {
       profilePresenter = new ProfileImpl(this,context);
       nameText = getActivity().findViewById(R.id.editText19);
       emailText = getActivity().findViewById(R.id.editText20);
       birth_date_Text = getActivity().findViewById(R.id.editText21);
        bloodSpinner = getActivity().findViewById(R.id.spinner10);
        int city_id = 1;
        phoneText = getActivity().findViewById(R.id.editText25);
        passWordText = getActivity().findViewById(R.id.editText26);
        passWord_Confirmation_Tex = getActivity().findViewById(R.id.editText);
        donation_last_date_Text = getActivity().findViewById(R.id.editText28);
        String name = nameText.getText().toString();
        String mail = emailText.getText().toString();
        String birth_date = birth_date_Text.getText().toString();
        int city = 1;
        for (int i = 0; i < list.size(); i++) {
            if (spinner.getSelectedItem().toString().equals(list.get(i).getName()))
                ;
            city = list.get(i).getId();
        }
        String phone = phoneText.getText().toString();
        String passWord = passWordText.getText().toString();
        String passWord_confirm = passWord_Confirmation_Tex.getText().toString();
        int blood_type = 2;
        for (int i = 0; i < bloodsList.size(); i++) {
            if (bloodSpinner.getSelectedItem().toString().equals(bloodsList.get(i).getName()))
                ;
            blood_type = list.get(i).getId();
        }
        String donation_last_date = donation_last_date_Text.getText().toString();
        profilePresenter.initialProfileData(name,mail,birth_date,city,phone,donation_last_date,passWord,passWord_confirm,blood_type,"W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl");
    }
}
