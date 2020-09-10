package com.adeeva.movieworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.adeeva.movieworld.AboutActivity;
import com.adeeva.movieworld.R;
import com.adeeva.movieworld.fragment.MovieFragment;
import com.adeeva.movieworld.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;

                case R.id.navigation_tv:
                    fragment = new TvShowFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_change_settings:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;

            case R.id.about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
