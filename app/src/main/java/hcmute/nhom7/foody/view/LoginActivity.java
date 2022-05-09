package hcmute.nhom7.foody.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.LoginDAO;
import hcmute.nhom7.foody.database.UserDAO;
import hcmute.nhom7.foody.model.User;

public class LoginActivity extends AppCompatActivity {
    TextView txtSignup;
    private Button btnLogin;
    private EditText edtEmail, edtPassword;
    private LoginDAO loginDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        loginDAO = new Database(this);

        edtEmail = (EditText) findViewById(R.id.edittextEmail);
        edtPassword = (EditText) findViewById(R.id.edittextPassword);

        txtSignup = (TextView) findViewById(R.id.textSignup);
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                User user = loginDAO.login(email, password);
                if(user != null) {
                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
