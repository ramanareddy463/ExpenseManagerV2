package expmanager.idea.spark.in.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kveldurty on 2/20/17.
 */

public class LoginActivty extends AppCompatActivity {

    private Button login;
    private Button forgotPassword;
    private Button usePin;
    private Button signUp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = (Button) findViewById(R.id.loginbtn);
        forgotPassword = (Button) findViewById(R.id.forgot_password);
        usePin = (Button) findViewById(R.id.use_pin);
        signUp = (Button) findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivty.this, MainActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivty.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        usePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivty.this, UsePinActivity.class);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivty.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}
