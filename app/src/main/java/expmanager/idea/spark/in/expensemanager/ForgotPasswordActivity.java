package expmanager.idea.spark.in.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import expmanager.idea.spark.in.expensemanager.model.ForgotPassword;
import expmanager.idea.spark.in.expensemanager.model.LoginRequest;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RamanaRedddy on 2/22/17.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button btnBack,btnForgotPassword;
    private EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        btnBack = (Button) findViewById(R.id.btn_back);
        email = (EditText)findViewById(R.id.email_forgot_password);
        btnForgotPassword = (Button) findViewById(R.id.btn_forgot_password);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((!email.getText().toString().isEmpty())){


                    ForgotPassword forgotPassword = new ForgotPassword(email.getText().toString());
                    RetrofitApi.getApi().ForgotPasswordExpenseManager(forgotPassword).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if(response.isSuccessful()){

                                finish();

                            }else {

                                Toast.makeText(ForgotPasswordActivity.this,"Oops something went wrong",Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(ForgotPasswordActivity.this,"Oops something went wrong",Toast.LENGTH_SHORT).show();

                        }
                    });



                }else {
                    Toast.makeText(ForgotPasswordActivity.this,"Enter all the fields",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}