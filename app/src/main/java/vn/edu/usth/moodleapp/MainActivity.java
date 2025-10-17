package vn.edu.usth.moodleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.usth.moodleapp.Courses.CoursesCategoriesFragment;
import vn.edu.usth.moodleapp.NavBottom.BlogsFragment;
import vn.edu.usth.moodleapp.NavBottom.CalendarFragment;
import vn.edu.usth.moodleapp.NavBottom.MoreFragment;
import vn.edu.usth.moodleapp.NavBottom.NotificationFragment;
import vn.edu.usth.moodleapp.Nav_fragments.BadgesFragment;
import vn.edu.usth.moodleapp.Nav_fragments.FilesFragment;
import vn.edu.usth.moodleapp.Nav_fragments.GradesFragment;
import vn.edu.usth.moodleapp.Nav_fragments.PreferenceFragment;
import vn.edu.usth.moodleapp.adapter.HomePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navBar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("USTH Moodle");
        }

//        drawerLayout = findViewById(R.id.main);
//        navBar = findViewById(R.id.nav_bar);
//        toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//        );
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navBar.setNavigationItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//            int id = item.getItemId();
//
//            if (id == R.id.nav_grades) {
//                selectedFragment = new GradesFragment();
//            } else if (id == R.id.nav_files) {
//                selectedFragment = new FilesFragment();
//            } else if (id == R.id.nav_badges) {
//                selectedFragment = new BadgesFragment();
//            } else if (id == R.id.nav_preference) {
//                selectedFragment = new PreferenceFragment();
//            } else if (id == R.id.nav_out) {
//                finishAffinity(); // Exit app
//                return true;
//            }
//
//            if (selectedFragment != null) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, selectedFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//
//            drawerLayout.closeDrawers();
//            return true;
//        });

        // TabLayout + ViewPager2
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        HomePagerAdapter adapter = new HomePagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) tab.setText("System home page"); // SystemFragment
            else tab.setText("Control panel");                 // DashboardFragment
        }).attach();

        // BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                // Hiển thị lại ViewPager2 + TabLayout
                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.fragment_container).setVisibility(View.GONE);
                viewPager.setCurrentItem(0);
            } else if (id == R.id.notification) {
                selectedFragment = new NotificationFragment();
            } else if (id == R.id.calendar) {
                selectedFragment = new CalendarFragment();
            } else if (id == R.id.blog) {
                selectedFragment = new BlogsFragment();
            } else if (id == R.id.more) {
                selectedFragment = new MoreFragment();
            }

            if (selectedFragment != null) {
                openFragment(selectedFragment);
                // Ẩn ViewPager2 + TabLayout, hiển thị fragment riêng
//                viewPager.setVisibility(View.GONE);
//                tabLayout.setVisibility(View.GONE);
//                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
//
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, selectedFragment)
//                        .commit();
            }
            return true;
        });

//        // 👉 Mặc định hiển thị ViewPager
//        viewPager.setVisibility(View.VISIBLE);
//        tabLayout.setVisibility(View.VISIBLE);
//        findViewById(R.id.fragment_container).setVisibility(View.GONE);
//        viewPager.setCurrentItem(0);

        // 👉 Bắt sự kiện nút btn_course_list (nằm trong layout nào đó)
        View btnCourseList = findViewById(R.id.btn_course_list);
        if (btnCourseList != null) {
            btnCourseList.setOnClickListener(v -> {
                // Ẩn ViewPager + TabLayout
                viewPager.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

                // Mở CoursesCategoriesFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CoursesCategoriesFragment())
                        .addToBackStack(null) // cho phép back về ViewPager
                        .commit();
            });
        }

        String fragmentName = getIntent().getStringExtra("fragment");
        if (fragmentName != null) {
            Fragment fragment = null;
            if ("grades".equals(fragmentName)) {
                fragment = new GradesFragment();
            } else if ("files".equals(fragmentName)) {
                fragment = new FilesFragment();
            } else if ("badges".equals(fragmentName)) {
                fragment = new BadgesFragment();
            } else if ("preference".equals(fragmentName)) {
                fragment = new PreferenceFragment();
            }

            if (fragment != null) {
                openFragment(fragment);
                return; // skip loading ViewPager
            }
        }

        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        findViewById(R.id.fragment_container).setVisibility(View.GONE);
        viewPager.setCurrentItem(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }

        if (item.getItemId() == R.id.action_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onSupportNavigateUp();
    }

    public void openFragment(Fragment fragment) {
        // Hide the ViewPager and TabLayout
        if (viewPager != null && tabLayout != null) {
            viewPager.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
        }

        // Show the fragment container
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

        // Replace the current fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}
