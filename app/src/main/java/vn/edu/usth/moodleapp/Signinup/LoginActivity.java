package vn.edu.usth.moodleapp.Signinup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import vn.edu.usth.moodleapp.MainActivity;
import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.network.MoodleVolleyClient;
import vn.edu.usth.moodleapp.network.TokenResponse;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt;
    private Button signInBtn;
    private MoodleVolleyClient volleyClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameEt = findViewById(R.id.username1);
        passwordEt = findViewById(R.id.password1);
        signInBtn  = findViewById(R.id.signin1);


        Button signUpBtn = findViewById(R.id.signup1);
        signUpBtn.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class))
        );


        volleyClient = new MoodleVolleyClient(this);


        signInBtn.setOnClickListener(v -> login());
    }


    private void login() {
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter username & password", Toast.LENGTH_SHORT).show();
            return;
        }


        volleyClient.login(username, password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            TokenResponse tokenResponse = volleyClient.parseTokenResponse(response);

                            if (tokenResponse != null && tokenResponse.token != null && !tokenResponse.token.isEmpty()) {
                                String token = tokenResponse.token;


                                getSharedPreferences("moodle", MODE_PRIVATE)
                                        .edit()
                                        .putString("token", token)
                                        .putString("username", username)
                                        .apply();


                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Login failed: check username/password",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this,
                                    "Error parsing response: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
