package com.example.future.smarthome.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.ContactUsImpl;
import com.example.future.smarthome.Model.Impl.SettingsImpl;
import com.example.future.smarthome.Model.presenter.ContactUsPresenter;
import com.example.future.smarthome.Model.presenter.SettingPresenter;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.ContactView;
import com.example.future.smarthome.View.SettingsView;
import com.example.future.smarthome.data.ContactUs.Data;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment implements SettingsView,ContactView,View.OnClickListener {
    Button ContactUs;
    EditText nameText;
    EditText phoneText;
    EditText messageText;
    EditText EmailText;
    EditText TitleText;
    ContactUsPresenter contactUsPresenter;
    SettingPresenter settingPresenter;
    SettingsView settingsView;
    private Context context;
    ImageView faceBookImage;
    ImageView whatsAppImage;
    ImageView youTube;
    ImageView googleImage;
    ImageView twitterImage;
    TextView phone;
    public Data newData;
    SettingPresenter presenter;
    com.example.future.smarthome.data.Settings.Data data;
    TextView email;
    Toolbar toolbar;
    TextView contact_Tv;

    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        contact_Tv = toolbar.findViewById(R.id.tv);
        contact_Tv.setText("تـواصل معنـا");
        ContactUs = view.findViewById(R.id.Send);
        faceBookImage = view.findViewById(R.id.imageView12);
        faceBookImage.setOnClickListener(this);
        presenter = new SettingsImpl(this,context);
        presenter.initialSettingPresenter();
        return view;
    }

    @Override
    public void onSuccess(Data contacts) {
        Toast.makeText(getActivity(), "تم إرسال الطلب", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), "some fields are required" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(final com.example.future.smarthome.data.Settings.Data data) {
        phone = getView().findViewById(R.id.Tv6);
        email = getActivity().findViewById(R.id.textView24);
       phone.setText(data.getPhone());
        email.setText(data.getEmail());
        faceBookImage = getActivity().findViewById(R.id.imageView12);
        whatsAppImage = getActivity().findViewById(R.id.imageView14);
        youTube = getActivity().findViewById(R.id.imageView17);
        googleImage = getActivity().findViewById(R.id.imageView15);
        twitterImage = getActivity().findViewById(R.id.imageView13);
        final String faceBook = data.getFacebookUrl();
        final String youtube = data.getYoutubeUrl();
        final String google = data.getGoogleUrl();
        final String twitter = data.getTwitterUrl();
        faceBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(faceBook)));
        }
    });
        whatsAppImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+2"+ data.getWhatsapp() +"&text="+text));
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(youtube)));
            }
        });
        googleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(google)));
            }
        });
        twitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(twitter)));
            }
        });

    }
    public void createContact() {
      contactUsPresenter = new ContactUsImpl(this, context);
      nameText = getActivity().findViewById(R.id.editText24);
      phoneText = getActivity().findViewById(R.id.editText30);
      messageText = getActivity().findViewById(R.id.editText32);
      EmailText = getActivity().findViewById(R.id.editText29);
      TitleText = getActivity().findViewById(R.id.editText31);
      String name = nameText.getText().toString();
      String phone = phoneText.getText().toString();
      String message = messageText.getText().toString();
      String email = EmailText.getText().toString();
      String title = TitleText.getText().toString();
          contactUsPresenter.initialContactsData(name, phone, message, email, title);
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Send:
                createContact();
                break;
        }
    }
}
