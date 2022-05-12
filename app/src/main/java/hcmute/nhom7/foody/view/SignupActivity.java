package hcmute.nhom7.foody.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.SignupDAO;
import hcmute.nhom7.foody.model.User;

public class SignupActivity extends AppCompatActivity {
    private EditText edtFullName, edtEmail, edtPassword, edtPasswordAgain;
    private Button btnSignup;
    private SignupDAO signupDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupDAO = new Database(this);

        edtFullName = (EditText) findViewById(R.id.edittextFullNameSignup);
        edtEmail = (EditText) findViewById(R.id.edittextEmailSignup);
        edtPassword = (EditText) findViewById(R.id.edittextPasswordSignup);
        edtPasswordAgain = (EditText) findViewById(R.id.edittextPasswordAgainSignup);
        btnSignup = (Button) findViewById(R.id.buttonSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = edtFullName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordAgain = edtPasswordAgain.getText().toString();

                if (validateInput(fullName, email, password, passwordAgain)) {
                    User newUser = new User(fullName, email, password);
                    if (signupDAO.signUp(newUser)) {
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        Toast.makeText(SignupActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupActivity.this, "Đăng ký tài khoản không thành công.\n" +
                                "Vui lòng thực hiện lại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean validateInput(String fullName, String email, String password, String passwordAgain) {
        if (fullName.isEmpty() || fullName == null) {
            Toast.makeText(SignupActivity.this, "Chưa nhập họ tên", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.isEmpty() || email == null) {
            Toast.makeText(SignupActivity.this, "Chưa nhập email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty() || password == null) {
            Toast.makeText(SignupActivity.this, "Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordAgain.isEmpty() || passwordAgain == null) {
            Toast.makeText(SignupActivity.this, "Chưa nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(passwordAgain)) {
            Toast.makeText(SignupActivity.this, "Mật khẩu không khớp, vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (signupDAO.checkExistsEmail(email)) {
            Toast.makeText(SignupActivity.this, "Địa chỉ email đã tồn tại", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}