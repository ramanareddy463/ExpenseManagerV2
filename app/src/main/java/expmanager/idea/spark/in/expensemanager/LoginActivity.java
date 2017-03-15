package expmanager.idea.spark.in.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import expmanager.idea.spark.in.expensemanager.model.LoginRequest;
import expmanager.idea.spark.in.expensemanager.model.LoginResponse;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.NetworkUtils;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kveldurty on 2/20/17.
 */

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button forgotPassword;
    private Button usePin;
    private Button signUp;
    private EditText userName, password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = (Button) findViewById(R.id.loginbtn);
        forgotPassword = (Button) findViewById(R.id.forgot_password);
        usePin = (Button) findViewById(R.id.use_pin);
        signUp = (Button) findViewById(R.id.sign_up);

        userName = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!NetworkUtils.getInstance().isNetworkAvailable(LoginActivity.this)) {

                    Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((!userName.getText().toString().isEmpty()) && (!password.getText().toString().isEmpty())) {

                    LoginRequest loginRequest = new LoginRequest(userName.getText().toString(), password.getText().toString(), Utils.getDeviceId(LoginActivity.this));
                    RetrofitApi.getApi().loginExpenseManager(loginRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if (response.isSuccessful()) {

                                Gson gson = new Gson();
                                try {
                                    LoginResponse loginResponse = gson.fromJson(response.body().string(), LoginResponse.class);
                                    SessionManager sessionManager = new SessionManager(LoginActivity.this);
                                    sessionManager.createLoginSession(loginResponse.getToken());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            } else {

                                Toast.makeText(LoginActivity.this, "Oops something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(LoginActivity.this, "Oops something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    });


                } else {

                    Toast.makeText(LoginActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        usePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, UsePinActivity.class);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}
