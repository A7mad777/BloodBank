package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.BloodTypeImpl;
import com.example.future.smarthome.Model.Impl.CitiesImpl;
import com.example.future.smarthome.Model.Impl.DonationRequestCreateImpl;
import com.example.future.smarthome.Model.Impl.GovernorsImpl;
import com.example.future.smarthome.Model.presenter.BloodPresenter;
import com.example.future.smarthome.Model.presenter.CitiesPresenter;
import com.example.future.smarthome.Model.presenter.DonationRequestCreatePresenter;
import com.example.future.smarthome.Model.presenter.GovernorsPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.BloodTypeView;
import com.example.future.smarthome.View.CitiesView;
import com.example.future.smarthome.View.DonationRequestCreateView;
import com.example.future.smarthome.View.ListOfGovernorsView;
import com.example.future.smarthome.data.Api;
import com.example.future.smarthome.data.ListOfBlood.Bloods;
import com.example.future.smarthome.data.ListOfGovernorates.Governors;
import com.example.future.smarthome.data.ListOfGovernorates.ListOfGovernorates;
import com.example.future.smarthome.data.ListOofCities.Datum;
import com.example.future.smarthome.data.ListOofCities.ListOfCities;
import com.example.future.smarthome.data.RetrofitClient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Donation_request_create extends Fragment implements DonationRequestCreateView,BloodTypeView,ListOfGovernorsView,CitiesView, View.OnClickListener {
    DonationRequestCreatePresenter donationRequestCreatePresenter;
    private Context context;
    EditText nameText;
    EditText ageText;
    EditText bags_num_Text;
    Spinner blood_type;
    EditText notesText;
    EditText phoneText;
    EditText hospital_address_Text;
    EditText hospital_name_Text;
    Spinner governorSpinner;
    Spinner citiesSpinner;
    GovernorsPresenter governorsPresenter;
    BloodPresenter bloodPresenter;
    List<Bloods> bloodsList = new ArrayList<>();
    List<Datum> list = new ArrayList<>();
    ArrayAdapter<String> governorAdapter;
    ArrayAdapter<String> cityAdapter;
    ArrayAdapter<String> bloodAdapter;
    ImageButton location;
    List<Address> addresses;
    ImageButton locationButton;
    Geocoder geocoder;
    Call<ListOfCities> callCities;
    Call<ListOfGovernorates> governoratesCall;
    List<Governors> governors = new ArrayList<>();
    CitiesView citiesView;
    CitiesPresenter citiesPresenter;
    Button RequestButton;
    TextView Donation_create_Tv;
    Toolbar toolbar;

    public Donation_request_create() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_request_create, container, false);
        RequestButton = view.findViewById(R.id.RequestButton);
        toolbar = getActivity().findViewById(R.id.toolbar);
        Donation_create_Tv = toolbar.findViewById(R.id.tv);
        Donation_create_Tv.setText("طلب تبرع");
        governorsPresenter = new GovernorsImpl(this, context);
        governorsPresenter.setDataOfGovernors();
        bloodPresenter = new BloodTypeImpl(this, context);
        bloodPresenter.initialBloodData();
        location = view.findViewById(R.id.imageButton);
        location.setOnClickListener(this);
        RequestButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onCanceled() {
        Toast.makeText(getActivity(), "Some Fields are required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSpinner(final List<Integer> integerList) {
        final int[] Id = {-1};
        governorSpinner = getActivity().findViewById(R.id.spinner7);
        citiesPresenter = new CitiesImpl(this,context);
        governorSpinner.setAdapter(governorAdapter);
        governorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Id[0] = integerList.get(position - 1);
                    governorSpinner.getSelectedItem().toString();
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
        governorAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, stringList);
        governorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBloodAdapter(List<Bloods> bloodsList, List<String> stringList, List<Integer> integerList) {
        bloodAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, stringList);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void setBloodSpinner(final List<Integer> integerList) {
        blood_type = getActivity().findViewById(R.id.BloodKind);
        final int[] Id = {-1};
        blood_type.setAdapter(bloodAdapter);
        blood_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Id[0] = integerList.get(position - 1);
                    blood_type.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        Double latitude = 31.043273;
        Double longtitude = 31.364542;
        switch (v.getId()) {
            case R.id.imageButton:
                hospital_address_Text = getActivity().findViewById(R.id.Address);
                Geocoder geocoder = new Geocoder(getActivity());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longtitude, 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address returnedAddress = addresses.get(0);
                        String address = returnedAddress.getAddressLine(0);
                        hospital_address_Text.setText(address);
                    } else {
                        hospital_address_Text.setText("No Address returned!");
                    }
                } catch (IOException e) {

                }
                break;
            case R.id.RequestButton:
                donationRequestCreatePresenter = new DonationRequestCreateImpl(this, context);
                nameText = getActivity().findViewById(R.id.patientName);
                ageText = getActivity().findViewById(R.id.age);
                bags_num_Text = getActivity().findViewById(R.id.spinner3);
                hospital_name_Text = getActivity().findViewById(R.id.HospitalName);
                hospital_address_Text = getActivity().findViewById(R.id.Address);
                phoneText = getActivity().findViewById(R.id.Telephone);
                notesText = getActivity().findViewById(R.id.Notes);
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                int blood_type_id = 2;
                for (int i = 0; i < bloodsList.size(); i++) {
                    if (blood_type.getSelectedItem().toString().equals(bloodsList.get(i).getName()))
                        ;
                    blood_type_id = bloodsList.get(i).getId();
                }
                String bags_num = bags_num_Text.getText().toString();
                String hospital_name = hospital_name_Text.getText().toString();
                String hospital_address = hospital_address_Text.getText().toString();
                int city_id = 1;
                for (int i = 0; i < list.size(); i++) {
                    if (citiesSpinner.getSelectedItem().toString().equals(list.get(i).getName())) ;
                    city_id = list.get(i).getId();
                }
                String phone = phoneText.getText().toString();
                String notes = notesText.getText().toString();
                donationRequestCreatePresenter.setDonationRequestData("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", name, age, blood_type_id, bags_num, hospital_name, hospital_address, city_id, phone, notes, latitude, longtitude);
                break;
        }
    }

    @Override
    public void setAdapterCity( List<String> strings, List<Integer> integerList) {
        cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strings);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void setCitySpinner(final List<Integer> integerList) {
        citiesSpinner = getActivity().findViewById(R.id.spinner8);
        final int[] Id = {-1};
        citiesSpinner.setAdapter(cityAdapter);
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Id[0] = integerList.get(position - 1);
                    citiesSpinner.getSelectedItem().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

