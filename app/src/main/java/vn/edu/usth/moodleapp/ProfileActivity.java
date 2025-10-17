package vn.edu.usth.moodleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Account");
        }


        Button profileUserBtn = findViewById(R.id.profile_user);
        SharedPreferences sp = getSharedPreferences("moodle", MODE_PRIVATE);
        String username = sp.getString("username", "Guest");
        if (profileUserBtn != null) {
            profileUserBtn.setText(username);
        }


        Button logoutBtn = findViewById(R.id.profile_logout);
        if (logoutBtn != null) {
            logoutBtn.setOnClickListener(v -> {
                sp.edit().clear().apply();
                Intent i = new Intent(this, vn.edu.usth.moodleapp.Signinup.LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
