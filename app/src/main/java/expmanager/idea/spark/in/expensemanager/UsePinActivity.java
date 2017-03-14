package expmanager.idea.spark.in.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ramana.Reddy on 2/22/2017.
 */

public class UsePinActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView tvPin;
    private Button signUp;
    private Button usePassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_pin_activity);

        signUp = (Button) findViewById(R.id.sign_up);
        tvPin = (TextView) findViewById(R.id.tv_enter_pin);
        usePassword = (Button) findViewById(R.id.use_password);

        KeyboardInitUI();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(UsePinActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        usePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(UsePinActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        tvPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               int size = charSequence.length();
                if(size!=4){
                    return;
                }
                if(charSequence.toString().equalsIgnoreCase("1234")){

                    Intent intent = new Intent(UsePinActivity.this, AdminActivity.class);
                    startActivity(intent);
                    finish();

                }else if (charSequence.toString().equalsIgnoreCase("5678")){

                    Intent intent = new Intent(UsePinActivity.this, StaffActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    private void KeyboardInitUI() {

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btn1 :
                tvPin.setText(tvPin.getText()+"1");

            break;
            case R.id.btn2 :
                tvPin.setText(tvPin.getText()+"2");

                break;
            case R.id.btn3 :
                tvPin.setText(tvPin.getText()+"3");

                break;
            case R.id.btn4 :
                tvPin.setText(tvPin.getText()+"4");

                break;
            case R.id.btn5 :
                tvPin.setText(tvPin.getText()+"5");

                break;

            case R.id.btn6 :
                tvPin.setText(tvPin.getText()+"6");

                break;
            case R.id.btn7 :
                tvPin.setText(tvPin.getText()+"7");

                break;

            case R.id.btn8 :
                tvPin.setText(tvPin.getText()+"8");

                break;
            case R.id.btn9 :
                tvPin.setText(tvPin.getText()+"9");

                break;

            case R.id.btn0 :
                tvPin.setText(tvPin.getText()+"0");

                break;

            case R.id.btn_clear :
                int size = tvPin.getText().toString().length();
                if(size>0) {
                    tvPin.setText(tvPin.getText().toString().substring(0, size - 1));
                }
                break;

            default :

        }
    }
}
