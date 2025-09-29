package vn.edu.usth.moodleapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.usth.moodleapp.Courses.CoursesCategoriesFragment;
import vn.edu.usth.moodleapp.NavBottom.BlogsFragment;
import vn.edu.usth.moodleapp.NavBottom.CalendarFragment;
import vn.edu.usth.moodleapp.NavBottom.MoreFragment;
import vn.edu.usth.moodleapp.NavBottom.NotificationFragment;
import vn.edu.usth.moodleapp.NavBottom.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Bắt sự kiện chọn item trong BottomNavigation
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

        // Fragment mặc định khi vào app
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CoursesCategoriesFragment()) // 👈 có thể đổi thành HomeFragment
                    .commit();
        }
    }
}
