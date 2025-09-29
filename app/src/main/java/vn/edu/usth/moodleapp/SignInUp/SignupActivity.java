package vn.edu.usth.moodleapp.SignInUp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.network.ApiClient;
import vn.edu.usth.moodleapp.network.CreateUserResponse;
import vn.edu.usth.moodleapp.network.MoodleApi;
import android.util.Log;


public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etRePassword;
    private MoodleApi api;

    // ⚠️ Thay bằng token của external service có quyền core_user_create_users
    private static final String ADMIN_TOKEN = "dcf02fcbb243866e88f57db5787340a9";

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

        api = ApiClient.getService();

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

        api.createUser(
                ADMIN_TOKEN,
                "core_user_create_users",
                "json",
                username,
                password,
                username,   // firstname
                "User",     // lastname
                email
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String json = response.body().string().trim();
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
                            // Server trả object lỗi
                            Toast.makeText(SignupActivity.this,
                                    "Server error: " + json,
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(SignupActivity.this,
                                "Parse error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this,
                            "HTTP error " + response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignupActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
