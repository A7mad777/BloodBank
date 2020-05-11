package com.example.future.smarthome.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.future.smarthome.Model.Impl.PostsDetailsImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.PostsDetailsView;
import com.example.future.smarthome.data.PostsDetails.Data;
import com.example.future.smarthome.Model.presenter.PostsDetailsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class PostsDetails extends AppCompatActivity implements PostsDetailsView{
    ImageView imageView;
    TextView textView;
    TextView textView2;
    private GoogleMap map;
    PostsDetailsPresenter presenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_details);
        imageView = (ImageView) findViewById(R.id.imageView26);
        textView = (TextView) findViewById(R.id.textView13);
        textView2 = (TextView) findViewById(R.id.textView14);
        presenter = new PostsDetailsImpl(PostsDetails.this,context);
        presenter.initialDetails();

    }
    @Override
    public void onSuccess(Data details) {
        textView.setText(details.getTitle());
        Picasso.get().load(details.getThumbnailFullPath()).into(imageView);
    }
    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(PostsDetails.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
