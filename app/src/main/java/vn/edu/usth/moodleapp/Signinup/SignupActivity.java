package vn.edu.usth.moodleapp.Signinup;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.network.CreateUserResponse;
import vn.edu.usth.moodleapp.network.MoodleVolleyClient;
import android.util.Log;


public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etRePassword;
    private MoodleVolleyClient volleyClient;


    private static final String ADMIN_TOKEN = "986624f3530e6493486ae5ec3956aed3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername   = findViewById(R.id.username);
        etEmail      = findViewById(R.id.email);
        etPassword   = findViewById(R.id.password);
        etRePassword = findViewById(R.id.repassword);
        Button btnSignUp = findViewById(R.id.signup);
        Button btnSignIn = findViewById(R.id.signin);

        volleyClient = new MoodleVolleyClient(this);

        btnSignUp.setOnClickListener(v -> attemptSignUp());
        btnSignIn.setOnClickListener(v -> finish());
    }

    private void attemptSignUp() {
        String username = etUsername.getText().toString().trim();
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String repass   = etRePassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(repass)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        volleyClient.createUser(
                ADMIN_TOKEN,
                username,
                password,
                username,   // firstname
                "User",     // lastname
                email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String json = response.trim();
                            if (json.startsWith("[")) {
                                // Thành công: parse list
                                Type listType = new TypeToken<List<CreateUserResponse>>(){}.getType();
                                List<CreateUserResponse> users = new Gson().fromJson(json, listType);
                                Toast.makeText(SignupActivity.this,
                                        "User created: " + users.get(0).username,
                                        Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Log.e("SignUp", "Server error: " + json);

                                Toast.makeText(SignupActivity.this,
                                        "Server error: " + json,
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SignupActivity.this,
                                    "Parse error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
