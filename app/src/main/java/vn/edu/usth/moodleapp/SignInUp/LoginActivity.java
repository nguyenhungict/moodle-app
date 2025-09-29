package vn.edu.usth.moodleapp.SignInUp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.moodleapp.MainActivity;
import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.network.ApiClient;
import vn.edu.usth.moodleapp.network.MoodleApi;
import vn.edu.usth.moodleapp.network.TokenResponse;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt;
    private Button signInBtn;
    private MoodleApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);   // layout bạn đã gửi

        // Ánh xạ view từ XML
        usernameEt = findViewById(R.id.username1);
        passwordEt = findViewById(R.id.password1);
        signInBtn  = findViewById(R.id.signin1);

        //nút Sign up
        Button signUpBtn = findViewById(R.id.signup1);
        signUpBtn.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class))
        );

        // Tạo Retrofit service
        api = ApiClient.getService();

        // Bắt sự kiện nút “Sign in”
        signInBtn.setOnClickListener(v -> login());
    }

    /** Gọi API Moodle để lấy token */
    private void login() {
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter username & password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Gọi /login/token.php
        api.getToken(username, password, "moodle_mobile_app")
                .enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        if (response.isSuccessful()
                                && response.body() != null
                                && response.body().token != null) {

                            String token = response.body().token;

                            // Lưu token vào SharedPreferences (có thể dùng EncryptedSharedPreferences nếu cần)
                            getSharedPreferences("moodle", MODE_PRIVATE)
                                    .edit()
                                    .putString("token", token)
                                    .apply();

                            // Chuyển sang MainActivity
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Login failed: check username/password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,
                                "Error: " + t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
