package expmanager.idea.spark.in.expensemanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import expmanager.idea.spark.in.expensemanager.fragments.ExpenseHistoryFragment;
import expmanager.idea.spark.in.expensemanager.fragments.StaffProfileFragment;
import expmanager.idea.spark.in.expensemanager.model.SignUpRequest;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        btnBack = (Button) findViewById(R.id.btn_back);
        userName = (EditText) findViewById(R.id.user_name_sign_up);
        email = (EditText) findViewById(R.id.email_sign_up);
        password = (EditText) findViewById(R.id.password_sign_up);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);

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

                if((!userName.getText().toString().isEmpty())&&(!email.getText().toString().isEmpty())&&(!password.getText().toString().isEmpty())){


                    SignUpRequest signUpRequest = new SignUpRequest(email.getText().toString(),userName.getText().toString(),password.getText().toString(), Utils.getDeviceId(SignUpActivity.this));
                    RetrofitApi.getApi().SignUpExpenseManager(signUpRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Toast.makeText(SignUpActivity.this,"Sign up successfully completed",Toast.LENGTH_SHORT).show();
                            finish();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

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
