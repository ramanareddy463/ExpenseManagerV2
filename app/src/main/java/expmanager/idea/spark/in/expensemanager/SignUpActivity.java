package expmanager.idea.spark.in.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import expmanager.idea.spark.in.expensemanager.fragments.ExpenseHistoryFragment;
import expmanager.idea.spark.in.expensemanager.fragments.StaffProfileFragment;
import expmanager.idea.spark.in.expensemanager.model.LoginResponse;
import expmanager.idea.spark.in.expensemanager.model.SignUpRequest;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.NetworkUtils;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RamanaRedddy on 2/22/17.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnBack,btnSignUp;
    private EditText userName,email,password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        btnBack = (Button) findViewById(R.id.btn_back);
        userName = (EditText) findViewById(R.id.user_name_sign_up);
        email = (EditText) findViewById(R.id.email_sign_up);
        password = (EditText) findViewById(R.id.password_sign_up);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        btnSignUp.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_back:
                  finish();

                break;

            case R.id.btn_sign_up:

                if (!NetworkUtils.getInstance().isNetworkAvailable(SignUpActivity.this)) {

                    Toast.makeText(SignUpActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }


                if((!userName.getText().toString().isEmpty())&&(!email.getText().toString().isEmpty())&&(!password.getText().toString().isEmpty())){

                    progressBar.setVisibility(View.VISIBLE);

                    SignUpRequest signUpRequest = new SignUpRequest(email.getText().toString(),userName.getText().toString(),password.getText().toString(), Utils.getDeviceId(SignUpActivity.this));
                    RetrofitApi.getApi().SignUpExpenseManager(signUpRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                Gson gson = new Gson();
                                try {
                                    LoginResponse loginResponse = gson.fromJson(response.body().string(), LoginResponse.class);
                                    SessionManager sessionManager = new SessionManager(SignUpActivity.this);
                                    sessionManager.createLoginSession(loginResponse.getToken(),loginResponse.getUser().getUsername(),
                                            loginResponse.getUser().getEmail(),loginResponse.getUser().getCompany_id());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            } else {

                                try {
                                    String errorBody = response.errorBody().string();
                                    JSONObject jsonObject = new JSONObject(errorBody);
                                    Toast.makeText(SignUpActivity.this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

//                                {
//                                    "status": "failed",
//                                        "message": "Validation failed: Email has already been taken, Email has already been taken, Username has already been taken"
//                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(SignUpActivity.this,"Oops something went wrong",Toast.LENGTH_SHORT).show();

                        }
                    });



                }else {
                    Toast.makeText(this,"Enter all the fields",Toast.LENGTH_SHORT).show();
                }

                break;

        }


    }
}
