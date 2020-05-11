package com.example.future.smarthome.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.DonationDetailsImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.DonationDetailsView;
import com.example.future.smarthome.data.DonationDetails.Data;
import com.example.future.smarthome.Model.presenter.DonationDetailsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DonationDetails extends FragmentActivity implements DonationDetailsView,OnMapReadyCallback {
    private Context context;
    DonationDetailsPresenter presenter;
    TextView Name;
    TextView Age;
    TextView textView16;
    TextView textView17;
    TextView textView18;
    TextView textView19;
    TextView textView20;
    TextView textView21;
    Button Call;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details);
        presenter = new DonationDetailsImpl(DonationDetails.this,context);
        presenter.initialDonationDetails();
        Name = (TextView) findViewById(R.id.nickName);
        Age = (TextView) findViewById(R.id.nickAge);
        textView16 = (TextView) findViewById(R.id.textView16);
        textView17 = (TextView) findViewById(R.id.textView17);
        textView18 = (TextView) findViewById(R.id.textView18);
        textView19 = (TextView) findViewById(R.id.textView19);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView21 = (TextView) findViewById(R.id.textView21);
        Call = (Button) findViewById(R.id.button500);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onSuccess(final Data donationDetails) {
        Name.setText(donationDetails.getClient().getName());
        Age.setText(donationDetails.getPatientAge());
        textView16.setText(donationDetails.getBloodType().getName());
        textView17.setText(donationDetails.getBagsNum());
        textView18.setText(donationDetails.getHospitalName());
        textView19.setText(donationDetails.getHospitalAddress());
        textView20.setText(donationDetails.getClient().getPhone());
        textView21.setText(donationDetails.getNotes());
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+donationDetails.getClient().getPhone())));
            }
        });
    }
    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(31.043273, 31.364542);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Mansoura"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f));
    }
}
