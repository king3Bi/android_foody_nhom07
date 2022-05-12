package hcmute.nhom7.foody.view.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.UserDAO;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.view.LoginActivity;
import hcmute.nhom7.foody.view.NavigationActivity;

public class ProfileActivity extends AppCompatActivity {

    private User user;
    private UserDAO userDAO;
    EditText edtFullName, edtEmail, edtNewPassword, edtPasswordAgain;
    Button btnUpdate, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = (User) getIntent().getSerializableExtra("user");
        userDAO = new Database(ProfileActivity.this);

        edtFullName = findViewById(R.id.edittextFullNameProfile);
        edtEmail = findViewById(R.id.edittextEmailProfile);
        edtNewPassword = findViewById(R.id.edittextNewPasswordProfile);
        edtPasswordAgain = findViewById(R.id.edittextPasswordAgainProfile);

        loadData();

        btnCancel = findViewById(R.id.btnCancelProfile);
        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_OK,
                    new Intent().putExtra("user", user));
            finish();
        });

        btnUpdate = findViewById(R.id.btnUpdateProfile);
        btnUpdate.setOnClickListener(v -> {
            String newPassword = edtNewPassword.getText().toString();
            String passwordAgain = edtPasswordAgain.getText().toString();

            String fullName = edtFullName.getText().toString();
            String email = edtEmail.getText().toString();
            User userCopy = new User(user.getId(), fullName, email);

            if (isUpdatePassword()) {
                if (!newPassword.equals(passwordAgain)) {
                    Toast.makeText(ProfileActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    userCopy.setPassword(newPassword);
                    doAfterUpdate(userDAO.updateFullInfo(userCopy));
                }
            } else {
                doAfterUpdate(userDAO.updateInfo(userCopy));
            }
        });

    }

    private void doAfterUpdate(boolean b) {
        if (b) {

            Toast.makeText(ProfileActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            user = userDAO.getUserById(user.getId());
            loadData();
        } else {
            Toast.makeText(ProfileActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();

        }
    }

    private void loadData() {
        edtFullName.setText(user.getHoTen());
        edtEmail.setText(user.getEmail());
        edtNewPassword.getText().clear();
        edtPasswordAgain.getText().clear();
    }

    private boolean isUpdatePassword() {
        String newPassword = edtNewPassword.getText().toString();
        String passwordAgain = edtPasswordAgain.getText().toString();

        return !newPassword.isEmpty() || !passwordAgain.isEmpty();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}