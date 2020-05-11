package com.example.future.smarthome.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.future.smarthome.Fragments.ContactUs;
import com.example.future.smarthome.Fragments.Donation_request_create;
import com.example.future.smarthome.Fragments.Favourite;
import com.example.future.smarthome.Fragments.FavouriteList;
import com.example.future.smarthome.Fragments.Home;
import com.example.future.smarthome.Fragments.Notification;
import com.example.future.smarthome.Fragments.NotificationSetting;
import com.example.future.smarthome.Fragments.Profile;
import com.example.future.smarthome.Fragments.Report;
import com.example.future.smarthome.Model.Impl.NotificationCountImpl;
import com.example.future.smarthome.R;
import com.example.future.smarthome.View.NotificationView;
import com.example.future.smarthome.adapters.PostsAdapter;
import com.example.future.smarthome.data.NotificationCount.NotificationCount;
import com.example.future.smarthome.data.posts.PostsList;
import com.example.future.smarthome.Model.presenter.NotificationPresenter;
import com.example.future.smarthome.Model.presenter.PostsPresenter;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.List;

import retrofit2.Call;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,NotificationView {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PostsAdapter postsAdapter;
    List<PostsList> postsLists;
    PostsPresenter postsPresenter;
    private Context context;
    HomePager homePager;
    TabLayout tabLayout;
    ViewPager pager;
    NotificationBadge mbadge;
    ImageView notifyImage;
    int count = 0;
    NotificationView notificationView;
    NotificationPresenter notificationPresenter;
    Call<NotificationCount>notificationCountCall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mbadge = (NotificationBadge) findViewById(R.id.badge);
        notifyImage = findViewById(R.id.imageView20);
        Home home = new Home();
        replaceFragment(home);
        notificationPresenter = new NotificationCountImpl(HomeActivity.this,context);
        notificationPresenter.initailNotificationData("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl");

        notifyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                mbadge.setNumber(count);
                replaceFragment(new Notification());
                Toast.makeText(HomeActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Donation_request_create donation_request_create = new Donation_request_create();
                replaceFragment(donation_request_create);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerHome,fragment).commit();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.information) {
            Profile profile = new Profile();
            replaceFragment(profile);
        } else if (id == R.id.setting) {
            NotificationSetting notificationSetting = new NotificationSetting();
            replaceFragment(notificationSetting);
        } else if (id == R.id.favourite) {
            FavouriteList favouriteList = new FavouriteList();
            replaceFragment(favouriteList);

        } else if (id == R.id.home) {
            Home home = new Home();
           replaceFragment(home);

        } else if (id == R.id.instruction) {

        } else if (id == R.id.report) {
            Report report = new Report();
            replaceFragment(report);
        } else if (id == R.id.ContactUs) {
            ContactUs contactUs = new ContactUs();
            replaceFragment(contactUs);

        } else if (id == R.id.about) {

        } else if (id == R.id.review) {

        } else if (id == R.id.signOut) {
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onSuccess(int notificationCount) {
        mbadge.setNumber(notificationCount);
    }
    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(HomeActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
