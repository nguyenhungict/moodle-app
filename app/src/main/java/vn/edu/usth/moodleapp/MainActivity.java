package vn.edu.usth.moodleapp;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.usth.moodleapp.adapter.HomePagerAdapter;

public class MainActivity extends AppCompatActivity {


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.usth.moodleapp.NavBottom.BlogsFragment;
import vn.edu.usth.moodleapp.NavBottom.CalendarFragment;
import vn.edu.usth.moodleapp.NavBottom.MoreFragment;
import vn.edu.usth.moodleapp.NavBottom.NotificationFragment;
import vn.edu.usth.moodleapp.NavBottom.HomeFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("USTH Moodle");
        }

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        HomePagerAdapter adapter = new HomePagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) tab.setText("System home page");
            else tab.setText("Control panel");
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.bottom_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.notification) {
                    selectedFragment = new NotificationFragment();
                } else if (itemId == R.id.calendar) {
                    selectedFragment = new CalendarFragment();
                } else if (itemId == R.id.blog) {
                    selectedFragment = new BlogsFragment();
                } else if (itemId == R.id.more) {
                    selectedFragment = new MoreFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }

                return true;
            }
        });

        // mặc định mở fragment Home khi vào app
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
