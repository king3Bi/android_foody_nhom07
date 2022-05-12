package hcmute.nhom7.foody.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.PrepareData;

public class MainActivity extends AppCompatActivity {

    TextView txtVersion;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(MainActivity.this);

        txtVersion = (TextView) findViewById(R.id.textVersion);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersion.setText(getString(R.string.version) + " " + packageInfo.versionName);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            },500);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}