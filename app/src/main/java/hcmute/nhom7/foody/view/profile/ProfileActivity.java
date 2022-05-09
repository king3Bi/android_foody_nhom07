package hcmute.nhom7.foody.view.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.SupportMapFragment;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.view.fragment.MoreFragment;
import hcmute.nhom7.foody.view.home.HomeFragment;
import hcmute.nhom7.foody.view.profile.fragment.ActivitiesFragment;
import hcmute.nhom7.foody.view.profile.fragment.CollectionsFragment;
import hcmute.nhom7.foody.view.profile.fragment.PhotoVideoFragment;

public class ProfileActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private User user;
    TextView textViewUsername;
    RadioGroup radioGroup;
    RadioButton rdActivities, rdPhotoVideo, rdCollections;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        replaceFragment(new ActivitiesFragment());

        user = (User) getIntent().getSerializableExtra("user");

        textViewUsername = findViewById(R.id.textViewUsernameProfile);
        textViewUsername.setText(user.getHoTen());

        rdActivities = (RadioButton) findViewById(R.id.radioActivities);
        rdPhotoVideo = (RadioButton) findViewById(R.id.radioPhotoVideo);
        rdCollections = (RadioButton) findViewById(R.id.radioCollections);
        radioGroup = findViewById(R.id.radioGroup);
        toolbar = findViewById(R.id.toolbarProfile);

        radioGroup.setOnCheckedChangeListener(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioActivities:
                replaceFragment(new ActivitiesFragment());
                break;
            case R.id.radioPhotoVideo:
                replaceFragment(new PhotoVideoFragment());
                break;
            case R.id.radioCollections:
                replaceFragment(new CollectionsFragment());
                break;

            default:
                replaceFragment(new ActivitiesFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutProfile, fragment);
        fragmentTransaction.commit();
    }
}